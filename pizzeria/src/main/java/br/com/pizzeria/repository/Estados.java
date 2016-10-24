package br.com.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pizzeria.model.Estado;

public interface Estados extends JpaRepository<Estado, Long> {

}
