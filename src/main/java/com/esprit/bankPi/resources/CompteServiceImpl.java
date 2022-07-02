package com.esprit.bankPi.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.repository.CompteRepository;

@Path("/compte")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({ MediaType.APPLICATION_JSON})
public class CompteServiceImpl implements ICompteService{

	@Autowired
	private CompteRepository compteRepository;
	
	@POST
	@Path("/save")
	public Compte save(Compte compte) throws Exception {
		return compteRepository.save(compte);			}
	
	@PUT
	public Compte update(Compte compte) throws Exception {
		return compteRepository.save(compte);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteOne( @PathParam("id") Long id) throws Exception {
		compteRepository.deleteById(id);
	}

	@GET
	public Compte findByid(@QueryParam("id") Long id) throws Exception {
		return compteRepository.findById(id).orElse(null);
	}

	@GET
	@Path("/search/all")
	public Iterable<Compte> findAll() throws ServiceException {
		return compteRepository.findAll();
	}
	
	@GET
	@Path("/count")
	public Long count() throws Exception {
		return compteRepository.count();
	}
	
	
	
}
