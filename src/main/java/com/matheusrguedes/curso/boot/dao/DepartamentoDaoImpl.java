package com.matheusrguedes.curso.boot.dao;

import org.springframework.stereotype.Repository;

import com.matheusrguedes.curso.boot.domain.Departamento;

/*
 * Classse para servir de implementação do Dao específico, sendo gerenciado pelo Spring como um bean do tipo Repository
 * */

@Repository
public class DepartamentoDaoImpl extends AbstractDao<Departamento, Long> implements DepartamentoDao {

	
}

