package com.pao.availity.exercisesix.model;

public class FileContent implements Comparable<FileContent> {

	private String userId;
	private String firstAndLastName;
	private int version;
	private String insuranceCompany;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstAndLastName() {
		return firstAndLastName;
	}

	public void setFirstAndLastName(String firstAndLastName) {
		this.firstAndLastName = firstAndLastName;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	@Override
	public int compareTo(FileContent o) {
		return o.getVersion() >= this.getVersion() ? 1 : -1;//highest version (Descending)
	}
}
