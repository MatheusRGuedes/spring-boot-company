package com.matheusrguedes.curso.boot.dao;

import java.time.LocalDate;
import java.util.List;

import com.matheusrguedes.curso.boot.domain.Funcionario;

public interface FuncionarioDao {

	void save(Funcionario funcionario);
	
	void update(Funcionario funcionario);
	
	void delete(Long id);
	
	Funcionario findById(Long id);
	
	List<Funcionario> findAll();
	
	List<Funcionario> findByName(String nome);

	List<Funcionario> findBetweenDatas(LocalDate dataEntrada, LocalDate dataSaida);
	
	List<Funcionario> findByEntryData(LocalDate dataEntrada);
	
	List<Funcionario> findByExitData(LocalDate dataSaida);

	List<Funcionario> findByCargoId(Long id);
}
