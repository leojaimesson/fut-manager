package br.ufc.npi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.service.JogadorService;
import br.ufc.npi.util.RedirectConstants;;

@Controller
@RequestMapping(path="/jogadores")
public class JogadorController {
	
	@Autowired
	private JogadorService service;
	
	@GetMapping
	public ModelAndView index () {
		ModelAndView model = new ModelAndView("jogadores");
		List<Jogador> jogadores = service.getTodosJogadores();
		model.addObject("jogadores", jogadores);
		
		return model;
	}
	
	@PostMapping("/salvar")
	public String salvarJogador(@RequestParam String nomeJogador,  @RequestParam Integer idadeJogador) {
		service.salvarJogador(nomeJogador, idadeJogador);
		return RedirectConstants.REDIRECT_JOGADORES;
	}
	
	@GetMapping("/excluir/{id}")
	public String excluirJogador(@PathVariable("id") Integer idJogador) {
		service.delJogador(idJogador);
		return RedirectConstants.REDIRECT_JOGADORES;
	}
}
