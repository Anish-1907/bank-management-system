package com.training.bms.exception;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String userId) {

        super(String.format("User with Id: %d - Not found", userId));
    } 

}
