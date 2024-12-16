package com.example.BankAdmin;

import jakarta.mail.internet.MimeMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;

@SpringBootApplication
@EnableDiscoveryClient
public class BankAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAdminApplication.class, args);
	}

	@Bean
	public RestTemplate newTemplate()
	{
		return new RestTemplate();
	}

	@Bean
	public JavaMailSender mailSender()
	{
		return new JavaMailSender() {
			@Override
			public MimeMessage createMimeMessage() {
				return null;
			}

			@Override
			public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
				return null;
			}

			@Override
			public void send(MimeMessage... mimeMessages) throws MailException {

			}

			@Override
			public void send(SimpleMailMessage... simpleMessages) throws MailException {

			}
		};
	}

}
