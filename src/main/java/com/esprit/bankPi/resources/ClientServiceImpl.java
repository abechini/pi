package com.esprit.bankPi.resources;

import java.io.ByteArrayInputStream;

import javax.websocket.server.PathParam;
import javax.ws.rs.QueryParam;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.repository.ClientRepository;
import com.esprit.bankPi.util.ExcelHelper;

@RestController
@RequestMapping("/client")
public class ClientServiceImpl implements  IClientService {
     @Autowired
    ClientRepository clientRepository ;
  	@PostMapping(path="/save", produces = "application/json")
  	public Client save(Client client) throws Exception {
  		return clientRepository.save(client);			}
  	
  	@PutMapping(path="/update", produces = "application/json")
  	public Client update(Client client) throws Exception {
  		return clientRepository.save(client);
  	}
  	
  	@DeleteMapping(path="/{id}", produces = "application/json")
  	public void deleteOne( @PathParam("id") Long id) throws Exception {
  		clientRepository.deleteById(id);
  	}

  	@GetMapping(path="/{id}", produces = "application/json")
  	public Client findByid(@QueryParam("id") Long id) throws Exception {
  		return clientRepository.findById(id).orElse(null);
  	}

  	@GetMapping(path="/search/all", produces = "application/json")
  	public Iterable<Client> findAll() throws ServiceException {
  		return clientRepository.findAll();
  	}
  	
  	@GetMapping(path="/count", produces = "application/json")
  	public Long count() throws Exception {
  		return clientRepository.count();
  	}
	@Override
	public ByteArrayInputStream exportExcel() {
		return ExcelHelper.FormulairesToExcel(clientRepository.findAll());
	}

	

}
