package com.training.bms.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.training.bms.model.CustomerRegistration;


public interface CustomerRepository extends ReactiveCrudRepository<CustomerRegistration, Integer>{
	
}
