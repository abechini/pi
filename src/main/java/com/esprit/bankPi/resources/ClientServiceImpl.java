package com.esprit.bankPi.resources;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.QueryParam;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.repository.ClientRepository;
import com.esprit.bankPi.util.ExcelHelper;

@Service
public class ClientServiceImpl implements  IClientService {
    
	@Autowired
    ClientRepository clientRepository ;
     
  	public Client save(Client client) {
  		return clientRepository.save(client);			}
  	
  	public Client update(Client client) throws Exception {
  		return clientRepository.save(client);
  	}
  	
  	public void deleteOne(Long id) throws Exception {
  		clientRepository.deleteById(id);
  	}

  	public Client findById(Long id) throws Exception {
  		return clientRepository.findById(id).orElse(null);
  	}

  	public List<Client> findAll() throws ServiceException {
  		return clientRepository.findAll();
  	}
  	
  	public Long count() throws Exception {
  		return clientRepository.count();
  	}
	@Override
	public ByteArrayInputStream exportExcel() {
		return ExcelHelper.FormulairesToExcel(clientRepository.findAll());
	}

	

}
