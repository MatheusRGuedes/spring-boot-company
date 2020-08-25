package com.matheusrguedes.curso.boot.web.conversor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/*
 * Classe que será usada pelo spring, quando for enviado um dado do tipo string em que o tipo q referencia a propriedade de classe de contexto for um LocalDate.
 * 
 * Importante -> Essa classe pode ser trocada pela anotação do DateTimeFormatter nas propriedades tipo Localdate.
 * 
 * @Component --> conponente gerenciável do spring mais genérico.
 * */

//@Component
public class StringToLocalDate implements Converter<String, LocalDate> {
	
	@Override
	public LocalDate convert(String data) {
		
		if ("".equals(data)) {
			return null;
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		//pega a data no forato dd/MM/yyyy -> yyyy-MM-dd
		return LocalDate.parse(data, formatter);
	}
}
