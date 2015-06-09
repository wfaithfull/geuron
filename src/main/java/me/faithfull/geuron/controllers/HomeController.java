package me.faithfull.geuron.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	String index() {
		return "index";
	}
	
	@RequestMapping("/app")
	String app() {
		return "app";
	}
}
