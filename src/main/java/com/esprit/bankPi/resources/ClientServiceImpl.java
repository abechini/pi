package com.esprit.bankPi.resources;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.bankPi.repository.ClientRepository;
import com.esprit.bankPi.util.ExcelHelper;

@Service
public class ClientServiceImpl implements  IClientService {
     @Autowired
    ClientRepository clientRepository ;

	@Override
	public ByteArrayInputStream exportExcel() {
		return ExcelHelper.FormulairesToExcel(clientRepository.findAll());
	}

	

}
