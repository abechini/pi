package com.esprit.bankPi.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.esprit.bankPi.chat.ChatService;
import com.esprit.bankPi.model.Reclamation;

@Controller
public class chabotController {
	   @Autowired
	    private ChatService service;
	     
	    @RequestMapping("/search")
	  //  public String viewHomePage(Model model) {
//	        Page<Reclamation> page =service.listAll();
//	    	List<Reclamation> listReclamations = page.getContent();
//	        model.addAttribute("listReclamations", listReclamations);
	    	 public String viewHomePage(Model model,
	    			 @Param("keyword") String keyword) {
	    	        List<Reclamation> listReclamations = service.listAll(keyword);
	    	       
	    	        model.addAttribute("listReclamations", listReclamations);
	    	      //  model.addAttribute("keyword", keyword);
	       
	         
	        return "search";
	    }


}
