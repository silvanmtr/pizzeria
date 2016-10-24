package br.com.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pizzeria.model.Pizza;
import br.com.pizzeria.repository.helper.pizza.PizzasQueries;


public interface Pizzas extends JpaRepository<Pizza, Long>, PizzasQueries {
	
}
