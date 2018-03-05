package br.ufc.npi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufc.npi.service.TimeService;

@Controller
@RequestMapping(path="/times/")
public class TimeController {
	@Autowired
	TimeService service;
	
	@RequestMapping(path="/")
	public String index () {
		return "times";
	}
	
	@RequestMapping(path="/{id}")
	public String detalhesTime() {
		return "detalhes-time";
	}
	
	@RequestMapping(path="/salvar/", method=RequestMethod.POST)
	public String salvarTime(@RequestParam String nomeTime) {
		service.salvarTime(nomeTime);
		
		return "redirect:/times/";
	}
	
}
