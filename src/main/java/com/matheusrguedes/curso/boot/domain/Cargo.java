package com.matheusrguedes.curso.boot.domain;

import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")

@NamedQueries({
	@NamedQuery(name = "Cargo.count", query = " select count(c) from Cargo c ")
})
@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long> {

	@NotBlank(message = "Nome cargo é obrigatório.")
	@Size(max = 60, message = "Nome cargo não pode passar de {max} caracteres.")
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	public String nome;
	
	@NotNull(message = "Departamento é obrigatório.")
	@ManyToOne
	@JoinColumn(name = "id_departamento_fk")
	public Departamento departamento;
	
	@OneToMany(mappedBy = "cargo")
	public List<Funcionario> funcionarios;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
}
