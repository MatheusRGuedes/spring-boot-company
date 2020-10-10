package com.matheusrguedes.curso.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.matheusrguedes.curso.boot.domain.Cargo;
import com.matheusrguedes.curso.boot.util.PaginacaoUtil;


@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {

	public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao) {
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho; // 0*5 = 0 | 1*5 = 5 | 2*5 = 10
		
		if (!"asc".equals(direcao.toLowerCase()) && !"desc".equals(direcao.toLowerCase())) {
			direcao = "asc";
		}
		
		String qlString = " select c from Cargo c order by c.nome " + direcao + " ";
		
		List<Cargo> cargos = getEntityManager()
				.createQuery(qlString, Cargo.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistros = count();
		// valores com vírgula irão ser redondados para baixo, dando um nº exato de páginas
		long totalPaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<Cargo>(tamanho, pagina, totalPaginas, direcao, cargos);
	}
	
	public long count() {
		return getEntityManager()
				.createNamedQuery("Cargo.count", Long.class)
				.getSingleResult();
	}
}
