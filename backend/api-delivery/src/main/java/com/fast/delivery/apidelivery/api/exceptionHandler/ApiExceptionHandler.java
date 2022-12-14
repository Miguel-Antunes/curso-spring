package com.fast.delivery.apidelivery.api.exceptionHandler;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fast.delivery.apidelivery.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
			
		List<Erro.Campo> campos = new ArrayList<>();
		for(ObjectError erro : ex.getBindingResult().getAllErrors()) {
			String nomeCampo = ((FieldError) erro).getField();
			String mensagemErro = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			campos.add(new Erro.Campo( nomeCampo, mensagemErro));
		}
		
		Erro erro = new Erro();
			erro.setStatus(status.value());
			erro.setDataHora(OffsetDateTime.now());
			erro.setTituloErro("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente!");
			erro.setCampos(campos);
			return handleExceptionInternal(ex, erro, headers, status, request);
	}
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Erro erro = new Erro();
		erro.setStatus(status.value());
		erro.setDataHora(OffsetDateTime.now());
		erro.setTituloErro(ex.getMessage());
		
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
}
