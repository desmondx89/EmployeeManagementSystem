package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationController {
//    @Autowired
//    private TokenStore tokenStore;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/return")
	public String returnToHome() {
		return "redirect:";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

//	@RequestMapping("/logout")
//	public String logout(HttpServletRequest request, HttpServletResponse response) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (auth != null) {
//			new SecurityContextLogoutHandler().logout(request, response, auth);
//		}
//		return "redirect:/login";
//	}

	@RequestMapping("/logout")
    public String logout(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response){
        System.out.println("logout");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "redirect:login";
    }



//	@GetMapping("/logout")
//	public String logout() {
//		return "logout";
//	}

}
