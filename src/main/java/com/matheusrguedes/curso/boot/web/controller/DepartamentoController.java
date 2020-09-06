package com.matheusrguedes.curso.boot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
 * PathVariable -> Consegue pegar o id pela url e injetar no parâmetro.
 * 
 * RedirectAttributes --> Usado em cenário de redirecionamento e podem ser acessados (flashAttributes) depois, só em métodos que serão os finais de uma operação de redirecionamento.
 * 					  --> Fornece uma possibilidade de compartilhar atributos etre dois métodos.
 * 
 * BindingResult --> Armazena o resultado da validação do objeto, através do @Valid. Então, o Spring procura as anotações de validação, depois ele vincula os erros no objeto BindResults;
 * 				 --> Sempre adicionar o objeto BindingResult logo após o objeto a ser validado;
 * */

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;
	
	/*-------- Cadastro -------*/
	
	@GetMapping("/cadastrar")
	public String cadastro(Departamento departamento) {
		return "/departamento/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult result,
			RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			attr.addFlashAttribute("errors", result.getFieldErrors());
			return "redirect:/departamentos/cadastrar";
		}
		
		departamentoService.salvar(departamento);
		
		attr.addFlashAttribute("success", "Departamento inserido com sucesso.");
		
		return "redirect:/departamentos/cadastrar";
	}
	
	/*-------- Listagem -------*/
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		List<Departamento> lista = departamentoService.buscarTodos();
		
		model.addAttribute("listaDepartamentos", lista);
		
		return "/departamento/lista";
	}
	
	/*---------- Edição ----------*/
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		
		Departamento departamento = departamentoService.buscarPorId(id);
		
		model.addAttribute("departamento", departamento);
			
		return "/departamento/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento, BindingResult result, 
			RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			attr.addFlashAttribute("fail", result.getFieldError().getDefaultMessage());
			return "redirect:/departamentos/cadastrar";
		}
		
		departamentoService.editar(departamento);

		attr.addFlashAttribute("success", "Departamento editado com sucesso.");
		
		return "redirect:/departamentos/cadastrar";
	}
	
	/*--------- Exclusão ----------*/
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		
		if (departamentoService.temCargos(id)) {
			
			attr.addFlashAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s).");
			
		} else {
			
			departamentoService.excluir(id);
			
			attr.addFlashAttribute("success", "Departamento excluído com sucesso.");
		}
		
		return "redirect:/departamentos/listar";
		
		// Não muda url
		//return listar(model);
	}
}