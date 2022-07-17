package com.esprit.bankPi.resources;

import java.util.Optional;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.repository.CompteRepository;

@RestController
@RequestMapping("/compte")
public class CompteServiceImpl implements ICompteService{

	@Autowired
	private CompteRepository compteRepository;
	
	@PostMapping(path="/save", produces = "application/json")
	public Compte save(Compte compte) throws Exception {
		return compteRepository.save(compte);			}
	
	@PutMapping(path="/update", produces = "application/json")
	public Compte update(Compte compte) throws Exception {
		return compteRepository.save(compte);
	}
	
	@DeleteMapping(path="/{id}", produces = "application/json")
	public void deleteOne( @PathParam("id") Long id) throws Exception {
		compteRepository.deleteById(id);
	}

	@GetMapping(path="/{id}", produces = "application/json")
	public Compte findByid(@QueryParam("id") Long id) throws Exception {
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
	
	public Compte getAccountById(Long id) {
		Optional<Compte> act = compteRepository.findById(id);
		if (act.isPresent()) {
			return act.get();
		} else {
			return null;
		}
	}
	
 public Compte getAccountById(Long id) {
        Optional<Compte> act = compteRepository.findById(id);
        if (act.isPresent()) {
            return act.get();
        } else {
            return null;
        }
    }
}
