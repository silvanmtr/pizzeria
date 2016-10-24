package br.com.pizzeria.repository.helper.pizza;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.pizzeria.model.Pizza;
import br.com.pizzeria.repository.filter.PizzaFilter;

public interface PizzasQueries {

	public Page<Pizza> filtrar(PizzaFilter filtro, Pageable pageable);
	
	public Pizza buscarPizzas(Long codigo);

}
