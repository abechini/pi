package com.esprit.bankPi.chat.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.chat.ChatBotSearchUtil;


import java.sql.SQLException;

@RestController
public class RequestController {



@RequestMapping("/getMySQL")
public String showMySQLResult(
        @RequestParam(value = "filetype", defaultValue = "small") String fileType,
        @RequestParam(value = "searchMethod", defaultValue = "MySQL") String searchMethod,
        @RequestParam(value = "searchContent") String searchContent
) throws SQLException {
    String queryResult = ChatBotSearchUtil.doQueryMySQL(fileType, searchContent);
    return queryResult;
}




@RequestMapping("/getSearchHistory")
public String showSearchHistory() {
    return ChatBotSearchUtil.printHistory();
}



	
	

}
