package com.matheusrguedes.curso.boot.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/*
 * DAO -> padrão de projetos que significa - Data Acsses Object, onde se trabalha com a persistência de dados. Esse pacote será para as classes referentes ao DAO
 * 
 * createQuery 	 	  -> método protegido para ser acessado apenas pelas classes que herdam AbstractDao ou estar no mesmo pacote.
 * 			     	  -> Criado para possibilitar vários tipos de consulta.
 * 			   	 	  -> Parâmetros: a query em jpql e os valores dos parâmetros (var args do tipo Object)
 * TypedQuery  	 	  -> Serve para informar a classe de entidade referente a consulta
 * entityClass 	 	  -> Tipo da entidade que a operação q vem de um DAO expecífico, como CargoDao (passando o tipo Cargo), por exemplo, que está passando para AbstractDAO
 * 			   	 	  -> Finalidade: Para fazer uma consulta em jpql, deve-se informar o nome da entidade.
 * PersistenceContext -> faz a injeção de dependência na variável
 * entityManager 	  -> te permite fazer as consultas no banco. A parte de abrir e commitar fechar as transações fica responsável pelo spring.
 * 
 * -------------------------------------------------------------------
 * 
 * Esta classe serve como DAO genérico, com métodos comuns a várias classes. 
 * */

public abstract class AbstractDao<T, PK extends Serializable> {

	private final Class<T> entityClass = 
			(Class<T>) ( (ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void save(T entity) {
		
		entityManager.persist(entity);
	}
	
	public void update(T entity) {
		
		entityManager.merge(entity);
	}
	
	public void delete(PK id) {
		
		entityManager.remove(entityManager.getReference(entityClass, id));
	}
	
	public T findById(PK id) {
		
		return entityManager.find(entityClass, id);
	}
	
	public List<T> findAll() {
		
		return entityManager
				.createQuery("from " + entityClass.getSimpleName(), entityClass)
				.getResultList();
	}
	
	protected List<T> createQuery(String jpql, Object... params) {
		TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
		
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i+1, params[i]);
		}
		
		return query.getResultList();
	}
}
