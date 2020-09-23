package com.matheusrguedes.curso.boot.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * Classe que substituirá o controlador padrão do spring boot para erros, através da interface ErrorController.
 * 
 * -> Não é necessário criá-lo, pois é usado apenas para lógicas mais personalizadas.
 * 
 * getErrorPath() -> irá retornar um caminho personalizado a ser requisitado quando ocorrer um erro;
 * handleError()  -> mapeado para pegar a requisição e retornar a página de erro renderizada.
 * 
 * */

//@Controller
public class MyErrorController implements ErrorController {

	@RequestMapping( path = "${server.error.path}", method = RequestMethod.GET)
	private String handleError(HttpServletRequest request, ModelMap model) {		
		return "error";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}
}