package com.matheusrguedes.curso.boot.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.matheusrguedes.curso.boot.dao.CargoDaoImpl;
import com.matheusrguedes.curso.boot.domain.Cargo;
import com.matheusrguedes.curso.boot.domain.Departamento;
import com.matheusrguedes.curso.boot.service.CargoService;
import com.matheusrguedes.curso.boot.service.DepartamentoService;
import com.matheusrguedes.curso.boot.util.PaginacaoUtil;

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
	
	//@Autowired
	//private CargoDaoImpl cargoDaoImpl;

	/*-------- Cadastro ---------*/
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		
		// é feito pelo método listaDepartamentos()
		//List<Departamento> departamentos = departamentoService.buscarTodos();
		//model.addAttribute("listaDepartamentos", departamentos);
		
		return "cargo/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			//attr.addFlashAttribute("errors", result.getFieldErrors());
			return "cargo/cadastro";
		}
		
		cargoService.salvar(cargo);
		
		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		
		return "redirect:/cargos/cadastrar";
	}
	
	/*-------- Listagem ----------*/
	
	@GetMapping("/listar")
	public String listar(ModelMap model, 
			@RequestParam("page") Optional<Integer> page, 
			@RequestParam("dir") Optional<String> direcao,
			@RequestParam("prop") Optional<String> prop,
			@RequestParam("rows") Optional<Integer> rows) {
		
		int paginaAtual = page.orElse(1);
		String direcaoOrdem = direcao.orElse("asc");
		String propriedade = prop.orElse("nome");
		int numLinhas = rows.orElse(5);
		
		PaginacaoUtil<Cargo> pageCargo = cargoService.buscarPorPagina(paginaAtual, direcaoOrdem, propriedade, numLinhas);
		
		model.addAttribute("pageCargo", pageCargo);
		
		return "cargo/lista";
	}
	
	/*-------- Edição ---------*/
	
	@GetMapping("/editar/{cargoId}")
	public String preEdita(@PathVariable("cargoId") Long id, 
			ModelMap model) {
		
		Cargo cargo = cargoService.buscarPorId(id);
		
		model.addAttribute("cargo", cargo);
		
		return "cargo/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			//attr.addFlashAttribute("errors", result.getFieldErrors());
			return "cargo/cadastro";
		}
		
		cargoService.editar(cargo);
		
		attr.addFlashAttribute("success", "Cargo atualizado com sucesso.");
		
		return "redirect:/cargos/cadastrar";
	}
	
	/*-------- Exclusão ---------*/
	
	@GetMapping("/deletar/{cargoId}")
	public String deletar(@PathVariable Long cargoId, 
			RedirectAttributes attr) {
		
		if (!cargoService.temFuncionarios(cargoId)) {
			
			cargoService.excluir(cargoId);
			attr.addFlashAttribute("success", "Cargo excluído com sucesso.");
		} else {
			
			attr.addFlashAttribute("fail", "Cargo não excluído. Possui funcionários vinculados.");
		}		
		
		return "redirect:/cargos/listar";
	}
	
	
	@ModelAttribute("listaDepartamentos")
	public List<Departamento> listaDepartamentos() {
		return departamentoService.buscarTodos();
	}
}