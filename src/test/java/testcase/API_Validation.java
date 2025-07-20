package testcase;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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

		RequestSpecification given = RestAssured.given();
		given.header("x-api-key", "reqres-free-v1");
		Response response = given.request(Method.GET, "api/users?page=2");

		int actualStatusCode = response.getStatusCode();
		int expectedStatusCode = 200; // 200 - OK
		Assert.assertEquals(actualStatusCode, expectedStatusCode, "GET - LIST USERS Fail");
		System.out.println("TestCase 1 : getListOfUsersInPage2 PASSED");

	}

	@Test
	public void postCreateNewUser() {

		// pay load using HashMap
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
		System.out.println("TestCase 2 : postCreateNewUser PASSED");

	}

	@Test
	public void putUpdateUser2() {

		// pay load using JSONObject
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
		System.out.println("TestCase 3 : putUpdateUser2 PASSED");

	}

	@Test
	public void patchUpdateUsers2() {

		// pay load using POJO class
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
		System.out.println("TestCase 4 : patchUpdateUsers2 PASSED");

	}

	@Test
	public void deleteUser2() {

		RequestSpecification given = RestAssured.given();
		given.header("x-api-key", "reqres-free-v1");
		Response response = given.request(Method.DELETE, "api/users/2");

		int actualStatusCode = response.getStatusCode();
		int expectedStatusCode = 204; // 204 - No content
		Assert.assertEquals(actualStatusCode, expectedStatusCode, "DELETE - DELETE USER2 Fail");
		System.out.println("TestCase 5 : deleteUser2 PASSED");

	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("API Validation completed");
	}
	
}
