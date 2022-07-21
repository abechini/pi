package com.esprit.bankPi.resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

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
import com.esprit.bankPi.repository.CompteRepository;

@RestController
@RequestMapping("/compte")
public class CompteServiceImpl implements ICompteService{

	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@PostMapping(path="/save", produces = "application/json")
	public Compte save(@RequestBody Compte compte) throws Exception {
		return compteRepository.save(compte);			}
	
	@PutMapping(path="/update", produces = "application/json")
	public Compte update(@RequestBody Compte compte) throws Exception {
		return compteRepository.save(compte);
	}
	
	@DeleteMapping(path="/{id}", produces = "application/json")
	public void deleteOne( @PathVariable Long id) throws Exception {
		compteRepository.deleteById(id);
	}

	@GetMapping(path="/{id}", produces = "application/json")
	public Compte findByid(@PathVariable Long id) throws Exception {
		return compteRepository.findById(id).orElse(null);
	}

	@GetMapping(path="/search/all", produces = "application/json")
	public Iterable<Compte> findAll() throws ServiceException {
		return compteRepository.findAll();
	}
	
	@GetMapping(path="/count", produces = "application/json")
	public Long count() throws Exception {
		return compteRepository.count();
	}
	
	 @Transactional
	 @PostMapping("/saveFiles/{clientID}/{accountID}")
	    public String uploadFile(@RequestParam("contract") MultipartFile contract,@RequestParam("cin") MultipartFile cin,@RequestParam("photo") MultipartFile photo,@PathVariable("clientID") String clientID,@PathVariable("accountID") String accountID) throws JSONException {
		 Client client  = clientRepository.findById(Long.valueOf(clientID)).orElse(null);
		 String location = "/pi/banque/client/docs/"+clientID+"/"+accountID+"/";
		 if(client!=null) {
			 Boolean existCompte = client.getCompteList().stream().anyMatch(c -> Long.valueOf(accountID).equals(c.getNumeroCompte()));
			 if(existCompte) {
				 Collection<MultipartFile> listOfFiles = new ArrayList<MultipartFile>();
				 if((!contract.isEmpty()&&contract.getSize()!=0)&&(!contract.isEmpty()&&contract.getSize()!=0)&&(!contract.isEmpty()&&contract.getSize()!=0)) {
					 listOfFiles.add(contract);
					 listOfFiles.add(cin);
					 listOfFiles.add(photo);
					 saveFiles(listOfFiles, location);
					 return "all files where uploaded with success";
				 }
				 return "one of the all files is missing";
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
}
