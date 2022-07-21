package com.esprit.bankPi.resources;

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

import com.esprit.bankPi.data.BankCarte;
import com.esprit.bankPi.repository.BankCarteRepository;

@RestController
@RequestMapping("/bankCarte")
public class BankCarteServiceImpl implements IBankCarteService{

	@Autowired
	private BankCarteRepository bankCarteRepository;
	
	@PostMapping(path="/save", produces = "application/json")
	public BankCarte save(BankCarte bankCarte) throws Exception {
		return bankCarteRepository.save(bankCarte);			}
	
	@PutMapping(path="/update", produces = "application/json")
	public BankCarte update(BankCarte bankCarte) throws Exception {
		return bankCarteRepository.save(bankCarte);
	}
	
	@DeleteMapping(path="/{id}", produces = "application/json")
	public void deleteOne( @PathParam("id") Long id) throws Exception {
		bankCarteRepository.deleteById(id);
	}

	@GetMapping(path="/{id}", produces = "application/json")
	public BankCarte findByid(@QueryParam("id") Long id) throws Exception {
		return bankCarteRepository.findById(id).orElse(null);
	}

	@GetMapping(path="/search/all", produces = "application/json")
	public Iterable<BankCarte> findAll() throws ServiceException {
		return bankCarteRepository.findAll();
	}
	
	@GetMapping(path="/count", produces = "application/json")
	public Long count() throws Exception {
		return bankCarteRepository.count();
	}
	
	
}
