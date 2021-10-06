package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NewController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/hello")
	public String helloWorld() {
		Developer dev = new Developer();
		dev.setName("Puneet Arora");
		dev.setPhoneNumber("9999999999");
		return sendNotification(dev, "hi");
	}
	
	private String sendNotification(Developer dev, String message) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		Notification not = new Notification();
		not.setPhoneNumber(dev.getPhoneNumber());
		not.setMessage(message);
		HttpEntity<Notification> entity = new HttpEntity<Notification>(not, headers);

		return restTemplate.exchange("https://run.mocky.io/v3/fd99c100-f88a-4d70-aaf7-393dbbd5d99f", HttpMethod.POST,
				entity, String.class).getBody();

		// post request to the external API:

		// parse the json message
		// return the message
	}
}
