package com.esprit.bankPi.resources;

import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.data.Compte;

@Service
public interface ICompteService {

	public Compte save( Compte compte) throws Exception ;
	
	public Compte update( Compte compte) throws Exception ;
	
	public void deleteOne( Long id) throws Exception ;

	public Compte findByid(Long id) throws Exception ;

	public Iterable<Compte> findAll() throws ServiceException ;
	public Long count() throws Exception ;

}
