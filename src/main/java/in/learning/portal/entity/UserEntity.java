package in.learning.portal.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	private String email;
	private long phoneNo;

	private String password;

	@ManyToOne
	@JoinColumn(name = "countryId")
	private CountryEntity country;
	@ManyToOne
	@JoinColumn(name = "stateId")
	private StateEntity state;
	@ManyToOne
	@JoinColumn(name = "cityId")
	private CityEntity city;

	private String pwd_reset_status;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createAt;

	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updatedAt;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
	}

	public StateEntity getState() {
		return state;
	}

	public void setState(StateEntity state) {
		this.state = state;
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	public String getPwd_reset_status() {
		return pwd_reset_status;
	}

	public void setPwd_reset_status(String pwd_reset_status) {
		this.pwd_reset_status = pwd_reset_status;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
