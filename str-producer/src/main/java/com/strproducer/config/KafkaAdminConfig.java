package com.strproducer.config;

import java.util.HashMap;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class KafkaAdminConfig {

//	@Value("${spring.kafka.bootstrap-servers}")
	//private String bootstrapServers;

	public final KafkaProperties properties = new KafkaProperties();

	// configuração para comunicação com o kafka cluster

	@Bean
	public KafkaAdmin kafkaAdmim() {
		var configs = new HashMap<String, Object>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
		return new KafkaAdmin(configs);
	}

	// criação de uma instancia para tópico
	@Bean
	public KafkaAdmin.NewTopics topics() {
		return new KafkaAdmin.NewTopics(TopicBuilder.name("str-topic").partitions(2).replicas(1).build());
	}
}
