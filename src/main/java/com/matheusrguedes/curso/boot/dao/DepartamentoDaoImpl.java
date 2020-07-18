package com.matheusrguedes.curso.boot.dao;

import org.springframework.stereotype.Repository;

import com.matheusrguedes.curso.boot.domain.Departamento;

//porque, além de herdar o abstractDao está implementando a interface departamentoDao, já que a herança de AbstractDao já fornece os métodos necessários ? E quando usado um método de 
// DepartamentoDaoImpl, por exemplo o save, como que é a identificação de quem é o método, se é do AbstractDao ou DepartamentoDao ?

@Repository
public class DepartamentoDaoImpl extends AbstractDao<Departamento, Long> implements DepartamentoDao {

	
}

