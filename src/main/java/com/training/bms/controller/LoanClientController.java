package com.training.bms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.training.bms.model.Loans;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class LoanClientController {
	
	
	WebClient webClient = WebClient.create("http://localhost:8082");
	
	@GetMapping("/bms/loans/users/{userId}")
	public Flux<Loans> getAllLoanDetailsByUserId(@PathVariable String userId){
		log.info("Inside client All loan details"+userId);
		return webClient.get().uri("/users/loans/"+userId)
				.retrieve()
				.bodyToFlux(Loans.class).log();
	}
	
	@PostMapping("/bms/loans")
	public Mono<Loans> createLoan(@RequestBody Loans loans){
		log.info("Inside client All loan details"+loans.toString());
		return webClient.post().uri("/loans")
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(loans), Loans.class)
				.retrieve()
				.bodyToMono(Loans.class).log();
	}
	
	

}
