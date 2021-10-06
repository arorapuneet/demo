package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PagerServiceController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/createTeam")
	public boolean createTeam(@RequestParam(name = "request") Map<String, Object> request) {

		Team teamObj = (Team) request.get("team");
		List<Developer> developerList = (List<Developer>) request.get("developers");

		try {
			// add team to DB
//		save(); // getteamId 
		} catch (Exception e) {
			System.err.println("The team already exist");
			return false; // 500
		}
		for (Developer devloper : developerList) {
			// add developer to the team with team_id
		}
		return true;
	}

	@RequestMapping("/sendPager")
	public boolean sendPager(@RequestParam(name = "teamId") String teamId) {
		if (!teamExist(teamId)) {
			// check teamId exists
			System.err.println("Team doesnt exists");
			return false;
		}
		List<Developer> developerList = getDeveloperList(teamId); // get all the developers from
		int devNum = (int) (Math.random() * ((developerList.size() - 1) - 1 + 1) + 1);
		Developer dev = developerList.get(devNum);
		String alertMesage = sendNotification(dev, "random message");

		if (alertMesage == "") {
			return true;
		}
		return false;
	}

	private List<Developer> getDeveloperList(String teamId) {
		return null;
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

	private boolean teamExist(String teamId) {
		// Db Call
		return false;
	}
}
