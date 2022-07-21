package com.esprit.bankPi.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.chat.ChatBotSearchUtil;
import com.esprit.bankPi.repository.ReclamationRepository;

import java.sql.SQLException;

@RestController
public class RequestController {
@Autowired
ReclamationRepository reclamationRepository;


@RequestMapping("/getMySQL")
public String showMySQLResult(
        @RequestParam(value = "filetype", defaultValue = "small") String fileType,
        @RequestParam(value = "searchMethod", defaultValue = "MySQL") String searchMethod,
        @RequestParam(value = "searchContent") String searchContent
) throws SQLException {
    String queryResult = ChatBotSearchUtil.doQueryMySQL(fileType, searchContent,reclamationRepository);
    return queryResult;
}




@RequestMapping("/getSearchHistory")
public String showSearchHistory() {
    return ChatBotSearchUtil.printHistory();
}



	
	

}
