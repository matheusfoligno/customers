package com.matheus.customers.resourcers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheus.customers.dtos.CustomerRequest;
import com.matheus.customers.services.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerResource {
	
	private final CustomerService customerService;

	@PostMapping
	public ResponseEntity save(@RequestBody CustomerRequest request) {
		var customer = request.toEntity();
		customerService.save(customer);
		URI headerLocation = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.query("cpf={cpf}")
				.buildAndExpand(customer.getCpf())
				.toUri();
		
		return ResponseEntity.created(headerLocation).build();
	}
	
	@GetMapping(params = "cpf")
	public ResponseEntity getCustomerByCpf(@RequestParam("cpf") String cpf) {
		var customer = customerService.getByCpf(cpf);
		
		if (!customer.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(customer);
	}
}
