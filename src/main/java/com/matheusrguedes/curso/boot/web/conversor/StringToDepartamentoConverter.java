package com.matheusrguedes.curso.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.matheusrguedes.curso.boot.domain.Departamento;
import com.matheusrguedes.curso.boot.service.DepartamentoService;

/*
 * Classe para conversão de string para objeto Departamento, quando é enviado o valor da propriedade departamento(vem com o id tipo string) em cargo para o controller.
 * Essa classe sempre será chamada quando uma requisição é feita em CargoController;
 * 
 * Converter<String, Departamento> --> permite converção, onde recebe dois tipos genéricos, o primeiro é o tipo de obj q está recebendo da página e o segundo, o tipo para ser retornado;
 * Component					   --> Informa que é um bean gerenciável pelo spring;
 * */

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {

	@Autowired
	private DepartamentoService departamentoService;
	
	@Override
	public Departamento convert(String text) {
		
		if ("".equals(text)) {
			return null;
		}
		
		Long id = Long.valueOf(text);
		
		return departamentoService.buscarPorId(id);
	}
}
