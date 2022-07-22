package com.esprit.bankPi.resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import javax.ws.rs.QueryParam;

import org.codehaus.jettison.json.JSONException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.repository.ClientRepository;

@RestController
@RequestMapping("/compte")
public class CompteResources {

	@Autowired
	private ICompteService compteService;
	
	@Autowired
	private IClientService clientService;
	
	@PostMapping(path="/save", produces = "application/json")
	public Compte save(@RequestBody Compte compte,@QueryParam(value = "clientID") Long clientID) throws Exception {
		Client client = clientService.findById(clientID);
		if(client!=null) {
			client.getCompteList().add(compte);
			compte.setClient(client);
			clientService.save(client);
			return compte;
		}
		return null;			}
	
	@PutMapping(path="/update", produces = "application/json")
	public Compte update(@RequestBody Compte compte) throws Exception {
		return compteService.save(compte);
	}
	
	@DeleteMapping(path="/{id}", produces = "application/json")
	public void deleteOne( @PathVariable Long id) throws Exception {
		compteService.deleteOne(id);
	}

	@GetMapping(path="/{id}", produces = "application/json")
	public Compte findByid(@PathVariable Long id) throws Exception {
		return compteService.findByid(id);
	}

	@GetMapping(path="/search/all", produces = "application/json")
	public Iterable<Compte> findAll() throws ServiceException {
		return compteService.findAll();
	}
	
	@GetMapping(path="/count", produces = "application/json")
	public Long count() throws Exception {
		return compteService.count();
	}
	
	 @Transactional
	 @PostMapping("/saveFiles/{clientID}/{accountID}")
	    public String uploadFile(@RequestParam("contract") MultipartFile contract,@RequestParam("cin") MultipartFile cin,@RequestParam("photo") MultipartFile photo,@PathVariable("clientID") String clientID,@PathVariable("accountID") String accountID) throws NumberFormatException, Exception {
		 Client client  = clientService.findById(Long.valueOf(clientID));
		 String location = "/pi/banque/client/docs/"+clientID+"/"+accountID+"/";
		 if(client!=null) {
			 Boolean existCompte = client.getCompteList().stream().anyMatch(c -> Long.valueOf(accountID).equals(c.getNumeroCompte()));
			 if(existCompte) {
				 Collection<MultipartFile> listOfFiles = new ArrayList<MultipartFile>();
				 if((!contract.isEmpty()&&contract.getSize()!=0)&&(!contract.isEmpty()&&contract.getSize()!=0)&&(!contract.isEmpty()&&contract.getSize()!=0)) {
					 if(isCorrectExtension(contract)&&isCorrectExtension(cin)&& isCorrectExtension(photo)) {
						 listOfFiles.add(contract);
						 listOfFiles.add(cin);
						 listOfFiles.add(photo);
						 saveFiles(listOfFiles, location);
						 return "all files where uploaded with success";
					 }
					 return "one of the files has wrong extension ( we only accept pdf or image )";

				 }
				 return "one of the files is missing";
			 }
			 return "the account with id : "+accountID+" not exist";
		 }
		 return "the client with id : "+clientID+" not exist";
		   
	    }
	 private void saveFiles(Collection<MultipartFile> files,String location) {
		 files.stream()
		 .forEach(file -> {
							  new File(location).mkdirs();
							  Path path = Path.of(location+"/"+file.getOriginalFilename());
			                try {
									file.transferTo(path);							
							} catch (IllegalStateException | IOException e) {
								throw new RuntimeException("probleme occured when saving folders",e);
							}
						});
	}
	 
	 private Boolean isCorrectExtension(MultipartFile file) {
		 boolean test = false;
		 String mimetype =file.getContentType();
		 if(mimetype!=null && (mimetype.split("/")[0].equals("image")|| mimetype.split("/")[1].equals("pdf"))) {
			 test= true;
		 }
		 return test;

		 
	 }
}
