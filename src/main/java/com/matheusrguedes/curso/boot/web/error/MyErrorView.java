package com.matheusrguedes.curso.boot.web.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/*
 * Classe que irá resolver os erros da view, através da interface ErrorViewResolver;
 *  
 *  resolveErrorView --> Possui as mensagens que são enviadas para a página de erro.html;
 *  
 *  status -> código do erro que ocorreu
 *  map    -> terá as mensagens atuais do erro que ocorreu;
 *  	   -> (timestamp, error, message, status, path);
 *  
 *  
 * */

@Component
public class MyErrorView implements ErrorViewResolver {

	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map) {
		
		map.forEach((k, v) -> System.out.println(k + ":" + v + "\n"));
		
		ModelAndView model = new ModelAndView("error");
		model.addObject("status", status.value());
		
		switch (status.value()) {
			case 404:
				model.addObject("error", "Página não Encontrada.");
				model.addObject("message", "A url para a página '" + map.get("path") + "' não existe.");
				break;
			case 500:
				model.addObject("error", "Ocorreu um erro interno no servidor.");
				model.addObject("message", "Ocorreu um erro inesperado. Tente mais tarde.");
				break;
			default:
				model.addObject("error", map.get("error"));
				model.addObject("message", map.get("message"));
				break;
		}
		
		return model;
	}

}
