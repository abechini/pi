package com.esprit.bankPi.notification;

import java.time.YearMonth;
import java.util.Map;

import com.esprit.bankPi.data.Client;

public class MessageUtility {
	private static String START_TABLE = "<table>";
	private static String END_TABLE = "</table>";
	private static String START_TR = "<tr>";
	private static String END_TR = "</tr>";
	private static String START_Th = "<th>";
	private static String END_Th = "</th>";
	private static String START_Td = "<td>";
	private static String END_Td = "</td>";

	public static String mapToHtml(Map<YearMonth, Double> map,Client client) {
		StringBuilder html = new StringBuilder();
		// create header
		html.append("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<style>\r\n" + "table {\r\n"
				+ "  font-family: arial, sans-serif;\r\n" + "  border-collapse: collapse;\r\n" + "  width: 100%;\r\n"
				+ "}\r\n" + "\r\n" + "td, th {\r\n" + "  border: 1px solid #dddddd;\r\n" + "  text-align: left;\r\n"
				+ "  padding: 8px;\r\n" + "}\r\n" + "\r\n" + "tr:nth-child(even) {\r\n"
				+ "  background-color: #dddddd;\r\n" + "}\r\n" + "</style>\r\n" + "</head>\r\n" + "<body>");
		html.append("<p>Dear our client </p>" + client.getName());
		html.append("<p>We have detected that you used our IA feature : budget managmenet lately to predict your expenses according to our Intelligence</p>");
		html.append("<p>you can find bellow a table describing your savings each month , so you can keep traking</p>");
		html.append(START_TABLE).append(START_TR).append(START_Th).append("year/month").append(END_Th).append(START_Th)
				.append("current balance").append(END_Th).append(START_Th).append("preddicted balance").append(END_Th)
				.append(END_TR);
		// create content
		for (YearMonth ym : map.keySet()) {
			if(map.get(ym.plusMonths(1))!=null) {
				html.append(START_TR).append(START_Th).append(ym).append(END_Th).append(START_Th).append(map.get(ym)).append(END_Th)
						.append(START_Th).append(map.get(ym.plusMonths(1))).append(END_Th).append(END_TR);
			}}
		html.append(END_TABLE);
		html.append("<p>Our team want to thank you for using our application</p>");
		html.append("</body>");
		html.append("</html>");
		return html.toString();
	}
}
