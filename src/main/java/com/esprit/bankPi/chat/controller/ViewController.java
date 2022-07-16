package com.esprit.bankPi.chat.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Controller
public class ViewController {
	   @RequestMapping("/chatbot")
	    public String view() {
	        return "home";
	    }

}
