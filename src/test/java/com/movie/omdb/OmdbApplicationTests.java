package com.movie.omdb;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;
import org.xml.sax.InputSource;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class OmdbApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private String contextPath = "/api/movies";

	@Test
	public void test200Json() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(restTemplate.getRootUri()).path(contextPath)
				.queryParam("title", "lord of the rings");

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		try {
			JSONObject json = new JSONObject(response.getBody());
			assertEquals(json.get("Response"), "True");
		} catch (JSONException e) {
			fail("Invalid Json recieved : " + e.getMessage());
		}
	}

	@Test
	public void test200Xml() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("accept", "application/xml");
		HttpEntity<?> entity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(restTemplate.getRootUri()).path(contextPath)
				.queryParam("title", "lord of the rings");

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Parse the content to Document object
		try {
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();

			String responseValue = xpath.evaluate("/Movie/response",
					new InputSource(new StringReader(response.getBody())));

			assertEquals(responseValue, "True");
		} catch (Exception e) {
			fail("Invalid xml recieved : " + e.getMessage());
		}

	}


	@Test
	public void test200WithYear() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(restTemplate.getRootUri()).path(contextPath)
				.queryParam("title", "lord of the rings").queryParam("year", "2001");

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		try {
			JSONObject json = new JSONObject(response.getBody());
			assertEquals(json.get("Year"), "2001");
		} catch (JSONException e) {
			fail("Invalid Json recieved : " + e.getMessage());
		}
	}

	@Test
	public void test200WithPlot() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(restTemplate.getRootUri()).path(contextPath)
				.queryParam("title", "lord of the rings").queryParam("plot", "full");
		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		try {
			JSONObject json = new JSONObject(response.getBody());
			assertTrue(json.getString("Plot").length() > 152);
		} catch (JSONException e) {
			fail("Invalid Json recieved : " + e.getMessage());
		}
	}

	//For sake of argument i skip validating all 403 responses.
	@Test
	public void test403NoTitle() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(restTemplate.getRootUri()).path(contextPath);;

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		try {
			JSONObject json = new JSONObject(response.getBody());
			assertEquals(json.get("Message"), "Movie name cannot be empty");
		} catch (JSONException e) {
			fail("Invalid Json recieved : " + e.getMessage());
		}

	}
}
