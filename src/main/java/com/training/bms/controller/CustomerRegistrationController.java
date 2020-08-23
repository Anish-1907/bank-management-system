package com.training.bms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.bms.exception.GlobalException;
import com.training.bms.exception.UserNotFoundException;
import com.training.bms.model.CustomerRegistration;
import com.training.bms.model.LoginUser;
import com.training.bms.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class CustomerRegistrationController {

	@Autowired
	CustomerRepository customerRepo;

	@GetMapping("/bms/users")
	public Flux<CustomerRegistration> getAllUserDetails() {
		log.info("Get All Users method - Get User details");
		return customerRepo.findAll().log();
	}
	
	@GetMapping("/bms/users/{userId}")
	public Mono<ResponseEntity<CustomerRegistration>> getUserDetailsforUserId(@PathVariable Integer userId) {
		log.info("getUserDetailsforUserId method: {}",userId);
		return customerRepo.findById(userId)
				.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/bms/users")
	public Mono<ResponseEntity<CustomerRegistration>> saveUser(@Valid @RequestBody CustomerRegistration customerInfo) {
		log.info("saveUser method: {}",customerInfo.toString());
		return customerRepo.save(customerInfo)
				.map(customer-> new ResponseEntity<>(customer,HttpStatus.CREATED));
	}
	
	@PutMapping("/bms/users/{userId}")
	public Mono<ResponseEntity<CustomerRegistration>> updateCustomerDetails(@Valid @RequestBody CustomerRegistration customerInfo, @PathVariable Integer userId) {
		
		return customerRepo.findById(userId).flatMap(customer -> {
			customer.setAddress(customerInfo.getAddress());
			customer.setEmailAddress(customerInfo.getEmailAddress());
			customer.setContactNo(customerInfo.getContactNo());
			return customerRepo.save(customer);
		})
		.map(updatedCustomer -> new ResponseEntity<>(updatedCustomer, HttpStatus.OK))
		.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
/*	@PostMapping("/customers/login")
	public Mono<ResponseEntity<String>> getUserLoginDetails(@RequestBody LoginUser loginUser) {
		
		      return customerRepo.findUserLoginDetails(loginUser.getUserId())
		    		  .map(pwd -> {
		    			  ResponseEntity<String> str =  null;
		    			  if(pwd.equals(loginUser.getPwd())) {
		    				  str =  new ResponseEntity<>("Login Success", HttpStatus.OK);
		    			  }
		    			  else {
		    				  str =  new ResponseEntity<>("Login failure", HttpStatus.UNAUTHORIZED);
		    			  }
		    			  return str;
		    			  
		    		  })
		     .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
	}
	new ResponseEntity<>("Login Success",HttpStatus.OK)*/
	
		@PostMapping("/bms/users/login")
			public Mono<ResponseEntity<CustomerRegistration>> getUserLoginDetails(@RequestBody LoginUser loginUser) {
		      return customerRepo.findById(loginUser.getUserId())
		    		  .flatMap(result -> {
		    			if(result.getPassword().equals(loginUser.getPwd())) {
		    				//return customerRepo.findById(result.getUserId())
		    				//.map(result1 -> new ResponseEntity<>(result1,HttpStatus.OK));
		    				return Mono.just(new ResponseEntity<>(result,HttpStatus.OK));
		    			}
		    			else {
		    				//return Mono.just(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
		    				throw new GlobalException(HttpStatus.UNAUTHORIZED, "Username or Password - Not Valid");
		    			}
		    			
		    		 });
		      
	}
	
	
}
