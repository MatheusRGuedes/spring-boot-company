package com.matheusrguedes.curso.boot.web.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Classe que substituirá o controlador padrão do spring boot para erros, através da interface ErrorController.
 * 
 * -> Não é necessário criá-lo, pois é usado apenas para lógicas mais personalizadas.
 * 
 * getErrorPath() -> irá retornar um caminho personalizado a ser requisitado quando ocorrer um erro;
 * handleError()  -> mapeado para pegar a requisição e retornar a página de erro renderizada.
 * 
 * */

//@Controller (não é executada)
public class MyErrorController implements ErrorController {

	@RequestMapping("/error")
	private String handleError(HttpServletRequest request, ModelMap model) {
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		Object message = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		
		if (status != null) {
			
			Integer statusCode = Integer.valueOf(status.toString());
			
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "error-404";
			}
			
			else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				String errorMessage = message.toString();
				model.addAttribute("message", errorMessage.split(";")[1]);
				model.addAttribute("status", statusCode);
				return "error-500";
			}
		}
		
		return "error";
	}
	
	@Override
	public String getErrorPath() {
		return null;
	}
}