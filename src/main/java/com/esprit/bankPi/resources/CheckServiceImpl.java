package com.esprit.bankPi.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import com.esprit.bankPi.data.Check;
import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.repository.CheckRepository;

public class CheckServiceImpl {

	@Autowired
	private CheckRepository checkRepository;
	
	@POST
	@Path("/save")
	public Check save(Check check) throws Exception {
		return checkRepository.save(check);			}
	
	@PUT
	public Check update(Check check) throws Exception {
		return checkRepository.save(check);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteOne( @PathParam("id") Long id) throws Exception {
		checkRepository.deleteById(id);
	}

	@GET
	public Check findByid(@QueryParam("id") Long id) throws Exception {
		return checkRepository.findById(id).orElse(null);
	}

	@GET
	@Path("/search/all")
	public Iterable<Check> findAll() throws ServiceException {
		return checkRepository.findAll();
	}
	
	@GET
	@Path("/count")
	public Long count() throws Exception {
		return checkRepository.count();
	}
	
	
}
