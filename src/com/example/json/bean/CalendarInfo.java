package com.example.json.bean;

public class CalendarInfo {
	private String address,province,district,city;
	

	@Override
	public String toString() {
		return "CalendarInfo [address=" + address + ", province=" + province
				+ ", district=" + district + ", city=" + city + "]";
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
