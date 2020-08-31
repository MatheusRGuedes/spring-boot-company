package com.matheusrguedes.curso.boot.service;

import java.time.LocalDate;
import java.util.List;

import com.matheusrguedes.curso.boot.domain.Funcionario;

public interface FuncionarioService {

	void salvar(Funcionario funcionario);
	
	void editar(Funcionario funcionario);
	
	void excluir(Long id);
	
	Funcionario buscarPorId(Long id);
	
	List<Funcionario> buscarTodos();
	
	List<Funcionario> buscarPorNome(String nome);

	List<Funcionario> buscarPorData(LocalDate dataEntrada, LocalDate dataSaida);
}
