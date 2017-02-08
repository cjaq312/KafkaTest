package com.jagan.kafka.mainTests;

import com.jagan.kafka.Consumers.MessageConsumer;

public class MessageConsumerTest {
	public static void main(String[] args) {
		MessageConsumer producer = new MessageConsumer("test", "0");
		producer.consumeMessage();
	}
}
