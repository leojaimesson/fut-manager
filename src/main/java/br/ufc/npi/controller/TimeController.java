package br.ufc.npi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.bean.Time;
import br.ufc.npi.repositorio.TimeRepositorio;
import br.ufc.npi.service.JogadorService;
import br.ufc.npi.service.TimeService;

@Controller
@RequestMapping(path="/times")
public class TimeController {
	@Autowired
	TimeService timeService;
	
	@Autowired
	JogadorService jogadorService;
	
	@Autowired
	TimeRepositorio tr;
	
	@GetMapping
	public ModelAndView index () {
		ModelAndView model = new ModelAndView("times");
		List<Time> times = timeService.getTodosTimes();
		model.addObject("times", times);
		
		return model;
	}
	
	
	@GetMapping("/detalhes/{id}")
	public ModelAndView detalhesTime(@PathVariable("id") Integer id, @RequestParam(required=false) String erro) {
		ModelAndView model = new ModelAndView("detalhes-time");
		Time time = timeService.getTime(id);
		List<Jogador> jogadoresSemTime = jogadorService.getJogadoresSemtTime();
		model.addObject("time", time);
		model.addObject("jogadoresSemTime", jogadoresSemTime);
		model.addObject("erro", erro);

		return model;

	}
	
	@PostMapping("/salvar")
	public String salvarTime(@RequestParam String nomeTime) {
		timeService.salvarTime(nomeTime);
		
		return "redirect:/times/";
	}
	
	@PostMapping("/{idTime}/adicionarjogador")
	public ModelAndView adicionarJogadorAoTime(@PathVariable("idTime") Integer idTime,
			@RequestParam Integer jogadorSemTimeID) {
		
		ModelAndView model = new ModelAndView("redirect:/times/detalhes/"+idTime);
		boolean itsOk = timeService.addJogadorAoTime(idTime, jogadorSemTimeID);
		if(itsOk==false){
			String erro = "O time já está completo.";
			model.addObject("erro", erro);
		}
		return model;
	}
	
	@GetMapping("/{idTime}/removerjogador/{idJogador}")
	public String removerJogadorDoTime(@PathVariable("idTime") Integer idTime, 
			@PathVariable("idJogador") Integer idJogador) {
		
		timeService.delJogadorDoTime(idTime, idJogador);
		
		return "redirect:/times/detalhes/"+idTime;
	}
	
	@GetMapping("/excluir/{idTime}")
	public String exluirTime(@PathVariable ("idTime") Integer idTime) {
		timeService.excluirTime(idTime);
		return "redirect:/times/";
	}
	
	
}
