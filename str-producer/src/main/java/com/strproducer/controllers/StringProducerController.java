package com.strproducer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strproducer.services.StringProducerService;

@RestController
@RequestMapping("/producers")
public class StringProducerController {

	private StringProducerService producerService;
	
	@Autowired 
    public StringProducerController(StringProducerService producerService) {
        this.producerService = producerService;
    }
	

	@PostMapping
	public ResponseEntity<?> sendMessage(@RequestBody String message){
		producerService.sendMessage(message);
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
	}
}
