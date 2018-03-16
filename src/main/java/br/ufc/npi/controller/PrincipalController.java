package br.ufc.npi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import org.springframework.web.bind.annotation.RequestMapping;
import br.ufc.npi.util.RedirectConstants;

@Controller
@RequestMapping(path="/")

public class PrincipalController {
	
	@GetMapping
	public String index () {
		return RedirectConstants.INDEX;
	}
}
