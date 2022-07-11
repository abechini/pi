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

import com.esprit.bankPi.data.CheckBook;
import com.esprit.bankPi.repository.CheckBookRepository;

@RestController
@RequestMapping("/checkbook")
public class CheckBookServiceImpl implements ICheckBookService{

	@Autowired
	private CheckBookRepository checkbookRepository;
	

	@PostMapping(path="/save", produces = "application/json")
	public CheckBook save(CheckBook checkBook) throws Exception {
		return checkbookRepository.save(checkBook);			}
	
	@PutMapping(path="/update", produces = "application/json")
	public CheckBook update(CheckBook checkBook) throws Exception {
		return checkbookRepository.save(checkBook);
	}
	

	@DeleteMapping(path="/{id}", produces = "application/json")
	public void deleteOne( @PathParam("id") Long id) throws Exception {
		checkbookRepository.deleteById(id);
	}

	@GetMapping(path="/{id}", produces = "application/json")
	public CheckBook findByid(@QueryParam("id") Long id) throws Exception {
		return checkbookRepository.findById(id).orElse(null);
	}

	@GetMapping(path="/search/all", produces = "application/json")
	public Iterable<CheckBook> findAll() throws ServiceException {
		return checkbookRepository.findAll();
	}
	
	@GetMapping(path="/count", produces = "application/json")
	public Long count() throws Exception {
		return checkbookRepository.count();
	}
	
	
}
