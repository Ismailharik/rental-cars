package com.example.notificationservice;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
public class NotificationServiceApplication {

	private JavaMailSender mailSender;

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}
	// topic name is the same name I used with orders-service
	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(OrderPlacedEvent orderPlacedEvent){// get order placed event as a method arg
		log.info("Received notification from Orders-service with order id - {}", orderPlacedEvent.getOrderNumber());
		sendEmail(orderPlacedEvent);
	}

	private void sendEmail(OrderPlacedEvent event) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ismailharik77@example.com");
		message.setTo("ismailforevyone@gmail.com");
		message.setSubject("New Order Notification");
		message.setText("Order has been placed with order number: " + event.getOrderNumber());

		mailSender.send(message);
		log.info("Email sent successfully to {}", message.getTo());
	}

}
