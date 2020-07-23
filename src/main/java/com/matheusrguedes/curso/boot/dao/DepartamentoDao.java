package com.matheusrguedes.curso.boot.dao;

import java.util.List;

import com.matheusrguedes.curso.boot.domain.Departamento;

/*
 * Dao específico
 * 
 * O Spring identifica a classe concreta que implementa essa interface e busca pelo método da operação (regras de polimorfismo), já que no DepartamentoDaoImpl terá todo o código das operações já 
 * que extende de AbstractDao, então, conseguindo gravar o objeto (dúvida tirada)
 * */

public interface DepartamentoDao {

	void save(Departamento departamento);
	
	void update(Departamento departamento);
	
	void delete(Long id);
	
	Departamento findById(Long id);
	
	List<Departamento> findAll();
}
