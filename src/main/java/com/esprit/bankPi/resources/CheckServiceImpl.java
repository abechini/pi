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

import com.esprit.bankPi.data.Check;
import com.esprit.bankPi.repository.CheckRepository;

@RestController
@RequestMapping("/check")
public class CheckServiceImpl {

	@Autowired
	private CheckRepository checkRepository;
	
	@PostMapping(path="/save", produces = "application/json")
	public Check save(Check check) throws Exception {
		return checkRepository.save(check);			}
	
	@PutMapping(path="/update", produces = "application/json")
	public Check update(Check check) throws Exception {
		return checkRepository.save(check);
	}
	
	@DeleteMapping(path="/{id}", produces = "application/json")
	public void deleteOne( @PathParam("id") Long id) throws Exception {
		checkRepository.deleteById(id);
	}

	@GetMapping(path="/{id}", produces = "application/json")
	public Check findByid(@QueryParam("id") Long id) throws Exception {
		return checkRepository.findById(id).orElse(null);
	}

	@GetMapping(path="/search/all", produces = "application/json")
	public Iterable<Check> findAll() throws ServiceException {
		return checkRepository.findAll();
	}
	
	@GetMapping(path="/count", produces = "application/json")
	public Long count() throws Exception {
		return checkRepository.count();
	}
	
	
}
