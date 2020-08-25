package com.matheusrguedes.curso.boot.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.matheusrguedes.curso.boot.domain.Cargo;
import com.matheusrguedes.curso.boot.domain.Funcionario;
import com.matheusrguedes.curso.boot.domain.Uf;
import com.matheusrguedes.curso.boot.service.CargoService;
import com.matheusrguedes.curso.boot.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private CargoService cargoService;
	
	
	/*---------- Cadastro ---------*/
	
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}
	
	
	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		
		funcionarioService.salvar(funcionario);
		
		attr.addFlashAttribute("success", "Funcionário cadastrado com sucesso.");
		
		return "redirect:/funcionarios/cadastrar";
	}
	
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		List<Funcionario> listaFuncionarios = funcionarioService.buscarTodos();
		
		model.addAttribute("listaFuncionarios", listaFuncionarios);
		
		return "/funcionario/lista";
	}
	
	
	/* Model Atributes para serem enviados para as páginas */
	
	@ModelAttribute("listaCargos")
	public List<Cargo> listaCargos() {
		return cargoService.buscarTodos();
	}
	
	@ModelAttribute("listaUfs")
	public List<Uf> listaUfs() {
		return Arrays.asList(Uf.values());
	}
}
