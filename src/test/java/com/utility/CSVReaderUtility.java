package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CSVReaderUtility {

	public static Iterator<User> readCSVFile(String fileName) {
		File csvFile = new File(System.getProperty("user.dir") + File.separator + "testData" + File.separator + fileName);
		List<User> userList = new ArrayList<>();

		try (FileReader fileReader = new FileReader(csvFile); CSVReader csvReader = new CSVReader(fileReader)) {
			csvReader.readNext();

			String[] line;
			while ((line = csvReader.readNext()) != null) {
				if (line.length < 2) {
					continue;
				}
				String emailAddress = line[0].trim();
				String password = line[1].trim();
				if (emailAddress.isEmpty() && password.isEmpty()) {
					continue;
				}
				userList.add(new User(emailAddress, password));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CsvValidationException | IOException e) {
			e.printStackTrace();
		}

		return userList.iterator();
	}

}
