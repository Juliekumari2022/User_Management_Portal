package in.learning.portal.dto;

public class UserDto {

	private Integer userId;
	private String email;

	private String password;
	private String pwd_reset_status;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPwd_reset_status() {
		return pwd_reset_status;
	}
	public void setPwd_reset_status(String pwd_reset_status) {
		this.pwd_reset_status = pwd_reset_status;
	}


}
