package com.matheus.customers.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matheus.customers.entities.Customer;
import com.matheus.customers.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;
	
	@Transactional
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Optional<Customer> getByCpf(String cpf) {
		return customerRepository.findByCpf(cpf);
	}
}
