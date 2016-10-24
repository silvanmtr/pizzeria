package br.com.pizzeria.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pizzeria.controller.page.PageWrapper;
import br.com.pizzeria.model.Pizza;
import br.com.pizzeria.model.Sabor;
import br.com.pizzeria.repository.Pizzas;
import br.com.pizzeria.repository.Tamanhos;
import br.com.pizzeria.repository.filter.PizzaFilter;
import br.com.pizzeria.service.CadastroPizzaService;
import br.com.pizzeria.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/pizzas")
public class PizzasController {

	@Autowired
	private Tamanhos tamanhos;
	
	@Autowired
	private Pizzas pizzas;
	
	@Autowired
	private CadastroPizzaService cadastroPizzaservice;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Pizza pizza) {
		ModelAndView mv = new ModelAndView("pizza/CadastroPizza");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("tamanhos", tamanhos.findAll());

		return mv;
	}
			
	@PostMapping(value = {"/nova", "{\\d+}"})
	public ModelAndView salvar(@Valid Pizza pizza, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return nova(pizza);
		}
		
		try {
			
			cadastroPizzaservice.salvar(pizza);
		} catch (Exception e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(pizza);
		}
		
		attributes.addFlashAttribute("mensagem", "Pizza salva com sucesso!");
		return new ModelAndView("redirect:/pizzas/nova");
	}
	
	
	@GetMapping
	public ModelAndView pesquisar(PizzaFilter pizzaFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pizza/PesquisaPizzas");
		
		mv.addObject("sabores", Sabor.values());
		mv.addObject("tamanhos", tamanhos.findAll());
		
		PageWrapper<Pizza> paginaWrapper = new PageWrapper<>(pizzas.filtrar(pizzaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Pizza pizza) {
		try {
			cadastroPizzaservice.excluir(pizza);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		
		Pizza pizza = pizzas.buscarPizzas(codigo);
		ModelAndView mv = nova(pizza);
		mv.addObject(pizza);
		return mv;
	}
}
