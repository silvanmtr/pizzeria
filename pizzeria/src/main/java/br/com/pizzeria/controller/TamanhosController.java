/**
 * 
 */
package br.com.pizzeria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.pizzeria.model.Tamanho;
import br.com.pizzeria.service.CadastroTamanhoService;


/**
 * @author Silvan de Jesus
 *
 */
@Controller
@RequestMapping("/tamanhos")
public class TamanhosController {
	
	@Autowired
	private CadastroTamanhoService cadastroTamanhoService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Tamanho tamanho, BindingResult result ){
		
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body(
					//result.getFieldError("nome").getDefaultMessage()
					result.getFieldErrors()
					//result.getAllErrors()
					);
		}
		
		tamanho = cadastroTamanhoService.salvar(tamanho);
		
		return ResponseEntity.ok(tamanho);
	}

}
