/**
 * 
 */
package br.com.pizzeria.service.event.bebida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.pizzeria.storage.FotoStorage;

/**
 * @author Silvan de Jesus
 *
 */
@Component
public class BebidaListener {

	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition = "#evento.temFoto() and #evento.novaFoto")
	public void  bebidaSalva(BebidaSalvaEvent evento){
		fotoStorage.salvar(evento.getBebida().getFoto());
	}
}
