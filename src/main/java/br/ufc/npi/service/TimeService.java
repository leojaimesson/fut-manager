package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.bean.Time;
import br.ufc.npi.repositorio.JogadorRepositorio;
import br.ufc.npi.repositorio.TimeRepositorio;

@Service
public class TimeService {
	@Autowired
	TimeRepositorio timeRepositorio;
	
	@Autowired
	JogadorRepositorio jogadorRepositorio;
	
	public TimeService() {
		// TODO Auto-generated constructor stub
	}
	
	public Time salvarTime(String nome) {
		Time time = new Time(nome);
		timeRepositorio.save(time);
		
		return time;
	}
	
	public List<Time> getTodosTimes() {
		return timeRepositorio.findAll();
	}
	
	public Time getTime(Integer id) {
		return timeRepositorio.findById(id).get();
	}
	
	public boolean addJogadorAoTime(Integer idTime, Integer idJogador) {
		Time time = timeRepositorio.findById(idTime).get();
		
		if(time.getJogadores().size() == 7) {
			return false;
		} 
		Jogador jogador = jogadorRepositorio.findById(idJogador).get();
		
		time.getJogadores().add(jogador);
		jogador.setTime(time);
		
		timeRepositorio.save(time);
		jogadorRepositorio.save(jogador);

		return true;
	}
	
	public void delJogadorDoTime(Integer idTime, Integer idJogador) {
		Time time = timeRepositorio.findById(idTime).get();
		Jogador jogador = jogadorRepositorio.findById(idJogador).get();
		
		time.getJogadores().remove(jogador);
		jogador.setTime(null);
		
		timeRepositorio.save(time);
		jogadorRepositorio.save(jogador);
	}
	
	public void excluirTime(Integer idTime) {
		timeRepositorio.deleteById(idTime);
	}
}
