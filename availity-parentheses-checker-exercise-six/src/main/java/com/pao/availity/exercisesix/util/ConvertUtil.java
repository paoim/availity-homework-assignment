package com.pao.availity.exercisesix.util;

import com.pao.availity.exercisesix.model.FileContent;

public class ConvertUtil {

	public static boolean isValidArray(String[] cells) {
		return (null != cells && cells.length > 0);
	}

	public static String getData(String[] cells, int index) {
		if (ConvertUtil.isValidArray(cells)) {
			for (int i = 0; i < cells.length; i++) {
				if (i == index) {
					return EntityUtil.toEmpty(cells[i]);
				}
			}
		}
		return "";
	}

	public static int getIntValue(String data) {
		int intValue = 0;
		try {
			intValue = Integer.parseInt(data);
		} catch (Exception e) {
			intValue = 0;
		}
		return intValue;
	}

	public static FileContent convertCells(String[] cells) {
		FileContent fileContent = new FileContent();
		if (ConvertUtil.isValidArray(cells)) {
			fileContent.setUserId(ConvertUtil.getData(cells, 0));
			fileContent.setFirstAndLastName(ConvertUtil.getData(cells, 1));
			fileContent.setVersion(ConvertUtil.getIntValue(ConvertUtil.getData(cells, 2)));
			fileContent.setInsuranceCompany(ConvertUtil.getData(cells, 3));
		}
		return fileContent;
	}
}
