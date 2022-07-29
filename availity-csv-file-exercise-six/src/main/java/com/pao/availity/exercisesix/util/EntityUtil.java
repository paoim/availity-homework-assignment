package com.pao.availity.exercisesix.util;

import java.util.List;

public class EntityUtil {

	public static <T> boolean isNullEntity(T entity) {

		return entity == null;
	}

	public static <T> boolean isNotNullEntity(T entity) {

		return entity != null;
	}

	public static <T> boolean isEmpltyList(List<T> entities) {

		return isNotNullEntity(entities) && (entities.isEmpty() || entities.size() == 0);
	}

	public static <T> boolean isValidList(List<T> entities) {

		return !isEmpltyList(entities);
	}

	public static boolean isValidString(String str) {

		return (str != null && str.length() > 0);
	}

	public static String toEmpty(String str) {
		if (isValidString(str)) {
			String data = str.trim();
			return isValidString(data) ? data : "";
		}
		return "";
	}

	public static String makeString(String str) {
		return "'" + str + "'";
	}

	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (isValidString(s1) && isValidString(s2)) {
			return s1.equalsIgnoreCase(s2);
		}
		return false;
	}
}