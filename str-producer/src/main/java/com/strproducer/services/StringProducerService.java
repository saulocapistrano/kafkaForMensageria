package com.strproducer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Component
@RequiredArgsConstructor
public class StringProducerService {

	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public StringProducerService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String message) {
	    kafkaTemplate.send("str-topic", message).addCallback(
	        success -> {
	            if (success != null && success.getRecordMetadata() != null) {
	                System.out.println("Send message with success: " + message);
	                System.out.println("Partition: " + success.getRecordMetadata().partition() +
	                                   ", Offset: " + success.getRecordMetadata().offset());
	            }
	        },
	        error -> System.err.println("Error sending message: " + error.getMessage())
	    );
	}
}
