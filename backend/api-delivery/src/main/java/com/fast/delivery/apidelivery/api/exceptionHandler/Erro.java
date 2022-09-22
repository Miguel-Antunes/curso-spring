package com.fast.delivery.apidelivery.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class Erro {
	
	private Integer status;
	private LocalDateTime dataHora;
	private String tituloErro;
	private List<Campo> campos;
	
	@AllArgsConstructor
	@Getter
	public static class Campo {
		
		private String nome;
		private String mensagemErro;
	}

}
