package com.esprit.bankPi.resources;

import java.io.ByteArrayInputStream;

import javax.websocket.server.PathParam;
import javax.ws.rs.QueryParam;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.util.ExcelHelper;

@RestController
@RequestMapping("/client")
public class ClientResources {
     @Autowired
    IClientService clientService ;
     
     
  	@PostMapping(path="/save", produces = "application/json")
  	public Client save(@RequestBody Client client) throws Exception {
  		return clientService.save(client);			}
  	
  	@PutMapping(path="/update", produces = "application/json")
  	public Client update(@RequestBody Client client) throws Exception {
  		return clientService.save(client);
  	}
  	
  	@DeleteMapping(path="/{id}", produces = "application/json")
  	public void deleteOne( @PathVariable("id") Long id) throws Exception {
  		clientService.deleteOne(id);
  	}

  	@GetMapping(path="/{id}", produces = "application/json")
  	public Client findByid(@PathVariable("id") Long id) throws Exception {
  		return clientService.findById(id);
  	}

  	@GetMapping(path="/search/all", produces = "application/json")
  	public Iterable<Client> findAll() throws ServiceException {
  		return clientService.findAll();
  	}
  	
  	@GetMapping(path="/count", produces = "application/json")
  	public Long count() throws Exception {
  		return clientService.count();
  	}
  	
	public ByteArrayInputStream exportExcel() {
		return ExcelHelper.FormulairesToExcel(clientService.findAll());
	}

	

}
