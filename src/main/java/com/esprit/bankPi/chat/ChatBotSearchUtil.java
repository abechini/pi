package com.esprit.bankPi.chat;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;



import com.esprit.bankPi.model.Reclamation;
import com.esprit.bankPi.repository.ReclamationRepository;


import java.sql.*;
import java.util.*;

public class ChatBotSearchUtil {
@Autowired
	 ReclamationRepository reclamationRepository;
	public static ArrayList<String> blockedWords = new ArrayList<>(Arrays.asList("FUCK","MERDE","DAMN"));
	


	    protected final static String INVALID_YEAR_WARNING = "Your input year is invalid!";

	    protected final static String INVALID_QUERY_WARNING = "Invalid query format, please check query rules and retry!";

	    protected final static int MAX_HIT = 100000;

	    public static HashMap<String, String> fileNameMap, tableNameMap, historyResultMap;

	    public static HashMap<String, Double>  MySQLQA;

	    public static HashMap<String, Integer>  MySQLCount;


	    public static Set<Map.Entry<String, String>> entrySet;

	    public static String cacheHistResult;

	    public static double startTime = 0;

	    public static ArrayList<Integer> xCount;

	    public static ArrayList<Double>  yMySQLRunTime;


	    static Connection connection = null;


 
	 

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





//	    /**
//	
//	     *
//	     * @param fileType
//	     * @throws SQLException
//	     */
//
//	    /**
//	     * Parse XML and insert ArticleTitle and PubDate into MySQL
//	     *
//	     * @param fileType
//	     * @throws SQLException
//	     */
//	    public static void MySQLParseXML(String fileType) throws SQLException {
//	        String tableName = tableNameMap.get(fileType);
//	        String fileName = fileNameMap.get(fileType);
//	        String curFilePath = "src/main/resources/data-xml/" + fileName;
//	        PreparedStatement statement = connection.prepareStatement("INSERT INTO " + tableName + "(" + "  Title, solution, date)" + "VALUES(?, ?, ?)");
//	        try {
//	            // xml parse
//	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//	            DocumentBuilder builder = factory.newDocumentBuilder();
//	            org.w3c.dom.Document document = builder.parse(new File(curFilePath));
//	            document.getDocumentElement().normalize();
//	            NodeList nodeList = document.getElementsByTagName("PubmedArticle");
//	            // iterate node list
//	            for (int i = 0; i < nodeList.getLength(); i++) {
//	                Node node = nodeList.item(i);
//	                if (node.getNodeType() == Node.ELEMENT_NODE) {
//	                    Element element = (Element) node;
//	                    List<String> cols = Arrays.asList(element.getElementsByTagName("ArticleTitle").item(0).getTextContent(),
//	                            element.getElementsByTagName("PubDate").item(0).getTextContent());
//	                    for (int paramIndex = 0; paramIndex < cols.size(); paramIndex++) {
//	                        statement.setString(paramIndex + 1, cols.get(paramIndex));
//	                    }
//	                    statement.execute();
//	                }
//	            }
//	        } catch (ParserConfigurationException e) {
//	            e.printStackTrace();
//	        } catch (SAXException e) {
//	            e.printStackTrace();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }

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
	    public static String doQueryMySQL(String fileType, String searchContent,ReclamationRepository repo) throws SQLException {
	        String res = "";
	        System.out.println(searchContent);
	        // Here it needs to de construct the searchContent
	        List<String> words = Arrays.asList(searchContent.toLowerCase().split("\\s+"));
	        String searchWord = words.get(words.indexOf("search") + 1);
	        String searchYear = "";
	        String startYear = "";
	        String endYear = "";
	        String query2 = "";
	        String query1 = "";
	        // reponse automatique pour les blocked words 
	        for(String word:words) {
	        	if(blockedWords.contains(word.toUpperCase()))
	        		return "merci de ne pas utilisé ce type des mots dans l'application";
	        	
	        }
	        
	      
	        // Reclamtion automatique with claim    word
	        if ( searchContent.startsWith("claim")) {
	        	List<String> wordsclaim = Arrays.asList(searchContent.toLowerCase().split("\\s+"));
	        	
	        	if (wordsclaim.size()>0) {
	        		List<String> wordsclaim2 = new ArrayList<>();
	        		for(int i =1 ;i< wordsclaim.size();i++) {
	        			wordsclaim2.add(wordsclaim.get(i));
	        		}
	        		
	        	String claim = StringUtils.join(wordsclaim2," ");
	        	int year = Calendar.getInstance().get(Calendar.YEAR);
	        	
	        	Reclamation reclamation= new Reclamation();
	        	reclamation.setName(claim);
	        	reclamation.setDate(""+year);
	        	repo.save(reclamation);

	        	return "You Reclamation is Created with sucess with name :"+ claim;
	        	}
	        	
	        	
	        }
	        Statement statement = connection.createStatement();
	        System.out.println(Arrays.asList(words));
	        if (words.contains("in")) {
	        	setStartTime();
	            searchYear = words.get(words.indexOf("in") + 1);
	            if (isValidYear(searchYear)) {
	          query1 ="SELECT solution FROM reclamation WHERE Name like '%" + searchWord +
                      "%' AND Date like '" + searchYear + "%'"; 
              ResultSet resultSet2 = statement.executeQuery(query1);    
	                String solutions = "<br>";
	                int count =0;
	                while (resultSet2.next()) {
	                    solutions += "- "+resultSet2.getString(1)+"<br>";
	                    count++;
	                  
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
	                
	                query2 = "SELECT solution FROM reclamation where REGEXP_LIKE(Name, \'" + searchWord + "\') and convert(substring(Date, 1, 4), SIGNED) between " + Integer.parseInt(startYear) + " and " + Integer.parseInt(endYear) + ";";

	                ResultSet resultSet3 = statement.executeQuery(query2);
	                String solutions = "<br>";
	                int count =0;
	                while (resultSet3.next()) {
	                    solutions +="- "+ resultSet3.getString(1)+"<br>";
	                    count++;
	                
	                }
	                double runTime = getRunningTime();
	                
	                res = "from "+startYear+ " to "+endYear+" we found "+count +" reclamations tha have the same name of your problem : ( "+searchWord + " ) here is some solutions that can helps you :"+solutions +"<br>"+". (response time:" + runTime + " ms)";
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
