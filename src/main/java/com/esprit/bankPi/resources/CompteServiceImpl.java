package com.esprit.bankPi.resources;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.repository.ClientRepository;
import com.esprit.bankPi.repository.CompteRepository;

@Service
public class CompteServiceImpl implements ICompteService{

	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Compte save( Compte compte) throws Exception {
		return compteRepository.save(compte);			}
	
	public Compte update( Compte compte) throws Exception {
		return compteRepository.save(compte);
	}
	
	public void deleteOne( Long id) throws Exception {
		compteRepository.deleteById(id);
	}

	public Compte findByid(Long id) throws Exception {
		return compteRepository.findById(id).orElse(null);
	}

	public Iterable<Compte> findAll() throws ServiceException {
		return compteRepository.findAll();
	}
	
	public Long count() throws Exception {
		return compteRepository.count();
	}
	
}
