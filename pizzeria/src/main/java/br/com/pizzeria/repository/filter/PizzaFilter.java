package br.com.pizzeria.repository.filter;

import java.math.BigDecimal;

import br.com.pizzeria.model.Sabor;
import br.com.pizzeria.model.Tamanho;

public class PizzaFilter {

	private String nome;
	private Tamanho tamanho;
	private Sabor sabor;
	private BigDecimal valorDe;
	private BigDecimal valorAte;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	public BigDecimal getValorDe() {
		return valorDe;
	}

	public void setValorDe(BigDecimal valorDe) {
		this.valorDe = valorDe;
	}

	public BigDecimal getValorAte() {
		return valorAte;
	}

	public void setValorAte(BigDecimal valorAte) {
		this.valorAte = valorAte;
	}

}
