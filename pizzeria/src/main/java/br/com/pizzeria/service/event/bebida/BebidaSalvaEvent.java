/**
 * 
 */
package br.com.pizzeria.service.event.bebida;

import org.springframework.util.StringUtils;

import br.com.pizzeria.model.Bebida;

/**
 * @author Silvan de Jesus
 *
 */
public class BebidaSalvaEvent {

	private Bebida bebida;

	public BebidaSalvaEvent(Bebida bebida) {
		super();
		this.bebida = bebida;
	}

	public Bebida getBebida() {
		return bebida;
	}
	
	public boolean temFoto(){
		return !StringUtils.isEmpty(bebida.getFoto());
	}
	
	public boolean isNovaFoto(){
		return bebida.isNovaFoto();
	}
}
