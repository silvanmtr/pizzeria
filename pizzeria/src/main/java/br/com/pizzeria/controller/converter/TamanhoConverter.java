package br.com.pizzeria.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.pizzeria.model.Tamanho;

public class TamanhoConverter implements Converter<String, Tamanho> {

	@Override
	public Tamanho convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Tamanho cidade = new Tamanho();
			cidade.setCodigo(Long.valueOf(codigo));
			return cidade;
		}
		
		return null;
	}

}
