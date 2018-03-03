package com.sushan.onlinebank.com.sushan.onlinebank.controller;

import com.sushan.onlinebank.com.sushan.onlinebank.DAO.RoleDao;
import com.sushan.onlinebank.com.sushan.onlinebank.domain.PrimaryAccount;
import com.sushan.onlinebank.com.sushan.onlinebank.domain.SavingsAccount;
import com.sushan.onlinebank.com.sushan.onlinebank.domain.User;
import com.sushan.onlinebank.com.sushan.onlinebank.domain.security.UserRole;
import com.sushan.onlinebank.com.sushan.onlinebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	@Autowired
	RoleDao roleDao;

	@RequestMapping("/")
	public String home(){
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	@RequestMapping("/signup")
	public String signup(Model model){
		User user = new User();
		model.addAttribute("user", user);
		return "signup";
	}

	@RequestMapping(value= "/signup", method = RequestMethod.POST)
	public String signupPost(User user, Model model){
		if(userService.checkUserExists(user.getUsername(), user.getEmail())){
			if(userService.checkEmailExists(user.getEmail())){
				model.addAttribute("emailExists", true);
			}
			if(userService.checkUsernameExists(user.getUsername()))
					model.addAttribute("usernameExists", true);
			return "signup";
		}
		else{
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(user, roleDao.findByName("ROLE_ USER")));
			userService.createUser(user, userRoles);
			return "redirect:/";
		}
	}

	@RequestMapping("/userFront")
	public String userFront(Principal principal, Model model){
		User user = userService.findByUsername(principal.getName());
		PrimaryAccount primaryAccount = user.getPrimaryAccount();
		SavingsAccount savingsAccount = user.getSavingsAccount();
		model.addAttribute("primaryAccount", primaryAccount);
		model.addAttribute("savingsAccount", savingsAccount);
		return "userFront";
	}
}
