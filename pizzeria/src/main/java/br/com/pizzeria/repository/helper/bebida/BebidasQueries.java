package br.com.pizzeria.repository.helper.bebida;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.pizzeria.model.Bebida;
import br.com.pizzeria.repository.filter.BebidaFilter;

public interface BebidasQueries {

	public Page<Bebida> filtrar(BebidaFilter filtro, Pageable pageable);
	
	public Bebida buscarBebidas(Long codigo);

}
