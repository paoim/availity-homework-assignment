package com.pao.availity.exercisesix.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.pao.availity.exercisesix.model.FileContent;

public class FileUtil {

	private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

	public static List<FileContent> readInputStreamCsv(InputStream in) {
		String line = "";
		String cvsSplitBy = ",";
		BufferedReader br = null;
		boolean isSkipFirstLine = true;
		List<FileContent> fileContents = new ArrayList<FileContent>();
		try {
			br = getBufferedReader(in);
			while ((line = br.readLine()) != null) {
				if (!isSkipFirstLine) {
					String[] cells = line.split(cvsSplitBy);
					FileContent fileContent = ConvertUtil.convertCells(cells);
					fileContents.add(fileContent);
				} else {
					isSkipFirstLine = false;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return fileContents;
	}

	public static BufferedReader getBufferedReader(InputStream in) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(in));
		} catch (Exception e) {
			br = null;
			e.printStackTrace();
		}
		return br;
	}

	public static InputStream getResourceFileInputStream(Resource resource) {
		log.info(">> Start on getResourceFileInputStream...");
		InputStream in = null;
		try {
			in = resource.getInputStream();
			log.info("Resource InputStream is OK to go!");
		} catch (FileNotFoundException e) {
			in = null;
			log.error(e.getMessage());
		} catch (Exception e) {
			in = null;
			log.error(e.getMessage());
		}
		log.info(">> End getResourceFileInputStream...");
		return in;
	}

	public static InputStream getInputStream(MultipartFile file) {
		log.info(">> Start on getInputStream...");
		InputStream in = null;
		try {
			in = file.getInputStream();
			log.info("MultipartFile is OK to go!");
		} catch (FileNotFoundException e) {
			in = null;
			log.error(e.getMessage());
		} catch (Exception e) {
			in = null;
			log.error(e.getMessage());
		}
		log.info(">> End on getInputStream...");
		return in;
	}

	public static void writeContentToCsv(Writer writer, List<FileContent> fileContents) {
		log.info(">> Start on writeContentToCsv...");
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			//Add Header to CSV File
			csvPrinter.printRecord("User Id", "First and Last Name", "Version", "Insurance Company");

			//Add Contents to CSV File
			for (FileContent fileContent : fileContents) {
				csvPrinter.printRecord(fileContent.getUserId(), fileContent.getFirstAndLastName(), fileContent.getVersion(), fileContent.getInsuranceCompany());
			}
			log.info("writeContentToCsv is OK to go!");
		} catch (IOException e) {
			log.error("Error While writing CSV ", e);
		}
		log.info(">> End on writeContentToCsv...");
	}
}
