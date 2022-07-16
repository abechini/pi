package com.esprit.bankPi.chat.controller;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.esprit.bankPi.chat.ChatBotSearchUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

@RestController
public class RequestController {
	 /*
    Request controller for brute force search
 */

@RequestMapping("/getMySQL")
public String showMySQLResult(
        @RequestParam(value = "filetype", defaultValue = "Medium") String fileType,
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
