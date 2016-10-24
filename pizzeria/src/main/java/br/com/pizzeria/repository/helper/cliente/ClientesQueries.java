package br.com.pizzeria.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.pizzeria.model.Cliente;
import br.com.pizzeria.repository.filter.ClienteFilter;

public interface ClientesQueries {

	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);
	
	public Cliente buscarClientes(Long codigo);

}
