package com.matheusrguedes.curso.boot.web.validator;

import java.time.format.DateTimeFormatter;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.matheusrguedes.curso.boot.domain.Funcionario;

/*
 * Classe que serve para fazer validações customizadas;
 * 
 * supports -> é executado primeiro e tem a finalidade de vareficar se o obj é do tipo Funcionário;
 * validate -> será invocado caso o método acima retornar true. Podemos criar nossa própria validação nele;
 * */

public class FuncionarioValidator implements Validator {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Funcionario.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		Funcionario funcionario = (Funcionario) target;
		
		// valida as datas validas;
		if (funcionario.getDataEntrada() != null && funcionario.getDataSaida() != null) {
			
			// dataSaida < dataEntrada
			if (funcionario.getDataSaida().isBefore(funcionario.getDataEntrada())) {
				errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
				return;
			}
		}
	}
}