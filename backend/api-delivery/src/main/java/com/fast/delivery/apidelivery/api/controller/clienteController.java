package com.fast.delivery.apidelivery.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fast.delivery.apidelivery.domain.model.Cliente;
import com.fast.delivery.apidelivery.domain.repository.ClienteRepository;
import com.fast.delivery.apidelivery.domain.service.CatalogoClienteService;

@RestController
@RequestMapping("/api/clientes")
public class clienteController {
	
	@Autowired
	private ClienteRepository repository; 
	
	@Autowired
	private CatalogoClienteService service;

	@GetMapping
	public List<Cliente> listarTodos() {
		return repository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
		
		return repository.findById(id).map(
				(cliente)-> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente) {
		return service.salvarCliente(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
		if(!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(id);
		cliente = service.salvarCliente(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
		if(!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		service.deletarCliente(id);
		return ResponseEntity.noContent().build();
	}


}
