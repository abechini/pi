package com.esprit.bankPi.chat;


import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.esprit.bankPi.data.Complaint;
import com.esprit.bankPi.resources.ComplaintServiceImpl;
import com.esprit.bankPi.resources.IComplaintService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;
public class ChatBotSearchUtil {
@Autowired
	ComplaintServiceImpl complaintService;
	public static ArrayList<String> blockedWords = new ArrayList<>(Arrays.asList("FUCK","MERDE"));
	public static ArrayList<String> ReclamationblockedWords = new ArrayList<>(Arrays.asList("HELP","ISSUES"));
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

	    public static String cacheHistResult;

	    public static double startTime = 0;

	    public static ArrayList<Integer> xCount;

	    public static ArrayList<Double>  yMySQLRunTime;


	    static Connection connection = null;

int year = Calendar.getInstance().get(Calendar.YEAR);
 Complaint complaint = new Complaint();
 
	 

	    static {
	        /*
	            Initialize global variables: fileNameMap, sqlTableNameMap
	         */
	  
	        tableNameMap = new HashMap<>();
	        tableNameMap.put("reclamation", "reclamation");


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
//	    public static void MySQLInitTable(String fileType) throws SQLException {
//	        String tableName = tableNameMap.get(fileType);
//	        connection.createStatement().execute("CREATE TABLE " + tableName + "(\n"
//	                + "  id integer primary key auto_increment,\n"
//	                + "  Title varchar(1000) not null,\n"
//	                + "  solution varchar(10000) not null,\n"
//	                + "  Date varchar(25) not null\n"
//	                + ")");
//	    }

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
	        // Here it needs to deconstruct the searchContent
	        List<String> words = Arrays.asList(searchContent.toLowerCase().split("\\s+"));
	        String searchWord = words.get(words.indexOf("search") + 1);
	        String searchWordReclamation = words.get(words.indexOf("help") + 1);
	        String searchYear = "";
	        String startYear = "";
	        String endYear = "";
	        String sqlTableName = ChatBotSearchUtil.tableNameMap.get(fileType);
	        String query2 = "";
	        String query1 = "";
	        // reponse automatique pour les blocked woeks 
	        for(String word:words) {
	        	if(blockedWords.contains(word.toUpperCase()))
	        		return "ma9al 7yeek klem zyd fel appliication mta3 wethnni";
	        	
	        }
	     
	        
	        
	        Statement statement = connection.createStatement();
	        System.out.println(Arrays.asList(words));
	        if (words.contains("in")) {
	        	setStartTime();
	            searchYear = words.get(words.indexOf("in") + 1);
//	            cacheHistResult = searchCacheHist(searchWord, searchYear);
//	            if (!cacheHistResult.isEmpty())
//	                return cacheHistResult;
	            if (isValidYear(searchYear)) {
	           
//	                query = "SELECT COUNT(*) FROM " + sqlTableName + " WHERE Name like '%" + searchWord +
//	                        "%' AND Date like '" + searchYear + "%'";
	          query1 ="SELECT solution FROM reclamation WHERE Name like '%" + searchWord +
                      "%' AND Date like '" + searchYear + "%'";
	               // query1="SELECT solution FROM reclamation where Name like Name and date like 2018;";
	             //   ResultSet result= statement.executeQuery(query1);
	             // query1 = "SELECT solution FROM reclamation where Name like Name and date like 2018";
	             
              
             
               
              ResultSet resultSet2 = statement.executeQuery(query1);    


	           //     ResultSet resultSet = statement.executeQuery(query);    
	                
//	                String count = "";
//	                while (resultSet.next())
//	                    count = resultSet.getString(1);
	                String solutions = "<br>";
	                int count =0;
	                while (resultSet2.next()) {
	                    solutions += "- "+resultSet2.getString(1)+"<br>";
	                    count++;
	                
	                }
	                if (count ==0) {
	                	   // reponse automatique pour les mots de reclamation
	        	        for(String word:words) {
	        	        	if(ReclamationblockedWords.contains(word.toUpperCase()))
	        	        		{

return "hani bech nreclami oss bark";}
	        	        		
	        	        	
	        	        }
	                	
	                }
	                double runTime = getRunningTime();
	            
	               
	                
	                res ="in "+searchYear + " we found "+ count + " reclamations  that have the same Name as yours : ( "+searchWord + " ) here is some solutions that can helps you : " + solutions;
	                MySQLQA.put(fileType, runTime);
	                MySQLCount.put(fileType,count);
	                historyResultMap.put(searchContent + " (by MySQL)", res);
	                return res;
	            } else
	                return INVALID_YEAR_WARNING;
	        } else if (words.contains("from") && words.contains("to")) {
	            // query by year range
	            startYear = words.get(words.indexOf("from") + 1);
	            endYear = words.get(words.indexOf("to") + 1);
	            setStartTime();
	            cacheHistResult = searchCacheHist(searchWord, startYear, endYear);
	            if (!cacheHistResult.isEmpty())
	                return cacheHistResult;
	            if (isValidYear(startYear) && isValidYear(endYear)) {
	                //query = "SELECT COUNT(*) FROM " + sqlTableName +
	                  //      " where REGEXP_LIKE(Name, \'" + searchWord + "\') and convert(substring(Date, 1, 4), SIGNED) between " + Integer.parseInt(startYear) + " and " + Integer.parseInt(endYear) + ";";
	                query2 = "SELECT solution FROM reclamation where REGEXP_LIKE(Name, \'" + searchWord + "\') and convert(substring(Date, 1, 4), SIGNED) between " + Integer.parseInt(startYear) + " and " + Integer.parseInt(endYear) + ";";
	               // System.out.println(query);
	               // ResultSet resultSet = statement.executeQuery(query);
	                ResultSet resultSet3 = statement.executeQuery(query2);
	                String solutions = "<br>";
	                int count =0;
	                while (resultSet3.next()) {
	                    solutions +="- "+ resultSet3.getString(1)+"<br>";
	                    count++;
	                
	                }
	                double runTime = getRunningTime();
	                
	                res = "from "+startYear+ " to "+endYear+" we found "+count +" reclamations tha have the same name of your problem :"+searchWord + " here is some solutions that can helps you :"+solutions +"<br>"+". (response time:" + runTime + " ms)";
	                MySQLQA.put(fileType, runTime);
	                MySQLCount.put(fileType, count);
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
