package com.pao.availity.exercisesix.service;

import java.io.InputStream;
import java.io.Writer;
import java.util.Collection;

import com.pao.availity.exercisesix.model.FileContent;

public interface FileContentService {

	Collection<FileContent> getAllOriginalContents();

	Collection<FileContent> getAllCustomContents();

	Collection<FileContent> getByInsuranceCompany(String insuranceCompany);

	Collection<FileContent> uploadCsv(InputStream is, String fileName);

	void writeContentToCsv(Writer writer, String insuranceCompany);
}