package com.matheusrguedes.curso.boot.web.conversor;

import java.math.BigDecimal;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//@Component
public class StringToBigDecimal implements Converter<String, BigDecimal> {

	@Override
	public BigDecimal convert(String valor) {
		
		if ("".equals(valor)) {
			return null;
		}
		
		valor = valor.replace(".", "").replace(",", ".");
		
		BigDecimal valorFormatado = new BigDecimal(valor);
		
		return valorFormatado;
	}
}
