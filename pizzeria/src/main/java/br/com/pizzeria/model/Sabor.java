package br.com.pizzeria.model;

public enum Sabor {
	DOCE("Doce"),
	SALGADA("Salgada");
	
	private String descricao;
	
	
	Sabor(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
