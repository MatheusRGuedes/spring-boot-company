package com.matheusrguedes.curso.boot.service;

import java.util.List;

import com.matheusrguedes.curso.boot.domain.Cargo;
import com.matheusrguedes.curso.boot.util.PaginacaoUtil;

public interface CargoService {

	void salvar(Cargo cargo);
	
	void editar(Cargo cargo);
	
	void excluir(Long id);
	
	Cargo buscarPorId(Long id);
	
	List<Cargo> buscarTodos();
	
	boolean temFuncionarios(Long id);
	
	PaginacaoUtil<Cargo> buscarPorPagina(int pagina, String direcao, String propriedade, int numLinhas);
}
