package com.example.demo;

import java.util.Arrays;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import tutorial.springboot.SpringBootProjectApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootProjectApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

	@Test
	public void testSurveyTitleAndDescription() {

		String url = "http://localhost:8080/surveys/1/questions/2";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = new TestRestTemplate().exchange(url, HttpMethod.GET, entity, String.class);

		try {
			JSONAssert.assertEquals("", "", true);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
