package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.repositorio.JogadorRepositorio;

@Service
public class JogadorService {
	@Autowired
	private JogadorRepositorio repositorio;
	
	@Autowired
	private TimeService timeService;
	
	
	public Jogador salvarJogador(String nome, Integer idade) {
		Jogador jogador = new Jogador(nome, idade);
		this.repositorio.save(jogador);
		return jogador;
	}
	
	public List<Jogador> getTodosJogadores() {
		return repositorio.findAll();
	}
	
	public List<Jogador> getJogadoresSemtTime() {
		return repositorio.buscarJogadoresSemTime();
	}
	
	public void delJogador(Integer idJogador) {
		Jogador jogador = repositorio.findById(idJogador).get();
		if(jogador.getTime() != null) {
			timeService.delJogadorDoTime(jogador.getTime().getId(), idJogador);
		}
		repositorio.delete(jogador);
	}
}
