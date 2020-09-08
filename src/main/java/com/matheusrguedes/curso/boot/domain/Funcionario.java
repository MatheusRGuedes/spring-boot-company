package com.matheusrguedes.curso.boot.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/*
 * columnDefinition 		 -> define o tipo de dado que será gravado no banco
 * cascade = CascadeType.ALL -> Quando se insere/atualiza/deleta um funcionário, acontece o mesmo com endereço. 
 * */

/*
 * Como o spring não sabe converter string para localDate da erro, assim como para o bigdecimal.
 * 
 * @DateTimeFormat -> essa anotação informa ao spring que o atributo será convertido conforme o formato padrão q é dd/MM/yyyy.
 * 				   -> Pode ser usado também antes de um parâmetro de método do controller.
 * 
 * ISO 			   -> Padrão para data totalmente numérica em formato AAAA-MM-DD
 * ISO.DATE		   -> Formato que contêm apenas a data, para yyyy-mm-dd. Caso o valor vier em branco, será reconhecido e setado como null.
 * 
 * Style.CURRENCY  -> Estilo moeda, por padrão usa o estilo americano;
 * 
 * @PastOrPresent -> data deve ser menor ou igual a data atual.
 * 
 * */

@SuppressWarnings("serial")
@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario extends AbstractEntity<Long> {

	@NotBlank
	@Size(min = 3, max = 255)
	@Column(nullable = false, unique = true)
	private String nome;
	
	@Digits(integer = 7, fraction = 2)
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal salario;
	
	@NotNull
	@PastOrPresent(message = "{PastOrPresent.funcionario.dataEntrada}")
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@Column(name = "data_entrada", nullable = false, columnDefinition = "DATE")
	private LocalDate dataEntrada;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@Column(name = "data_saida", columnDefinition = "DATE")
	private LocalDate dataSaida;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id_fk")
	private Endereco endereco;
	
	@NotNull(message = "{NotNull.funcionario.cargo}")
	@ManyToOne
	@JoinColumn(name = "cargo_id_fk")
	private Cargo cargo;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}