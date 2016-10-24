/**
 * 
 */
package br.com.pizzeria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pizzeria.model.Tamanho;
import br.com.pizzeria.repository.Tamanhos;
import br.com.pizzeria.service.exception.TamanhoJaCadastradoException;



/**
 * @author Silvan de Jesus
 *
 */

@Service
public class CadastroTamanhoService {

	@Autowired
	private Tamanhos tamanhos;
	
	@Transactional
	public Tamanho salvar(Tamanho tamanho){
		//Optional<Tamanho> nomeTamanhoOptional = tamanhos.findByNomeIgnoreCase(tamanho.getNome());
		Optional<Tamanho> nomeTamanhoOptional = tamanhos.findByNomeIgnoreCaseOrPedaco(tamanho.getNome(), tamanho.getPedaco());
		
		//Optional<Tamanho> pedacoTamanhoOptional = tamanhos.findByPedaco(tamanho.getPedaco());
		
		if(nomeTamanhoOptional.isPresent()){
			if(nomeTamanhoOptional.get().getNome().equals(tamanho.getNome())){
				throw new TamanhoJaCadastradoException("Nome já cadastrado");	
			}
			if(nomeTamanhoOptional.get().getPedaco().equals(tamanho.getPedaco())){
				throw new TamanhoJaCadastradoException("Quantidade de pedaços já cadastrado");	
			}
				
		}
		
		/*if(pedacoTamanhoOptional.isPresent()){
			throw new TamanhoJaCadastradoException("Quantidade de pedaços já cadastrado");
		}*/
		
		
		return tamanhos.saveAndFlush(tamanho);
	}

}
