package com.esprit.bankPi.resources;

import java.io.ByteArrayInputStream;

import org.springframework.stereotype.Service;

@Service
public interface IClientService  {
	public ByteArrayInputStream exportExcel();
}
