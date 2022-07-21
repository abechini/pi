package com.esprit.bankPi.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.resources.ClientServiceImpl;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
   @Autowired
   ClientServiceImpl clientServiceImpl ;
   
   @GetMapping("/exportExcel")
   @ResponseBody
   public String exportExcel() {

       clientServiceImpl.exportExcel();
       return "clients exported successfully" ;
   }
}
