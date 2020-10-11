package com.matheusrguedes.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matheusrguedes.curso.boot.dao.CargoDao;
import com.matheusrguedes.curso.boot.domain.Cargo;
import com.matheusrguedes.curso.boot.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class CargoServiceImpl implements CargoService {

	@Autowired
	public CargoDao cargoDao;
	
	@Override
	public void salvar(Cargo cargo) {
		cargoDao.save(cargo);
	}

	@Override
	public void editar(Cargo cargo) {
		cargoDao.update(cargo);
	}

	@Override
	public void excluir(Long id) {
		cargoDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Cargo buscarPorId(Long id) {
		
		return cargoDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cargo> buscarTodos() {
		
		return cargoDao.findAll();
	}

	@Override
	public boolean temFuncionarios(Long id) {
		
		Cargo cargo = buscarPorId(id);
		
		if (cargo.getFuncionarios().size() == 0) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public PaginacaoUtil<Cargo> buscarPorPagina(int pagina, String direcao, String propriedade, int numLinhas) {
		return cargoDao.buscaPaginada(pagina, direcao, propriedade, numLinhas);
	}
}