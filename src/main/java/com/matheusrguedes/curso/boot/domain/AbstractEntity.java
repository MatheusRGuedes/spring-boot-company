package com.matheusrguedes.curso.boot.domain;

import java.io.Serializable;

import javax.persistence.*;

/*
 * Serializable 	-> A serialização significa salvar o estado atual dos objetos em arquivos em formato binário para o seu computador, 
 * 					-> sendo assim esse estado poderá ser recuperado posteriormente recriando o objeto em memória assim como ele estava no momento da sua serialização.
 * 					-> Já que estamos trabalhando com ORM, uma boa prática é usar a interface acima.
 * MappedSuperClass -> Informa que essa classe é uma superclasse das demais classes de entidade da aplicação.
 * SuppressWarnings -> Omite a informação de warning do serial version id;
 * */

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id;

	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		AbstractEntity<?> other = (AbstractEntity<?>) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
			
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		return "id = " + id;
	}
}
