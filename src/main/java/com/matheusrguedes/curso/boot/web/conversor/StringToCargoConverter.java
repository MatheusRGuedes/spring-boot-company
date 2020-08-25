package com.matheusrguedes.curso.boot.web.conversor;

import com.matheusrguedes.curso.boot.domain.Cargo;
import com.matheusrguedes.curso.boot.service.CargoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/*
 * Classe para conversão do id do cargo, vindo para a página de cadastro em direção ao Controller de funcionário;
 * */

@Component
public class StringToCargoConverter implements Converter<String, Cargo> {

	@Autowired
	private CargoService cargoService;
	
	@Override
	public Cargo convert(String text) {
		
		if ("".equals(text)) {
			return null;
		}
		
		Long id = Long.valueOf(text);
		
		return cargoService.buscarPorId(id);
	}
}
