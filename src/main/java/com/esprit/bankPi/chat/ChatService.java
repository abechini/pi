package com.esprit.bankPi.chat;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.esprit.bankPi.model.Reclamation;

@Service
public class ChatService {
    @Autowired
    private chatRepository repo;
     
    public List<Reclamation> listAll(String keyword) {
        if (keyword != null && keyword.length()!=0) {
        	
            return repo.findByName(keyword);
        }
        return repo.findAll();
    }



}
