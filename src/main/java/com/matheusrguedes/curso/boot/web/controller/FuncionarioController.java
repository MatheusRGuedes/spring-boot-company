package com.matheusrguedes.curso.boot.web.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.matheusrguedes.curso.boot.domain.Cargo;
import com.matheusrguedes.curso.boot.domain.Departamento;
import com.matheusrguedes.curso.boot.domain.Funcionario;
import com.matheusrguedes.curso.boot.domain.Uf;
import com.matheusrguedes.curso.boot.service.CargoService;
import com.matheusrguedes.curso.boot.service.DepartamentoService;
import com.matheusrguedes.curso.boot.service.FuncionarioService;
import com.matheusrguedes.curso.boot.web.validator.FuncionarioValidator;

/*
 * @InitBinder -> indica que o método com a anotação será o primeiro a ser executado ao fazer uma requisição a este controlador;
 * 			   -> Dessa forma ele ativa a validação e o Spring MVC vai até o FuncionarioValidator para ativar a validação, antes de chegar ao método da requisição (salvar ou editar);
 * */

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private CargoService cargoService;
	
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.addValidators(new FuncionarioValidator());
	}
	
	
	/*---------- Cadastro ---------*/
	
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "funcionario/cadastro";
	}
	
	
	@PostMapping("/salvar")
	public String salvar(@Valid Funcionario funcionario, BindingResult result, 
			RedirectAttributes attr) {
		
		//new FuncionarioValidator().validate(funcionario, result);
		
		if (result.hasErrors()) {
			return "funcionario/cadastro";
		}
		
		funcionarioService.salvar(funcionario);
		
		attr.addFlashAttribute("success", "Funcionário cadastrado com sucesso.");
		
		return "redirect:/funcionarios/cadastrar";
	}
	
	
	/*---------- Listagem ----------*/
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		List<Funcionario> listaFuncionarios = funcionarioService.buscarTodos();
		
		model.addAttribute("listaFuncionarios", listaFuncionarios);
		
		return "funcionario/lista";
	}
	
	
	/*----------- Edição ------------*/
	
	@GetMapping("/editar/{funcionarioId}")
	public String preEditar(@PathVariable("funcionarioId") Long id, 
			RedirectAttributes attr) {
		
		Funcionario funcionario = funcionarioService.buscarPorId(id);
		
		attr.addFlashAttribute("funcionario", funcionario);
		
		return "redirect:/funcionarios/cadastrar";
	}
	
	
	@PostMapping("/editar")
	public String editar(@Valid Funcionario funcionario, BindingResult result, 
			RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "funcionario/cadastro";
		}
		
		funcionarioService.editar(funcionario);
		
		attr.addFlashAttribute("success", "Funcionário editado com sucesso.");
		
		return "redirect:/funcionarios/cadastrar";
	}
	
	
	/*----------- Exclusão ----------*/
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attr) {
		
		funcionarioService.excluir(id);
		
		attr.addFlashAttribute("success", "Funcionário removido com sucesso.");
		
		return "redirect:/funcionarios/listar";
	}
	
	
	/*----------- Buscas ------------*/
	
	@GetMapping("/buscar/nome")
	public String buscarNome(@RequestParam("nome") String nomeFuncionario,
			ModelMap model) {
		
		List<Funcionario> funcionarios = funcionarioService.buscarPorNome(nomeFuncionario);
		
		model.addAttribute("listaFuncionarios", funcionarios);
		
		return "funcionario/lista";
	}
	
	@GetMapping("/buscar/data")
	public String buscarData(@RequestParam("entrada") @DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE) LocalDate dataEntrada,
							 @RequestParam("saida") @DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE) LocalDate dataSaida,
							 ModelMap model) {
		
		List<Funcionario> funcionarios = funcionarioService.buscarPorData(dataEntrada, dataSaida);
		
		model.addAttribute("listaFuncionarios", funcionarios);
		
		return "funcionario/lista";
	}
	
	
	@GetMapping("/buscar/cargo")
	public String buscarCargo(@RequestParam("id") Long cargoId, ModelMap model) {
		
		List<Funcionario> funcionarios = funcionarioService.buscarPorCargo(cargoId);
		
		model.addAttribute("listaFuncionarios", funcionarios);
		
		return "funcionario/lista";
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