package com.pao.availity.exercisesix.service;

import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.pao.availity.exercisesix.model.FileContent;
import com.pao.availity.exercisesix.model.FirstAndLastNameComparator;
import com.pao.availity.exercisesix.util.EntityUtil;
import com.pao.availity.exercisesix.util.FileUtil;

@Service
public class FileContentServiceImpl implements FileContentService {

	private InputStream resourceInputStream;
	private static List<FileContent> fileContentList;
	private static final Logger log = LoggerFactory.getLogger(FileContentServiceImpl.class);

	private static void setFileContentList() {
		if (EntityUtil.isValidList(fileContentList)) {
			fileContentList = null;
		}
	}

	private static List<FileContent> save(List<FileContent> fileContents) {
		fileContentList = fileContents;
		return fileContents;
	}

	@Autowired
	private ResourceLoader resourceLoader;

	@PostConstruct
	public void init() {
		log.info(">> Start to load Data from availity.csv");
		Resource resource = resourceLoader.getResource("classpath:availity.csv");
		resourceInputStream = FileUtil.getResourceFileInputStream(resource);
		if (resourceInputStream != null) {
			loadDataFromCsv(resourceInputStream);
		}
	}
	
	@PreDestroy
	public void preDestroy() {
		if (resourceInputStream != null) {
			try {
				resourceInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		}
	}

	@Override
	public Collection<FileContent> getAllOriginalContents() {
		return (null != fileContentList) ? fileContentList : new ArrayList<FileContent>();
	}

	@Override
	public Collection<FileContent> getAllCustomContents() {
		List<FileContent> fileContents = new ArrayList<FileContent>();
		Map<String, List<FileContent>> groupByInsuranceCompanyMap = groupContentByInsuranceCompanyWithJava8();
		for (String insuranceCompany : groupByInsuranceCompanyMap.keySet()) {
			List<FileContent> smallFileContents = this.getAllHighestVersionWithSameUserIdWithJava8(insuranceCompany);
			if (!smallFileContents.isEmpty()) {
				this.sortContentsByLastAndFirstNameAssending(smallFileContents);
				fileContents.addAll(smallFileContents);
			}
		}
		return fileContents;
	}

	@Override
	public Collection<FileContent> getByInsuranceCompany(String insuranceCompany) {
		return this.getAllHighestVersionWithSameUserId(insuranceCompany);
	}

	@Override
	public Collection<FileContent> uploadCsv(InputStream is, String fileName) {
		log.info(">>Start on uploadCsv...");
		loadDataFromCsv(is);
		Collection<FileContent> fileContents = getAllCustomContents();
		log.info(">>End on uploadCsv...");
		return fileContents;
	}

	@Override
	public void writeContentToCsv(Writer writer, String insuranceCompany) {
		List<FileContent> fileContents = this.getAllHighestVersionWithSameUserId(insuranceCompany);
		if (EntityUtil.isValidList(fileContents)) {
			this.sortContentsByLastAndFirstNameAssending(fileContents);
			FileUtil.writeContentToCsv(writer, fileContents);
		}
	}

	private void loadDataFromCsv(InputStream is) {
		List<FileContent> fileContents = FileUtil.readInputStreamCsv(is);
		if (EntityUtil.isValidList(fileContents)) {
			setFileContentList();//Re-set it to null when get new contents from CSV file by upload from UI or default CSV file
			save(fileContents);
		}
	}

	private void sortContentsByLastAndFirstNameAssending(List<FileContent> fileContents) {
		if (!fileContents.isEmpty()) {
			Collections.sort(fileContents, new FirstAndLastNameComparator());//sort the contents of each file by last and first name (ascending)
		}
	}

	private Map<String, List<FileContent>> groupContentByInsuranceCompanyWithJava8() {
		Collection<FileContent> fileContents = getAllOriginalContents();
		return fileContents.stream().collect(Collectors.groupingBy(FileContent::getInsuranceCompany));
	}

	private Map<String, FileContent> groupByUserIdWithHighestVersion(List<FileContent> fileContents) {
		Map<String, FileContent> groupByUserIdWithHighestVersionMap = fileContents.stream().collect(Collectors.groupingBy(FileContent::getUserId, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(FileContent::getVersion)), Optional::get)));
		return groupByUserIdWithHighestVersionMap;
	}

	private List<FileContent> getAllHighestVersionWithSameUserIdWithJava8(String insuranceCompany) {
		List<FileContent> fileContents = new ArrayList<FileContent>();
		Map<String, List<FileContent>> groupByInsuranceCompanyMap = groupContentByInsuranceCompanyWithJava8();
		if (null != groupByInsuranceCompanyMap.get(insuranceCompany)) {
			Map<String, FileContent> groupByUserIdWithHighestVersionMap = groupByUserIdWithHighestVersion(groupByInsuranceCompanyMap.get(insuranceCompany));
			for (String userId : groupByUserIdWithHighestVersionMap.keySet()) {
				fileContents.add(groupByUserIdWithHighestVersionMap.get(userId));
			}
		}
		return fileContents;
	}

	private Map<String, List<FileContent>> groupContentByInsuranceCompany() {
		Map<String, List<FileContent>> map = new HashMap<>();
		Collection<FileContent> fileContents = getAllOriginalContents();
		for (FileContent fileContent : fileContents) {
			if (null == map.get(fileContent.getInsuranceCompany())) {
				List<FileContent> tmpList = new ArrayList<FileContent>();
				tmpList.add(fileContent);
				map.put(fileContent.getInsuranceCompany(), tmpList);
			} else {
				map.get(fileContent.getInsuranceCompany()).add(fileContent);
			}
		}
		return map;
	}

	private List<FileContent> getAllHighestVersionWithSameUserId(String insuranceCompany) {
		List<FileContent> fileContents = new ArrayList<FileContent>();
		Map<String, List<FileContent>> groupByInsuranceCompanyMap = groupContentByInsuranceCompany();
		if (null != groupByInsuranceCompanyMap.get(insuranceCompany)) {
			Map<String, List<FileContent>> groupByUserIdMap = new HashMap<>();
			for (FileContent fileContent : groupByInsuranceCompanyMap.get(insuranceCompany)) {
				if (null == groupByUserIdMap.get(fileContent.getUserId())) {
					List<FileContent> tmpList = new ArrayList<FileContent>();
					tmpList.add(fileContent);
					groupByUserIdMap.put(fileContent.getUserId(), tmpList);
				} else {
					groupByUserIdMap.get(fileContent.getUserId()).add(fileContent);
				}
			}
			for (String userId : groupByUserIdMap.keySet()) {
				List<FileContent> tmpList = groupByUserIdMap.get(userId);
				if (EntityUtil.isValidList(tmpList)) {
					Collections.sort(tmpList);//Sort By highest version
					fileContents.add(tmpList.get(0));//Just get highest version
				}
			}
		}
		return fileContents;
	}
}
