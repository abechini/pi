package com.esprit.bankPi.chat;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.util.*;
public class ChatBotSearchUtil {
	 public enum KEYWORD {
	        MYSQL
	    }


	    protected final static String INVALID_YEAR_WARNING = "Your input year is invalid!";

	    protected final static String INVALID_QUERY_WARNING = "Invalid query format, please check query rules and retry!";

	    protected final static int MAX_HIT = 100000;

	    public static HashMap<String, String> fileNameMap, tableNameMap, historyResultMap, BruteForceQA, LuceneQA;

	    public static HashMap<String, Double>  MySQLQA;

	    public static HashMap<String, Integer>  MySQLCount;


	    public static Set<Map.Entry<String, String>> entrySet;

//	    public static String cacheHistResult;

	    public static double startTime = 0;

	    public static ArrayList<Integer> xCount;

	    public static ArrayList<Double>  yMySQLRunTime;


	    static Connection connection = null;

	 

	    static {
	        /*
	            Initialize global variables: fileNameMap, sqlTableNameMap
	         */
	  
	        tableNameMap = new HashMap<>();
	        tableNameMap.put("Small", "Article1");
	        tableNameMap.put("Medium", "Article2");
	        tableNameMap.put("Large", "Article3");

	        MySQLQA = new HashMap<>();
	        MySQLCount = new HashMap<>();


	        /*
	            in historyResultMap, we need to store <key, value> as <userInput, queryResult>;
	         */
	        historyResultMap = new HashMap<>();
	        entrySet = historyResultMap.entrySet();

	        /*
	            Initialize xCount, yTimeSeries for plotting graphs with x-axis and y-axis
	         */
	        xCount = new ArrayList<>();
	        yMySQLRunTime = new ArrayList<>();
	    }

	    static {
	        try {
	            connection = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/bankPi?useTimezone=true&serverTimezone=UTC",
	                    "root",
	                    "root"
	            );
	        } catch (SQLException ex) {
	            System.err.println("Driver not found: " + ex.getMessage());
	        }
	    }


	    /**
	     * @param year
	     * @return if the string is a valid year
	     */
	    public static boolean isValidYear(String year) {
	        if (year.length() == 4)
	            return true;
	        else
	            return false;
	    }

	    private static void setStartTime() {
	        startTime = System.currentTimeMillis();
	    }

	    private static double getRunningTime() {
	        return System.currentTimeMillis() - startTime;
	    }





	    /**
	     * Init MySQL by creating tables for storing Pubmed articles
	     *
	     * @param fileType
	     * @throws SQLException
	     */
	    public static void MySQLInitTable(String fileType) throws SQLException {
	        String tableName = tableNameMap.get(fileType);
	        connection.createStatement().execute("CREATE TABLE " + tableName + "(\n"
	                + "  id integer primary key auto_increment,\n"
	                + "  Title varchar(1000) not null,\n"
	                + "  solution varchar(10000) not null,\n"
	                + "  Date varchar(25) not null\n"
	                + ")");
	    }

	    /**
	     * Parse XML and insert ArticleTitle and PubDate into MySQL
	     *
	     * @param fileType
	     * @throws SQLException
	     */
	    public static void MySQLParseXML(String fileType) throws SQLException {
	        String tableName = tableNameMap.get(fileType);
	        String fileName = fileNameMap.get(fileType);
	        String curFilePath = "src/main/resources/data-xml/" + fileName;
	        PreparedStatement statement = connection.prepareStatement("INSERT INTO " + tableName + "(" + "  Title, solution, date)" + "VALUES(?, ?, ?)");
	        try {
	            // xml parse
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            org.w3c.dom.Document document = builder.parse(new File(curFilePath));
	            document.getDocumentElement().normalize();
	            NodeList nodeList = document.getElementsByTagName("PubmedArticle");
	            // iterate node list
	            for (int i = 0; i < nodeList.getLength(); i++) {
	                Node node = nodeList.item(i);
	                if (node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element element = (Element) node;
	                    List<String> cols = Arrays.asList(element.getElementsByTagName("ArticleTitle").item(0).getTextContent(),
	                            element.getElementsByTagName("PubDate").item(0).getTextContent());
	                    for (int paramIndex = 0; paramIndex < cols.size(); paramIndex++) {
	                        statement.setString(paramIndex + 1, cols.get(paramIndex));
	                    }
	                    statement.execute();
	                }
	            }
	        } catch (ParserConfigurationException e) {
	            e.printStackTrace();
	        } catch (SAXException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    /*
	        Do Query by MySQL
	     */

	    /**
	     * This method is implemented to do main query work using MySQL
	     *
	     * @param fileType
	     * @param searchContent
	     * @return the result of query from user input
	     * @throws SQLException
	     */
	    public static String doQueryMySQL(String fileType, String searchContent) throws SQLException {
	        String res = "";
	        System.out.println(fileType);
	        System.out.println(searchContent);
	        // Here it needs to deconstruct the searchContent.
	        List<String> words = Arrays.asList(searchContent.toLowerCase().split("\\s+"));
	        String searchWord = words.get(words.indexOf("search") + 1);
	        String searchYear = "";
	        String startYear = "";
	        String endYear = "";
	        String solution ="test solution";
	        String sqlTableName = ChatBotSearchUtil.tableNameMap.get(fileType);
	        String query = "";
	        String query1 = "";
	        
	        Statement statement = connection.createStatement();
	        System.out.println(Arrays.asList(words));
	        if (words.contains("in")) {
	            // query by year
	            setStartTime();
	            searchYear = words.get(words.indexOf("in") + 1);
//	            cacheHistResult = searchCacheHist(searchWord, searchYear);
//	            if (!cacheHistResult.isEmpty())
//	                return cacheHistResult;
	            if (isValidYear(searchYear)) {
	                query = "SELECT COUNT(*) FROM " + sqlTableName + " WHERE Title like '%" + searchWord +
	                        "%' AND Date like '" + searchYear + "%'";
	                ResultSet resultSet = statement.executeQuery(query);
	                query1 = "SELECT SOLUTION FROM " + sqlTableName;
//	                ResultSet resultSet1 = statement.executeQuery(query1);
	//
//	                while (resultSet1.next())
//	                    solution = resultSet1.getString(1);
//	            
	                String count = "";
	                while (resultSet.next())
	                    count = resultSet.getString(1);
	            
	                double runTime = getRunningTime();
	            
	                res = "The total counts in year " + searchYear + " with word: " 
	                + searchWord + " is " + count  + "  " + "the solution is:  "+solution;
	                MySQLQA.put(fileType, runTime);
	                MySQLCount.put(fileType, Integer.parseInt(count));
	                historyResultMap.put(searchContent + " (by MySQL)", res);
	                return res;
	            } else
	                return INVALID_YEAR_WARNING;
	        } else if (words.contains("from") && words.contains("to")) {
	            // query by year range
	            startYear = words.get(words.indexOf("from") + 1);
	            endYear = words.get(words.indexOf("to") + 1);
	            setStartTime();
//	            cacheHistResult = searchCacheHist(searchWord, startYear, endYear);
//	            if (!cacheHistResult.isEmpty())
//	                return cacheHistResult;
	            if (isValidYear(startYear) && isValidYear(endYear)) {
	                query = "SELECT COUNT(*) FROM " + sqlTableName +
	                        " where REGEXP_LIKE(Title, \'" + searchWord + "\') and convert(substring(Date, 1, 4), SIGNED) between " + Integer.parseInt(startYear) + " and " + Integer.parseInt(endYear) + ";";
	                System.out.println(query);
	                ResultSet resultSet = statement.executeQuery(query);
	                String count = "";
	                while (resultSet.next())
	                    count = resultSet.getString(1);
	                double runTime = getRunningTime();
	                res = "Articles count from year " + startYear + " to year " + endYear + " with word: " + searchWord + " is " + count + ". (response time:" + runTime + " ms)"  + "  "+ "the solution is: "+solution;
	                MySQLQA.put(fileType, runTime);
	                MySQLCount.put(fileType, Integer.parseInt(count));
	                historyResultMap.put(searchContent+ " (by MySQL)", res);
	                return res;
	            } else {
	                return INVALID_YEAR_WARNING;
	            }
	        }
	        return INVALID_QUERY_WARNING;
	    }

	    

	    

	    public static String printHistory() {
	        String res = "";
	        for (Map.Entry<String, String> history : entrySet)
	            res += "- Asked: " + history.getKey() + "<br>" + "- Answered: " + history.getValue() + "<br><br>";
	        return res;
	    }

	    public static String searchCacheHist(String searchWord, String searchYear) {
	        for (Map.Entry<String, String> history : entrySet) {
	            if (history.getKey().contains(searchWord) && history.getKey().contains(searchYear))
	                return history.getValue();
	        }
	        return "";
	    }

	    public static String searchCacheHist(String searchWord, String startYear, String endYear) {
	        for (Map.Entry<String, String> history : entrySet) {
	            if (history.getKey().contains(searchWord) && history.getKey().contains(startYear) && history.getKey().contains(endYear))
	                return history.getValue();
	        }
	        return "";
	    }

	  
	   
	

}
