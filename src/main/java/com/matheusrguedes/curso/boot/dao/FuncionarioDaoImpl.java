package com.matheusrguedes.curso.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.matheusrguedes.curso.boot.domain.Funcionario;

/*
 * Manipulação de string em jpql: 
 * --> https://www.objectdb.com/java/jpa/query/jpql/string#CONCAT___String_Concatenation
 * */

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

	@Override
	public List<Funcionario> findByName(String name) {
		
		/*TypedQuery<Funcionario> query = getEntityManager()
				.createQuery("select f from Funcionario f where LOWER(f.nome) like :nome", Funcionario.class);
		
		query.setParameter("nome", "%" + name.toLowerCase() + "%");
		return query.getResultList();*/
		
		return createQuery("Select f from Funcionario f where f.nome like CONCAT('%',?1,'%')", name);
	}
}
