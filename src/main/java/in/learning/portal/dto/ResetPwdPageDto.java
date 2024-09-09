package in.learning.portal.dto;

public class ResetPwdPageDto {

	private String email;

	private String newPassword;
	private String confirmPassword;

	private String pwd_reset_status;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPwd_reset_status() {
		return pwd_reset_status;
	}

	public void setPwd_reset_status(String pwd_reset_status) {
		this.pwd_reset_status = pwd_reset_status;
	}
	
	
}
