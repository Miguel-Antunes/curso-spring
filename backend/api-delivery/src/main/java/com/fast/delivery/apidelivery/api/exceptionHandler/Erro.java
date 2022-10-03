package com.fast.delivery.apidelivery.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
@JsonInclude(Include.NON_NULL)
@Data
public class Erro {
	
	private Integer status;
	private OffsetDateTime dataHora;
	private String tituloErro;
	private List<Campo> campos;
	
	@AllArgsConstructor
	@Getter
	public static class Campo {
		
		private String nome;
		private String mensagemErro;
	}

}
