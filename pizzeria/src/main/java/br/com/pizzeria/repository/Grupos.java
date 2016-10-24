package br.com.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pizzeria.model.Grupo;

public interface Grupos extends JpaRepository<Grupo, Long> {

}
