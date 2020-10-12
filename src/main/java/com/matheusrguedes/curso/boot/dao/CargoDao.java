package com.matheusrguedes.curso.boot.dao;

import java.util.List;

import com.matheusrguedes.curso.boot.domain.Cargo;
import com.matheusrguedes.curso.boot.util.PaginacaoUtil;

public interface CargoDao {

	void save(Cargo cargo);
	
	void update(Cargo cargo);
	
	void delete(Long id);
	
	Cargo findById(Long id);
	
	List<Cargo> findAll();
	
	PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao, String propriedade, int numLinhas);
}
