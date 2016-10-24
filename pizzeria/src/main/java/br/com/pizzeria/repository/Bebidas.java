package br.com.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pizzeria.model.Bebida;
import br.com.pizzeria.repository.helper.bebida.BebidasQueries;


public interface Bebidas extends JpaRepository<Bebida, Long>, BebidasQueries {
	
}
