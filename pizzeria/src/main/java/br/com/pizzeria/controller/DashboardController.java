package br.com.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.pizzeria.repository.Clientes;

@Controller
public class DashboardController {
	@Autowired
	private Clientes clientes;
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		
		/*
		
		mv.addObject("valorItensEstoque", cervejas.valorItensEstoque());*/
		mv.addObject("totalClientes", clientes.count());
		
		return mv;
	}
	
}
