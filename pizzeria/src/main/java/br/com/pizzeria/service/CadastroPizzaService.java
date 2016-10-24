package br.com.pizzeria.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pizzeria.model.Pizza;
import br.com.pizzeria.repository.Pizzas;
import br.com.pizzeria.service.event.bebida.PizzaSalvaEvent;
import br.com.pizzeria.service.exception.ImpossivelExcluirEntidadeException;
import br.com.pizzeria.storage.FotoStorage;

@Service
public class CadastroPizzaService {

	@Autowired
	private Pizzas pizzas;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
    private FotoStorage fotoStorage;
	
	@Transactional
	public void salvar(Pizza pizza) {
		
				
		pizzas.save(pizza);
		
		publisher.publishEvent(new PizzaSalvaEvent(pizza));
	}

	@Transactional
	public void excluir(Pizza pizza) {
		try {
			String foto = pizza.getFoto();
			
			pizzas.delete(pizza);
			pizzas.flush();
			fotoStorage.excluir(foto);
			
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível excluir a pizza.");
		}
	}	
}
