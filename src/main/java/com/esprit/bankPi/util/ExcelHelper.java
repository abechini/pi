package com.esprit.bankPi.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.data.DepositPojo;
import com.esprit.bankPi.data.TransactionPojo;
import com.esprit.bankPi.data.TransfertPojo;
import com.esprit.bankPi.model.Deposit;
import com.esprit.bankPi.model.Transaction;

public class ExcelHelper {

	private static final String FAIL_EXPORT = "fail to export data to Excel file: ";
	private static final String FILENAME = "clients.xlsx";
	private static final String FOLDER_EXPORT = "C:\\Users\\gfilali\\Documents\\";
	private static final String EXTRAITFILENAME ="extrait.xlsx";;
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERsEXPORT = { "LastName", "FirstName", "CIN", "BirthDate", "Email", "Sexe", "NumTel", "Adress",
			"CivilState", "Agency" };
	static String[] EXTRAITHEADERS = { "id", "amount", "amountInNumber", "currency", "transactionDate", "NumeroCompte",
			"description", "Npl", "Receiver" };
	static String SHEET = "clients";

	public static boolean hasExcelFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static ByteArrayInputStream FormulairesToExcel(List<Client> clients) {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);
// Header
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERsEXPORT.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERsEXPORT[col]);
			}
			int rowIdx = 1;
			for (Client formulaire : clients) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(formulaire.getName());
				row.createCell(1).setCellValue(formulaire.getFirstName());
				row.createCell(2).setCellValue(formulaire.getCin());
				row.createCell(3).setCellValue(formulaire.getDateOfBirth());
				row.createCell(4).setCellValue(formulaire.getEmail());
				row.createCell(5).setCellValue(formulaire.getSexe().toString());
				row.createCell(6).setCellValue(formulaire.getNumTel());
				row.createCell(7).setCellValue(formulaire.getAddress());
				row.createCell(8).setCellValue(formulaire.getCivilState().toString());
				row.createCell(9).setCellValue(formulaire.getAgency().getName());
			}
			workbook.write(out);
			FileOutputStream out2 = new FileOutputStream(new File(FOLDER_EXPORT + FILENAME));
			workbook.write(out2);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException(FAIL_EXPORT + e.getMessage());
		}
	}

	public static ByteArrayInputStream extrait(List<TransactionPojo> transactions) {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);
// Header
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < EXTRAITHEADERS.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(EXTRAITHEADERS[col]);
			}
			int rowIdx = 1;
			for (TransactionPojo transaction : transactions) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(transaction.getId());
				row.createCell(1).setCellValue(transaction.getAmount());
				row.createCell(2).setCellValue(transaction.getAmount_in_number());
				row.createCell(3).setCellValue(transaction.getCurrency().toString());
				row.createCell(4).setCellValue(transaction.getTransaction_date());
				row.createCell(5).setCellValue(transaction.getCompte().getNumeroCompte());
				if (transaction instanceof TransfertPojo) {
					row.createCell(6).setCellValue(((TransfertPojo) transaction).getDescription());
				}
				if (transaction instanceof TransfertPojo) {
					row.createCell(7).setCellValue(((TransfertPojo) transaction).getNpl());
				}
				if (transaction instanceof TransfertPojo) {
					row.createCell(8).setCellValue(((TransfertPojo) transaction).getReciver());
				}
			}
			workbook.write(out);
			FileOutputStream out2 = new FileOutputStream(new File(FOLDER_EXPORT + EXTRAITFILENAME));
			workbook.write(out2);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException(FAIL_EXPORT + e.getMessage());
		}
	}
}