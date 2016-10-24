/**
 * 
 */
package br.com.pizzeria.service.event.bebida;

import org.springframework.util.StringUtils;

import br.com.pizzeria.model.Pizza;

/**
 * @author Silvan de Jesus
 *
 */
public class PizzaSalvaEvent {

	private Pizza pizza;

	public PizzaSalvaEvent(Pizza pizza) {
		super();
		this.pizza = pizza;
	}

	public Pizza getPizza() {
		return pizza;
	}
	
	public boolean temFoto(){
		return !StringUtils.isEmpty(pizza.getFoto());
	}
	
	public boolean isNovaFoto(){
		return pizza.isNovaFoto();
	}
}
