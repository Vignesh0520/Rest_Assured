package testcase;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.PatchUpdateUsers2_Payload;

public class API_Validation {

	@BeforeTest
	public void baseURI() {
		RestAssured.baseURI = "https://reqres.in/";
	}

	@Test
	public void getListOfUsersInPage2() {
		
		System.out.println("\nTestCase 1");
		RequestSpecification given = RestAssured.given();
		given.header("x-api-key", "reqres-free-v1");
		Response response = given.request(Method.GET, "api/users?page=2");

		int actualStatusCode = response.getStatusCode();
		int expectedStatusCode = 200; // 200 - OK
		Assert.assertEquals(actualStatusCode, expectedStatusCode, "GET - LIST USERS Fail");
		String responseBody = response.asPrettyString();
		System.out.println("Response Body: " + responseBody);
		System.out.println("TestCase 1 : getListOfUsersInPage2 PASSED\n");

	}

	@Test
	public void postCreateNewUser() {
		
		// pay load using HashMap
		System.out.println("\nTestCase 2");
		HashMap<String, String> payload = new HashMap<String, String>();
		payload.put("name", "VigneshRaja");
		payload.put("job", "SDET");

		RequestSpecification given = RestAssured.given();
		given.header("x-api-key", "reqres-free-v1");
		given.body(payload);
		Response response = given.request(Method.POST, "api/users");

		int actualStatusCode = response.getStatusCode();
		int expectedStatusCode = 201; // 201 - Created
		Assert.assertEquals(actualStatusCode, expectedStatusCode, "POST - CREATE USER Fail");
		String responseBody = response.asPrettyString();
		System.out.println("Response Body: " + responseBody);
		System.out.println("TestCase 2 : postCreateNewUser PASSED\n");

	}

	@Test
	public void putUpdateUser2() {

		// pay load using JSONObject
		System.out.println("\nTestCase 3");
		JSONObject payload = new JSONObject();
		payload.put("name", "VigneshRaja");
		payload.put("job", "Test Lead");

		RequestSpecification given = RestAssured.given();
		given.header("x-api-key", "reqres-free-v1");
		given.body(payload.toString());
		Response response = given.request(Method.PUT, "api/users/2");

		int actualStatusCode = response.getStatusCode();
		int expectedStatusCode = 200; // 200 - OK
		Assert.assertEquals(actualStatusCode, expectedStatusCode, "PUT - UPDATE USER2 Fail");
		String responseBody = response.asPrettyString();
		System.out.println("Response Body: " + responseBody);
		System.out.println("TestCase 3 : putUpdateUser2 PASSED\n");

	}

	@Test
	public void patchUpdateUsers2() {

		// pay load using POJO class
		System.out.println("\nTestCase 4");
		PatchUpdateUsers2_Payload patchUpdateUsers2_Payload = new PatchUpdateUsers2_Payload();
		patchUpdateUsers2_Payload.setName("VigneshRaja");
		patchUpdateUsers2_Payload.setJob("QA");

		RequestSpecification given = RestAssured.given();
		given.header("x-api-key", "reqres-free-v1");
		given.body(patchUpdateUsers2_Payload);
		Response response = given.request(Method.PATCH, "api/users/2");

		int actualStatusCode = response.getStatusCode();
		int expectedStatusCode = 200; // 200 - OK
		Assert.assertEquals(actualStatusCode, expectedStatusCode, "PUT - UPDATE USER2 Fail");
		String responseBody = response.asPrettyString();
		System.out.println("Response Body: " + responseBody);
		System.out.println("TestCase 4 : patchUpdateUsers2 PASSED\n");

	}

	@Test
	public void deleteUser2() {

		System.out.println("\nTestCase 5");
		RequestSpecification given = RestAssured.given();
		given.header("x-api-key", "reqres-free-v1");
		Response response = given.request(Method.DELETE, "api/users/2");

		int actualStatusCode = response.getStatusCode();
		int expectedStatusCode = 204; // 204 - No content
		Assert.assertEquals(actualStatusCode, expectedStatusCode, "DELETE - DELETE USER2 Fail");
		String responseBody = response.asPrettyString();
		System.out.println("Response Body: " + responseBody);
		System.out.println("TestCase 5 : deleteUser2 PASSED\n");

	}
	
	@Test
	public void postCreateNewUserUsingExternalFile() throws StreamReadException, DatabindException, IOException {

		// pay load using external file
		System.out.println("\nTestCase 6");
		File file = new File("src/test/resources/postCreateNewUserUsingExternalFile.json");
		ObjectMapper objectMapper = new ObjectMapper();
		PatchUpdateUsers2_Payload payload = objectMapper.readValue(file, PatchUpdateUsers2_Payload.class);
	
		RequestSpecification given = RestAssured.given();
		given.header("x-api-key", "reqres-free-v1");
		given.contentType("application/json");
		given.body(payload);
		Response response = given.request(Method.POST, "api/users");
		int actualStatusCode = response.getStatusCode();
		int expectedStatusCode = 201; // 201 - Created
		Assert.assertEquals(actualStatusCode, expectedStatusCode, "POST - CREATE USER Fail");
		String responseBody = response.asPrettyString();
		System.out.println("Response Body: " + responseBody);
		System.out.println("TestCase 6 : postCreateNewUserUsingExternalFile PASSED\n");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("API Validation completed");
	}
	
}
