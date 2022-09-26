package com.fast.delivery.apidelivery.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.fast.delivery.apidelivery.domain.model.Cliente;
import com.fast.delivery.apidelivery.domain.model.Entrega;
import com.fast.delivery.apidelivery.domain.model.StatusEntrega;
import com.fast.delivery.apidelivery.domain.repository.EntregaRepository;

@Service
public class SolicitacaoEntregaService {

	@Autowired
	private EntregaRepository entregaRepository;
	@Autowired
	private CatalogoClienteService clienteService;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
	Cliente cliente = clienteService.buscar(entrega.getCliente().getId());
	entrega.setCliente(cliente);	
	entrega.setStatusEntrega(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		return entregaRepository.save(entrega);
	}
}
