package com.postulate7.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "Студент ИТ-30018: Коваль Дмитрий Вадимович");
		return "about";
	}

	@GetMapping("/contacts")
	public String contacts(Model model) {
		model.addAttribute("title", "Почта:dimkkov99@gmail.com");
		return "contacts";
	}
}