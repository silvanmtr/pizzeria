package br.com.pizzeria.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pizzeria.model.Bebida;
import br.com.pizzeria.repository.Bebidas;
import br.com.pizzeria.service.event.bebida.BebidaSalvaEvent;
import br.com.pizzeria.service.exception.ImpossivelExcluirEntidadeException;
import br.com.pizzeria.storage.FotoStorage;

@Service
public class CadastroBebidaService {

	@Autowired
	private Bebidas bebidas;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
    private FotoStorage fotoStorage;
	
	@Transactional
	public void salvar(Bebida bebida) {
		
		bebidas.save(bebida);
		
		publisher.publishEvent(new BebidaSalvaEvent(bebida));
	}

	@Transactional
	public void excluir(Bebida bebida) {
		try {
			String foto = bebida.getFoto();
			
			bebidas.delete(bebida);
			bebidas.flush();
			fotoStorage.excluir(foto);
			
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível excluir a bebida.");
		}
	}	
}
