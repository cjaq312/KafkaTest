package com.jagan.kafka.mainTests;

import com.jagan.kafka.Producers.MessageProducer;

public class MessageProducerTest {

	public static void main(String[] args) {
		 MessageProducer producer = new MessageProducer();
		String topicName = "test";
		 producer.publishMessage(topicName, "this is test message");
		 producer.closeProducer();
	}

}
