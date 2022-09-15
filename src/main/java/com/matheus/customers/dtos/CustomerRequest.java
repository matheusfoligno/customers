package com.matheus.customers.dtos;

import com.matheus.customers.entities.Customer;

import lombok.Data;

@Data
public class CustomerRequest {

	private String cpf;
	private String name;
	private Integer age;
	
	public Customer toEntity() {
		return new Customer(cpf, name, age);
	}
}
