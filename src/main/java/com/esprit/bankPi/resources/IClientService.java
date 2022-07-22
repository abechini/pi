package com.esprit.bankPi.resources;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.Client;

@Service
public interface IClientService  {
	public ByteArrayInputStream exportExcel();

	public Client save(Client client) throws Exception;

	public void deleteOne(Long id) throws Exception;

	public Client findById(Long id) throws Exception;

	public List<Client> findAll() throws ServiceException;

	public Long count() throws Exception;
}
