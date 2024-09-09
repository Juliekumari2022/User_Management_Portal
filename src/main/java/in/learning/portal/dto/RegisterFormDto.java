package in.learning.portal.dto;

public class RegisterFormDto {

	//private Integer userId;
	private String userName;
	private String email;
	private long phoneNo;

	private String password;

	private Integer countryId;

	private Integer stateId;

	private Integer cityId;

	private String pwd_reset_status;

	/*
	 * public Integer getUserId() { return userId; }
	 * 
	 * public void setUserId(Integer userId) { this.userId = userId; }
	 */

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getPwd_reset_status() {
		return pwd_reset_status;
	}

	public void setPwd_reset_status(String pwd_reset_status) {
		this.pwd_reset_status = pwd_reset_status;
	}

}
