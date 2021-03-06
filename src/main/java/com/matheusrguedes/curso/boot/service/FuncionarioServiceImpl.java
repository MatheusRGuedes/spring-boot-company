package com.matheusrguedes.curso.boot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matheusrguedes.curso.boot.dao.FuncionarioDao;
import com.matheusrguedes.curso.boot.domain.Funcionario;

/*
 * @Transactional -> indica que é um método que se precisará abrir uma transação e será apenas de leitura;
 * */

@Service
@Transactional(readOnly = false)
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioDao funcionarioDao;
	
	@Override
	public void salvar(Funcionario funcionario) {
		funcionarioDao.save(funcionario);
	}

	@Override
	public void editar(Funcionario funcionario) {
		funcionarioDao.update(funcionario);
	}

	@Override
	public void excluir(Long id) {
		funcionarioDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Funcionario buscarPorId(Long id) {
		return funcionarioDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> buscarTodos() {
		return funcionarioDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> buscarPorNome(String nome) {
		return funcionarioDao.findByName(nome);
	}

	@Override
	public List<Funcionario> buscarPorData(LocalDate dataEntrada, LocalDate dataSaida) {
		
		if (dataEntrada == null || dataSaida == null) {
			
			if (dataEntrada == null && dataSaida != null) {
				return funcionarioDao.findByExitData(dataSaida);
			} else if (dataEntrada != null && dataSaida == null) {
				return funcionarioDao.findByEntryData(dataEntrada);
			}
			
			return new ArrayList<>();
		}
		
		return funcionarioDao.findBetweenDatas(dataEntrada, dataSaida);
	}
	
	@Override
	public List<Funcionario> buscarPorCargo(Long id) {
		return funcionarioDao.findByCargoId(id);
	}
}
