package org.demoProject.controller;

import org.demoProject.service.SqlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sql")
public class SqlController {
	
	@Autowired
	private SqlServiceImpl sqlServiceImpl;

	@GetMapping("/address")
	public String updateAddress() {
		sqlServiceImpl.updateAddress();
		return "address";
	}
	
//	@GetMapping("/admin")
//	public void updateAdmin() {
//		sqlServiceImpl.updateAdmin();
//	}
}
