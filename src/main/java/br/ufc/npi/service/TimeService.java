package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Time;
import br.ufc.npi.repositorio.TimeRepositorio;

@Service
public class TimeService {
	@Autowired
	TimeRepositorio repositorio;
	
	public TimeService() {
		// TODO Auto-generated constructor stub
	}
	
	public Time salvarTime(String nome) {
		Time time = new Time(nome);
		repositorio.save(time);
		
		return time;
	}
	
	public List<Time> getTodosTimes() {
		return repositorio.findAll();
	}
}
