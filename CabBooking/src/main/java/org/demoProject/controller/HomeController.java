package org.demoProject.controller;

import java.util.List;

import org.demoProject.model.Client;
import org.demoProject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@Autowired
	ClientService clientService;
	
	@GetMapping("/")
	public String index(Model model) {
		
		List<Client> lists = clientService.findAll();
		model.addAttribute("lists", lists);
		System.out.println(lists);
		return "index";
	}
}
