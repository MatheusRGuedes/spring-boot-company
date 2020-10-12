package com.matheusrguedes.curso.boot.util;

import java.util.List;

/*
 * Classe para paginação backend
 * 
 * tamanho 		--> número de linhas
 * página  		--> número da página atual, para o cliente não se perder
 * totalPaginas --> tamanho dividido por 5, pois cada página terá até 5 registros
 * direcao		--> asc ou desc
 * registros	--> resultado do banco de dados
 * */

public class PaginacaoUtil<T> {

	private int tamanho;
	private int pagina;
	private long totalPaginas;
	private String propriedade;
	private String direcao;
	private List<T> registros;
	
	public PaginacaoUtil(int tamanho, int pagina, long totalPaginas, String propriedade, 
			String direcao, List<T> registros) {
		super();
		this.tamanho = tamanho;
		this.pagina = pagina;
		this.totalPaginas = totalPaginas;
		this.propriedade = propriedade;
		this.direcao = direcao;
		this.registros = registros;
	}

	public int getTamanho() {
		return tamanho;
	}

	public int getPagina() {
		return pagina;
	}

	public long getTotalPaginas() {
		return totalPaginas;
	}

	public List<T> getRegistros() {
		return registros;
	}

	public String getDirecao() {
		return direcao;
	}

	public String getPropriedade() {
		return propriedade;
	}
}