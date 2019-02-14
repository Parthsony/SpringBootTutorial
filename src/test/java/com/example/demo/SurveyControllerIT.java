package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import tutorial.springboot.SpringBootProjectApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootProjectApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

	@LocalServerPort
	private int port;
	
	@Test
	public void test() {
		
		String url = "http://localhost" + port + "/surveys/Survey1/questions/Question1";
		
		TestRestTemplate rt = new TestRestTemplate();
		rt.getForObject(url, String.class);
	}

}
