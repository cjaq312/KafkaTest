package com.jagan.kafka.Consumers;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.errors.WakeupException;

public class MessageConsumer {
	KafkaConsumer<String, String> kafkaConsumer;
	String topic;

	public MessageConsumer(String topic, String groupId) {
		this.topic = topic;
		// Configure the Producer
		Properties configProperties = new Properties();
		configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "simple");

		setConsumer(new KafkaConsumer<String, String>(configProperties));
	}

	public KafkaConsumer<String, String> getConsumer() {
		return kafkaConsumer;
	}

	public void setConsumer(KafkaConsumer<String, String> consumer) {
		this.kafkaConsumer = consumer;
	}

	public void consumeMessage() {
		this.kafkaConsumer.subscribe(Arrays.asList(this.topic));

		try {
			while (true) {
				ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
				for (ConsumerRecord<String, String> record : records)
					System.out.println(record.value());
			}
		} catch (WakeupException ex) {
			System.out.println("Exception caught " + ex.getMessage());
		} finally {
			kafkaConsumer.close();
			System.out.println("After closing KafkaConsumer");
		}
	}
}
