package in.learning.portal.service;

import java.util.Map;

import in.learning.portal.dto.LoginFormDto;
import in.learning.portal.dto.RegisterFormDto;
import in.learning.portal.dto.ResetPwdPageDto;
import in.learning.portal.dto.UserDto;

public interface UserService {
	
	public Map<Integer,String> getCountries();

	public Map<Integer,String> getStates(Integer countryId);

	public Map<Integer,String> getCities(Integer stateId);

	public boolean duplicateEmailCheck(String email);

	public boolean saveUser(RegisterFormDto regFormDTO);

	public UserDto login(LoginFormDto loginFormDTO);

	public boolean resetPwd(ResetPwdPageDto resetPageDto);

}
