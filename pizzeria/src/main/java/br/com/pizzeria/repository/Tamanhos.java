package br.com.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pizzeria.model.Tamanho;

import java.util.Optional;

public interface Tamanhos extends JpaRepository<Tamanho, Long> {

	public Optional<Tamanho> findByNomeIgnoreCase(String nome);
	public Optional<Tamanho> findByNomeIgnoreCaseOrPedaco(String nome, Integer pedaco);
}
