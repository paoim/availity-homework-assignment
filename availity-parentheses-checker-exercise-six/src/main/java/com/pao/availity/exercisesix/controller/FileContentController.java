package com.pao.availity.exercisesix.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pao.availity.exercisesix.model.FileContent;
import com.pao.availity.exercisesix.service.FileContentService;
import com.pao.availity.exercisesix.util.DateFormatUtil;
import com.pao.availity.exercisesix.util.FileUtil;

@RestController
@RequestMapping("/api/content/")
public class FileContentController {

	private static final Logger log = LoggerFactory.getLogger(FileContentController.class);

	@Autowired
	private FileContentService fileContentService;

	@RequestMapping(value = "origin",
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE })
	public ResponseEntity<Collection<FileContent>> getAllOriginalContents() {
		Collection<FileContent> fileContentList = fileContentService.getAllOriginalContents();
		return new ResponseEntity<Collection<FileContent>>(fileContentList, HttpStatus.OK);
	}

	@RequestMapping(value = "custom",
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE })
	public ResponseEntity<Collection<FileContent>> getAllCustomContents() {
		Collection<FileContent> fileContentList = fileContentService.getAllCustomContents();
		return new ResponseEntity<Collection<FileContent>>(fileContentList, HttpStatus.OK);
	}

	@RequestMapping(
			value = "{ic}",
			method = RequestMethod.GET
	)
	public void downloadCsv(HttpServletResponse servletResponse, @PathVariable("ic") String insuranceCompany) throws IOException {
		servletResponse.setContentType("text/csv");
		String fileName = "availity-" + insuranceCompany + "-" + DateFormatUtil.getMMDDYYYYHHMMSSADate();
		servletResponse.addHeader("Content-Disposition", "attachment; filename=\""+fileName.replace(' ', '-')+".csv\"");
		fileContentService.writeContentToCsv(servletResponse.getWriter(), insuranceCompany);
	}

	@RequestMapping(value = "upload",
			method = RequestMethod.POST,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE })
	public ResponseEntity<Collection<FileContent>> uploadCsv(
			@RequestParam("file") MultipartFile file,
			@RequestParam("name") String fileName) {
		log.info(">> Start to uploadCsv...");
		InputStream is = FileUtil.getInputStream(file);
		if (is == null) {
			return new ResponseEntity<Collection<FileContent>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("FileName: " + fileName);
		Collection<FileContent> fileContents = fileContentService.uploadCsv(is, fileName);
		log.info(">> End to uploadCsv...");
		return new ResponseEntity<Collection<FileContent>>(fileContents, HttpStatus.OK);
	}
}
