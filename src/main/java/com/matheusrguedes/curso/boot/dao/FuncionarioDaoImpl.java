package com.matheusrguedes.curso.boot.dao;

import java.time.LocalDate;
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

	@Override
	public List<Funcionario> findBetweenDatas(LocalDate dataEntrada, LocalDate dataSaida) {
		String jpql = new StringBuilder(" Select f from Funcionario f ")
								.append(" where f.dataEntrada >= ?1 and f.dataSaida <= ?2 ")
								.append(" order by f.dataEntrada asc ").toString();
		return createQuery(jpql, dataEntrada, dataSaida);
	}
	
	@Override
	public List<Funcionario> findByEntryData(LocalDate dataEntrada) {		
		return createQuery(" Select f from Funcionario f where f.dataEntrada = ?1 "
						 + " order by f.dataEntrada asc", 
						 dataEntrada);
	}
	
	@Override
	public List<Funcionario> findByExitData(LocalDate dataSaida) {
		return createQuery(" Select f from Funcionario f where f.dataSaida = ?1 "
						 + " order by f.dataEntrada asc ", 
						 dataSaida);
	}
	
	@Override
	public List<Funcionario> findByCargoId(Long id) {
		return createQuery(" Select f from Funcionario f where f.cargo.id = ?1 ", id);
	}
}