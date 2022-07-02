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

import com.esprit.bankPi.data.BankCarte;
import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.repository.BankCarteRepository;

@Path("/bankcate")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({ MediaType.APPLICATION_JSON})
public class BankCarteServiceImpl implements IBankCarteService{

	@Autowired
	private BankCarteRepository bankCarteRepository;
	
	@POST
	@Path("/save")
	public BankCarte save(BankCarte bankCarte) throws Exception {
		return bankCarteRepository.save(bankCarte);			}
	
	@PUT
	public BankCarte update(BankCarte bankCarte) throws Exception {
		return bankCarteRepository.save(bankCarte);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteOne( @PathParam("id") Long id) throws Exception {
		bankCarteRepository.deleteById(id);
	}

	@GET
	public BankCarte findByid(@QueryParam("id") Long id) throws Exception {
		return bankCarteRepository.findById(id).orElse(null);
	}

	@GET
	@Path("/search/all")
	public Iterable<BankCarte> findAll() throws ServiceException {
		return bankCarteRepository.findAll();
	}
	
	@GET
	@Path("/count")
	public Long count() throws Exception {
		return bankCarteRepository.count();
	}
	
	
}
