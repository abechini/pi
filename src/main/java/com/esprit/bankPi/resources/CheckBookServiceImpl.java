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

import com.esprit.bankPi.data.CheckBook;
import com.esprit.bankPi.repository.CheckBookRepository;

@Path("/checkbook")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({ MediaType.APPLICATION_JSON})
public class CheckBookServiceImpl implements ICheckBookService{

	@Autowired
	private CheckBookRepository checkbookRepository;
	
	@POST
	@Path("/save")
	public CheckBook save(CheckBook checkBook) throws Exception {
		return checkbookRepository.save(checkBook);			}
	
	@PUT
	public CheckBook update(CheckBook checkBook) throws Exception {
		return checkbookRepository.save(checkBook);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteOne( @PathParam("id") Long id) throws Exception {
		checkbookRepository.deleteById(id);
	}

	@GET
	public CheckBook findByid(@QueryParam("id") Long id) throws Exception {
		return checkbookRepository.findById(id).orElse(null);
	}

	@GET
	@Path("/search/all")
	public Iterable<CheckBook> findAll() throws ServiceException {
		return checkbookRepository.findAll();
	}
	
	@GET
	@Path("/count")
	public Long count() throws Exception {
		return checkbookRepository.count();
	}
	
	
}
