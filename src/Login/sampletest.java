package Login;

import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.util.JSONPObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class sampletest extends baselogin {
	
	
	
	@BeforeClass

	public void token() throws IOException {
		
		baselogin.fileload();
		baselogin.login();
	}
	
	@Test
	public void getid() {
		JsonPath jsonPathEvaluator = response.jsonPath();
		String id = jsonPathEvaluator.get("access_token").toString();
		System.out.println(id);

		RestAssured.baseURI = obj.getProperty("createticket");

	request = RestAssured.given();

		request.header("Authorization", "Bearer " + id);
		request.header("Content-Type", "application/json");
		
	
		JSONObject obj=new JSONObject();
		obj.put("Ticket Summary","Test");
		obj.put("Category", "Applications");
		JSONObject pro=new JSONObject();
		pro.put("Properties", obj);
			
		request.body(pro.toJSONString());
	
        response = request.request(Method.POST);
		System.out.println(response.statusCode());
		String a=response.asString();
		System.out.println(a);
		
		JsonPath abc = new JsonPath(a);
		String deal = abc.getString("Ticket.1.Value");
		String category=abc.getString("Ticket.72.Value");
		String status=abc.getString("Ticket.97.Value");
		String title=abc.getString("Ticket.22.Value");
		
		System.out.println("Ticket ID =" +deal);
		System.out.println("Ticket categort=" +category);
		System.out.println("Ticket status=" +status);
		System.out.println("Ticket status=" +title);
	    
		Assert.assertTrue(category.equalsIgnoreCase("Applications"));
		  Assert.assertTrue(status.equalsIgnoreCase("Open"));
		    Assert.assertTrue(category.equalsIgnoreCase("Test"));
		

	   
}
}

