package com.matheusrguedes.curso.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.matheusrguedes.curso.boot.domain.Cargo;
import com.matheusrguedes.curso.boot.util.PaginacaoUtil;

/*
 * Tarefa: Adicionar ordenação para o departamento;
 * 
 * - possibilitar ordenar por cargo OU departamento;
 * - definir o nome do cargo como padrão;
 * 
 * */

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {

	public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao, String propriedade, int numLinhas) {
		int tamanho = numLinhas;
		int inicio = (pagina - 1) * tamanho; // 0*5 = 0 | 1*5 = 5 | 2*5 = 10
		
		direcao = direcao.trim().toLowerCase();
		propriedade = propriedade.trim().toLowerCase();
		
		if ( !"asc".equals(direcao) && !"desc".equals(direcao) ) { //verifica direção inexistente
			direcao = "asc";
		}
		
		if ( !"nome".equals(propriedade) && !"departamento".equals(propriedade) ) { //verifica direção inexistente
			propriedade = "nome";
		}
		
		String qlString = defineQuery(direcao, propriedade);
		
		List<Cargo> cargos = getEntityManager()
				.createQuery(qlString, Cargo.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistros = count();
		// valores com vírgula irão ser redondados para baixo, dando um nº exato de páginas
		long totalPaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<Cargo>(tamanho, pagina, totalPaginas, propriedade, direcao, cargos);
	}
	
	public long count() {
		return getEntityManager()
				.createNamedQuery("Cargo.count", Long.class)
				.getSingleResult();
	}
	
	public String defineQuery(String direcao, String propriedade) {		
		if ("departamento".equals(propriedade) ) {
			return "select c from Cargo c order by c.departamento.nome " + direcao;
		}
			
		return "select c from Cargo c order by c.nome " + direcao;
	}
}