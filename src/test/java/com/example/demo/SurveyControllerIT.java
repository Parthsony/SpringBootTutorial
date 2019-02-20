package com.example.demo;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import tutorial.springboot.SpringBootProjectApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

	@LocalServerPort
	private int port;

	private TestRestTemplate testRestTemplate = new TestRestTemplate();

	private HttpHeaders headers = createHttpHeaders("user1", "secret1");

	@Test
	public void testSurveyTitleAndDescription() throws JSONException {

		String url = "http://localhost:" + port + "/surveys/1";

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		JSONAssert.assertEquals(
				"{title : \"General Questions\" , description : \"Survey about general knowledge questions asked to people\"}",
				response.getBody(), false);

	}

	private HttpHeaders createHttpHeaders(String userId, String password) {

		String auth = userId + ":" + password;
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
		String headerValue = "Basic " + new String(encodedAuth);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", headerValue);

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		return headers;
	}

}
