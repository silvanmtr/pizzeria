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
import br.com.pizzeria.model.Bebida;
import br.com.pizzeria.repository.Bebidas;
import br.com.pizzeria.repository.filter.BebidaFilter;
import br.com.pizzeria.service.CadastroBebidaService;
import br.com.pizzeria.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/bebidas")
public class BebidasController {

	@Autowired
	private Bebidas bebidas;
	
	@Autowired
	private CadastroBebidaService cadastroBebidaService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Bebida bebida) {
		ModelAndView mv = new ModelAndView("bebida/CadastroBebida");
		return mv;
	}
	
		
	@PostMapping(value = {"/nova", "{\\d+}"})
	public ModelAndView salvar(@Valid Bebida bebida, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return nova(bebida);
		}
		
		try {
			cadastroBebidaService.salvar(bebida);
		} catch (Exception e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(bebida);
		}
		
		attributes.addFlashAttribute("mensagem", "Bebida salva com sucesso!");
		return new ModelAndView("redirect:/bebidas/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(BebidaFilter cidadeFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("bebida/PesquisaBebidas");
		
		PageWrapper<Bebida> paginaWrapper = new PageWrapper<>(bebidas.filtrar(cidadeFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Bebida bebida) {
		try {
			cadastroBebidaService.excluir(bebida);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		
		Bebida bebida = bebidas.buscarBebidas(codigo);
		ModelAndView mv = nova(bebida);
		mv.addObject(bebida);
		return mv;
	}
}
