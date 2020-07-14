package com.matheusrguedes.curso.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * / -> Acessa a raiz do projeto simbolizando a página raiz (index)
 * */

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String getHome() {
		return "/home";
	}
}
