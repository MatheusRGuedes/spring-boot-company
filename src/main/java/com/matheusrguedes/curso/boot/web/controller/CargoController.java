package com.matheusrguedes.curso.boot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.matheusrguedes.curso.boot.domain.Cargo;
import com.matheusrguedes.curso.boot.domain.Departamento;
import com.matheusrguedes.curso.boot.service.CargoService;
import com.matheusrguedes.curso.boot.service.DepartamentoService;

/*
 * Pode se referenciar o objeto e o atributo diretamento no elemento de formulário (input, select...) através de th:field="*{objeto.propriedade}" ao invés de definir apenas um th:objet para o form
 * 
 * @ModelAttribute -> Anotação que liga/vincula o valor de retorno do método com o model atribute, expondo o valor de retorno para a página de resposta (view);
 * 				   -> É executado antes da chamada de um método de requisição.
 * 				   -> Pode ser tmbém usado para mostrar dados que foram gravados na mesma hora em outra tela
 * */

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@Autowired
	private CargoService cargoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		
		// é feito pelo método listaDepartamentos()
		//List<Departamento> departamentos = departamentoService.buscarTodos();
		//model.addAttribute("listaDepartamentos", departamentos);
		
		return "/cargo/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(Cargo cargo, RedirectAttributes attr) {
			
		cargoService.salvar(cargo);
		
		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		List<Cargo> listaCargos = cargoService.buscarTodos();
		
		model.addAttribute("listaCargos", listaCargos);
		
		return "/cargo/lista";
	}
	
	@ModelAttribute("listaDepartamentos")
	public List<Departamento> listaDepartamentos() {
		return departamentoService.buscarTodos();
	}
}