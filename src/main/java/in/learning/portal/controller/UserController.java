package in.learning.portal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.learning.portal.dto.LoginFormDto;
import in.learning.portal.dto.QuoteApiResponseDto;
import in.learning.portal.dto.RegisterFormDto;
import in.learning.portal.dto.ResetPwdPageDto;
import in.learning.portal.dto.UserDto;
import in.learning.portal.service.DashboardService;
import in.learning.portal.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService uService;

	@Autowired
	private DashboardService dService;

	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		RegisterFormDto registerForm = new RegisterFormDto();

		model.addAttribute("registerFormObj", registerForm);
		Map<Integer, String> countryMap = uService.getCountries();
		System.out.println(countryMap);
		model.addAttribute("countries", countryMap);
		return "register";
	}

	@GetMapping("/states/{countryId}")
	@ResponseBody
	public Map<Integer, String> getStatesById(@PathVariable Integer countryId, Model model) {

		Map<Integer, String> stateMap = uService.getStates(countryId);

		model.addAttribute("states", stateMap);

		return stateMap;
	}

	@GetMapping("/cities/{stateId}")
	@ResponseBody
	public Map<Integer, String> getCitiesesById(@PathVariable Integer stateId, Model model) {

		Map<Integer, String> cityMap = uService.getCities(stateId);

		model.addAttribute("cities", cityMap);

		return cityMap;
	}

	@PostMapping("/register")
	public String handleRegisterPage(RegisterFormDto registerFormObj, Model model) {
		if (uService.duplicateEmailCheck(registerFormObj.getEmail())) {
			System.out.println(registerFormObj.getEmail());
			model.addAttribute("eMsg", "Duplicate email found, please register with another email..");
		} else {
			if (uService.saveUser(registerFormObj)) {
				model.addAttribute("sMsg", "Successfully registered, password sent over mail for login");
				
			} else
				model.addAttribute("errorMsg", "Invalid data,please register again..");
		}
		RegisterFormDto registerForm = new RegisterFormDto();
		model.addAttribute("registerFormObj", registerForm);
		return "register";
	}

	@GetMapping("/")
	public String getLoginPage(Model model) {
		LoginFormDto loginForm = new LoginFormDto();
		model.addAttribute("loginFormDtoObj", loginForm);

		return "login";
	}

	@GetMapping("/login")
	public String LoginPage(Model model) {
		LoginFormDto loginForm = new LoginFormDto();
		model.addAttribute("loginFormDtoObj", loginForm);

		return "login";
	}
	@PostMapping("/login")
	public String handleLoginPage(@ModelAttribute("loginFormDtoObj") LoginFormDto loginFormDtoObj, Model model) {

		UserDto loginUser = uService.login(loginFormDtoObj);
		if (loginUser == null) {
			
			model.addAttribute("eMsg", "Invalid credentials");
		} else {
			String password = loginUser.getPwd_reset_status();
			if (password.equals("No")) {
				return "redirect:/reset-pwd?email=" + loginUser.getEmail();
			} else {
				return "redirect:/dashboard";
			}
		}
		return "login";
	}

	@GetMapping("/reset-pwd")
	public String getResetPwdPage(@RequestParam String email, Model model) {
		ResetPwdPageDto ResetPwdPageDto = new ResetPwdPageDto();
		ResetPwdPageDto.setEmail(email);
		model.addAttribute("resetObj", ResetPwdPageDto);
		return "resetPassword";
	}

	@PostMapping("/resetPassword")
	public String handleResetPassword(ResetPwdPageDto resetObj, Model model) {
		boolean passCheck = uService.resetPwd(resetObj);
		if (passCheck)
			return "redirect:/dashboard";
		else
			return "resetPassword";
	}

	@GetMapping("/dashboard")
	public String getDashboardPage(Model model) {
		QuoteApiResponseDto quote = dService.getQuote();
		model.addAttribute("dQuote", quote);
		return "dashboard";
	}
}
