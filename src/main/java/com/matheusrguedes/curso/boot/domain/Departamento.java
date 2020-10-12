package com.matheusrguedes.curso.boot.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/*
 * mappedBy -> Coluna da chave estrangeira da entidade que se relaciona (Cargo, lado forte da relação, pois contêm fk)
 * 
 * @NotEmpty --> Anotação que n pode ser null ou vazio;
 * @NotBlank --> Anotação que não pode ser null e ter ao menos 1 caracter q n seja espaço.
 * */

@SuppressWarnings("serial")
@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractEntity<Long> {
	
	@NotBlank(message = "Nome departamento é obrigatório.")
	@Size(min = 3, max = 60, message = "Nome departamento deve ter entre {min} e {max} caracteres.")
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	@OneToMany(mappedBy = "departamento")
	private List<Cargo> cargos;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Cargo> getCargos() {
		return cargos;
	}
	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
}
