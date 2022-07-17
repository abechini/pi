package com.esprit.bankPi.resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.apache.commons.io.FilenameUtils;
import org.codehaus.jettison.json.JSONException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.repository.CompteRepository;

@RestController
@RequestMapping("/compte")
public class CompteServiceImpl implements ICompteService{

	@Autowired
	private CompteRepository compteRepository;
	
	@PostMapping(path="/save", produces = "application/json")
	public Compte save(@QueryParam("compte") Compte compte) throws Exception {
		return compteRepository.save(compte);			}
	
	@PutMapping(path="/update", produces = "application/json")
	public Compte update(@QueryParam("compte") Compte compte) throws Exception {
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
	 @PostMapping("/save")
	    public String uploadFile(@RequestParam("contract") MultipartFile contract,@RequestParam("cin") MultipartFile cin,@RequestParam("photo") MultipartFile photo, @QueryParam("compte") Compte compte) throws JSONException {
		 Compte saved  = compteRepository.save(compte);
		 Collection<MultipartFile> listOfFiles = new ArrayList<MultipartFile>();
		 listOfFiles.add(contract);
		 listOfFiles.add(cin);
		 listOfFiles.add(photo);
		 String location = "/pi/banque/client/docs/"+saved.getClient()+"/"+saved.getNumeroCompte();
		 saveFiles(listOfFiles, location);
		return "all folders where uploaded with success";
		  
	    }
	 private void saveFiles(Collection<MultipartFile> files,String location) {
		 files.stream()
		 .forEach(file -> {
	                	 String fileName = FilenameUtils.normalize(file.getOriginalFilename());
	                	 File filepath = Paths.get(location, file.getOriginalFilename()).toFile();
	                	 filepath.mkdirs();
	                	 try {
							file.transferTo(filepath);							
					} catch (IllegalStateException | IOException e) {
						throw new RuntimeException("probleme occured when saving folders");
					}
				});
	}
}
