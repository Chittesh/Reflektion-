package testCases;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import Reflektion.org.Reflektion.BasePage;
import Reflektion.org.Reflektion.EnvironmentURLS;
import Reflektion.org.Reflektion.StaticData;
import io.restassured.response.Response;

/**
 * @author chicharles
 * @Description :Test cases for Post Request
 *
 */
public class PostRequest {

	@Test()
	public void verifyPostRequest() {
		System.out.println("****************************************************************");
		System.out.println("Executing verifyPostRequest Test Case");

		BasePage objBasePage = new BasePage();
		String jsonBody = "{\r\n" + "\"title\": \"foo\",\r\n" + "\"body\": \"bar\",\r\n" + "\"userId\": 1\r\n" + "}";
		System.out.println("JSON Body is : " + jsonBody);

		Response response = objBasePage.getResponseForPost(jsonBody, EnvironmentURLS.getpostsUrl());
		System.out.println("Status Code of Response is : " + response.getStatusCode());
		System.out.println("Verfying Status Code");
		Assert.assertEquals(response.getStatusCode(), StaticData.status_201,
				"Verify Status : " + response.getStatusCode());

		JsonParser p = new JsonParser();
		JsonElement objJsonElement = p.parse(response.asString());

		HashMap<String, String> hmObject = new HashMap<String, String>();
		hmObject = objBasePage.getKeysMehthod(objJsonElement);
		System.out.println("Printing out all values from the response schema");
		objBasePage.printOutHashMap(hmObject);

		System.out.println("Verifying the response");
		Assert.assertEquals(hmObject.get("title"), "\"foo\"", "Verify Title");
		Assert.assertEquals(hmObject.get("body"), "\"bar\"", "Verify Body");
		Assert.assertEquals(hmObject.get("userId"), "1", "Verify userId");
		Assert.assertEquals(hmObject.get("id"), "101", "Verify id");

		System.out.println("Completed Executing verifyPostRequest Test Case");
		System.out.println("****************************************************************");

	}

}
