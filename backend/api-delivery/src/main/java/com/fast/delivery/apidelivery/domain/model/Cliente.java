package com.fast.delivery.apidelivery.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@Column
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;
	
	@Column
	@NotBlank
	@Size(max = 20)
	private String telefone;
}
