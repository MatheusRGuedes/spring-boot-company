package com.matheusrguedes.curso.boot.dao;

import org.springframework.stereotype.Repository;

import com.matheusrguedes.curso.boot.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

}
