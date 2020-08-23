package com.training.bms;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.training.bms.model.CustomerRegistration;
import com.training.bms.repository.CustomerRepository;

import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@AutoConfigureWebTestClient
public class CustomerRegistrationControllerTests {
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	CustomerRepository customRepo;

	
	public List<CustomerRegistration> data(){
		 List<CustomerRegistration> customers = new ArrayList<>();
		CustomerRegistration c1 = new CustomerRegistration();
		CustomerRegistration c2 = new CustomerRegistration();
		c1.setUserName("Vijesh");
		c1.setName("Vijesh");
		c1.setPassword("viju1234");
		c1.setAddress("No.55, Peter stre");
		c1.setState("TamilNadu");
		c1.setCountry("India");
		c1.setEmailAddress("viju@abc.com");
		c1.setPan("12345678");
		c1.setContactNo("+91-994183345");
		c1.setAccounttype("SAVINGS");
		c1.setDob(null);
		c2.setUserName("Karthi");
		c2.setName("Karthi");
		c2.setPassword("viju1234");
		c2.setAddress("No.55, Peter stre");
		c2.setState("TamilNadu");
		c2.setCountry("India");
		c2.setEmailAddress("viju@abc.com");
		c2.setPan("12345678");
		c2.setContactNo("+91-994183345");
		c2.setAccounttype("SAVINGS");
		c2.setDob(null);
		customers.add(c1);
		customers.add(c2);
		return customers;
	}
	
	@Before
	public void setup() {
		customRepo.deleteAll()
		.thenMany(Flux.fromIterable(data()))
		.flatMap(customRepo::save)
		.doOnNext(customer -> {
			System.out.println("Customer:::"+customer);})
		.blockLast();
		
    }
	
	@Test
	public void getAllUsersTest() {
		webTestClient.get().uri("http://localhost:8081/bms/users")
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(CustomerRegistration.class)
		.hasSize(2);
		}

}
