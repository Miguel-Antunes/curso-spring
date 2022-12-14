package com.fast.delivery.apidelivery.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fast.delivery.apidelivery.api.model.DestinatarioModel;
import com.fast.delivery.apidelivery.api.model.EntregaModel;
import com.fast.delivery.apidelivery.domain.model.Entrega;
import com.fast.delivery.apidelivery.domain.repository.EntregaRepository;
import com.fast.delivery.apidelivery.domain.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/api/entregas")
public class EntregaController {
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private SolicitacaoEntregaService solicitacaoEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
		return solicitacaoEntregaService.solicitar(entrega);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Entrega> listarTodos(){
		return entregaRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<EntregaModel> buscarEntrega(@PathVariable Long id){
		return entregaRepository.findById(id)
				.map((entrega)->{
					EntregaModel entregaModel = new EntregaModel();
					entregaModel.setId(entrega.getId());
					entregaModel.setNomeCliente(entrega.getCliente().getNome());
					entregaModel.setDestinatario(new DestinatarioModel());
					entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome());
					entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
					entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
					entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
					entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
					entregaModel.setTaxa(entrega.getTaxa());
					entregaModel.setStatus(entrega.getStatusEntrega());
					entregaModel.setDataPedido(entrega.getDataPedido());
					entregaModel.setDataFinalizacao(entrega.getDataFinalizacao());
					return ResponseEntity.ok(entregaModel);
				})
				.orElse(ResponseEntity.notFound().build());
	}

}
