package in.learning.portal.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.learning.portal.dto.LoginFormDto;
import in.learning.portal.dto.RegisterFormDto;
import in.learning.portal.dto.ResetPwdPageDto;
import in.learning.portal.dto.UserDto;
import in.learning.portal.entity.CityEntity;
import in.learning.portal.entity.CountryEntity;
import in.learning.portal.entity.StateEntity;
import in.learning.portal.entity.UserEntity;
import in.learning.portal.repository.CityRepository;
import in.learning.portal.repository.CountryRepository;
import in.learning.portal.repository.StateRepository;
import in.learning.portal.repository.UserRepository;
import in.learning.portal.service.EmailService;
import in.learning.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private CountryRepository countryRepo;
	private StateRepository stateRepo;
	private CityRepository cityRepo;
	private UserRepository userRepo;

	@Autowired
	private EmailService emailService;

	public UserServiceImpl(CountryRepository countryRepo, StateRepository stateRepo, CityRepository cityRepo,
			UserRepository userRepo) {
		this.countryRepo = countryRepo;
		this.stateRepo = stateRepo;
		this.cityRepo = cityRepo;
		this.userRepo = userRepo;
	}

	@Override
	public Map<Integer, String> getCountries() {
		List<CountryEntity> countryList = countryRepo.findAll();
		Map<Integer, String> countryMap = new HashMap<>();
		countryList.forEach(c -> {
			countryMap.put(c.getCountryId(), c.getCountryName());
		});
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<StateEntity> stateList = stateRepo.findByCountryId(countryId);
		Map<Integer, String> stateMap = new HashMap<>();
		stateList.forEach(s -> {
			stateMap.put(s.getStateId(), s.getStateName());
		});
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<CityEntity> cityList = cityRepo.findByStateId(stateId);
		Map<Integer, String> cityMap = new HashMap<>();
		cityList.forEach(city -> {
			cityMap.put(city.getCityId(), city.getCityName());
		});
		return cityMap;
	}

	@Override
	public boolean duplicateEmailCheck(String email) {
		UserEntity findByEmail = userRepo.findByEmail(email);
		if (findByEmail != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean saveUser(RegisterFormDto regFormDTO) {
		UserEntity user = new UserEntity();
		BeanUtils.copyProperties(regFormDTO, user);
		CountryEntity country = countryRepo.findById(regFormDTO.getCountryId()).orElse(null);
		if (country != null) {
			System.out.println("Country ID: " + country.getCountryId());
			user.setCountry(country);
		} else {
			System.out.println("Country not found for ID: " + regFormDTO.getCountryId());
		}

		StateEntity state = stateRepo.findById(regFormDTO.getStateId()).orElse(null);
		user.setState(state);

		CityEntity city = cityRepo.findById(regFormDTO.getCityId()).orElse(null);
		user.setCity(city);

		// Generate a random password
		String password = generateRandomPassword();
		user.setPassword(password);
		user.setPwd_reset_status("No");

		// Save the user entity
		UserEntity savedUser = userRepo.save(user);

		// Check if the user was saved successfully
		System.out.println("Saved User ID: " + savedUser.getUserId());
		if (savedUser.getUserId() != null) {
			String to = regFormDTO.getEmail();
			String subject = "Your account is created successfully";
			String body = "Your password is " + password;
			emailService.sendMail(to, subject, body);
			return true;
		}
		return false;
	}

	private String generateRandomPassword() {
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer passBuffer = new StringBuffer();
		for (int i = 0; i < 5; i++) {
			int randomIndex = random.nextInt(letters.length());
			passBuffer.append(letters.charAt(randomIndex));
		}
		return passBuffer.toString();
	}

	@Override
	public UserDto login(LoginFormDto loginFormDTO) {
		UserEntity loginUser = userRepo.findByEmailAndPassword(loginFormDTO.getEmail(), loginFormDTO.getPassword());

		if (loginUser != null) {
			UserDto userDtoObj = new UserDto();
			BeanUtils.copyProperties(loginUser, userDtoObj);
			return userDtoObj;
		}
		return null;
	}

	@Override
	public boolean resetPwd(ResetPwdPageDto resetPageDto) {
		String email=resetPageDto.getEmail();
		System.out.println("Email passed is " + email);
		UserEntity user = userRepo.findByEmail(email);
		System.out.println(user.getEmail());
		if (user != null) {
			user.setPassword(resetPageDto.getConfirmPassword());
			user.setPwd_reset_status("Yes");
			userRepo.save(user);
			return true;
		}
		return false;
	}

}
