package com.fast.delivery.apidelivery.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fast.delivery.apidelivery.domain.exception.NegocioException;
import com.fast.delivery.apidelivery.domain.model.Cliente;
import com.fast.delivery.apidelivery.domain.repository.ClienteRepository;

@Service
public class CatalogoClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvarCliente(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
		.stream()
		.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com esse email!");
		}
			return clienteRepository.save(cliente);		
	}
	@Transactional
	public Cliente buscar(Long id){
		return clienteRepository.findById(id)
		.orElseThrow(()-> new NegocioException("Cliente não encontrado!"));
	}
	@Transactional
	public void deletarCliente(Long id) {
		 clienteRepository.deleteById(id);
	}
}
