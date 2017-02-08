package com.jagan.kafka.Producers;

import java.util.List;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;


public class MessageProducer {
	private static Producer<String, String> producer;

	public MessageProducer() {
		// Configure the Producer
		Properties configProperties = new Properties();
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.ByteArraySerializer");
		configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
		
//		com.jagan.analyzer.services.PayloadSerializer
		setProducer(new KafkaProducer<String, String>(configProperties));
	}

	public Producer<String, String> getProducer() {
		return producer;
	}

	public static void setProducer(Producer<String, String> producer) {
		MessageProducer.producer = producer;
	}

	public void publishMessage(String topicName, String message) {
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, message);
		producer.send(record);
	}

	public void closeProducer() {
		producer.close();
	}

	public void publishMessage(String topicName, List<String> message) {
		for (String i : message) {
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, i);
			producer.send(record);
		}
	}

}
