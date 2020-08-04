package com.matheusrguedes.curso.boot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.matheusrguedes.curso.boot.domain.Departamento;
import com.matheusrguedes.curso.boot.service.DepartamentoService;

/* Dispatcher Servlet -> Um servlet container que tem como objetivo de pegar a uri da requisição do navegador e encontrar a combinação certa para o método do controlador e a página
 * 						 de resposta e gera o html.
 * 
 * Quando trabalhamos com java web usando Spring MVC, o padrão de retorno de cada Request é uma String que será tratada como Forward.
 * Então, se trata de um redirecionamento forward.
 * 
 * https://medium.com/code-maestro/spring-redirect-vs-forward-5cee2c748fb6
 * 
 * redirect -> redireciona novamente para a página, impedindo uma requisição dupla para o mesmo método(caso uma pessoa atualizasse a página para super popular o bd)
 * 			-> Além de não repassar o objeto do primeiro request, não corre o risco de repetir requisições na atualização da página.
 * 
 * ModelMap -> Classe que serve para tranferir dados para renderizar uma view (algum template engine) através do controlador. Trata os dados como se estivessem num Map;
 * 
 * */

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/cadastrar")
	public String cadastro(Departamento departamento) {
		return "/departamento/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(Departamento departamento) {
		
		departamentoService.salvar(departamento);
		
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		List<Departamento> lista = departamentoService.buscarTodos();
		
		model.addAttribute("listaDepartamentos", lista);
		
		return "/departamento/lista";
	}
}
