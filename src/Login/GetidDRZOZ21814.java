package Login;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class GetidDRZOZ21814 extends baselogin {

	
	
@BeforeClass

public void token() throws IOException {
	
	baselogin.fileload();
	baselogin.login();
	
	baselogin.createticket();
}

	
	@Test
	public void getid() {
	/*	JsonPath jsonPathEvaluator = response.jsonPath();
		String id = jsonPathEvaluator.get("access_token").toString();
		System.out.println(id);
*/
	    RestAssured.baseURI = obj.getProperty("getticketbyid");

	//	RestAssured.baseURI= "http://vw-pun-trt-qa02.dsl.bmc.com/TrackIt/WebApi/tickets";
		
	request = RestAssured.given();

	request.header("Authorization", "Bearer " + baselogin.TokenId);
		// request.body("scope=&grant_type=password&username=AUTOGROUP5\\AUTOSATECH5&password=welcome");
		response = request.request(Method.GET);
	response = request.get("/{id}",baselogin.TicketId);
	
	
		System.out.println(response.statusCode());
		String a=response.asString();
		System.out.println(a);
		
		JsonPath abc = new JsonPath(a);
	    String getTicketId = abc.getString("Ticket.1.Value");
		
   System.out.println("id of ticket=" +getTicketId);
		Assert.assertTrue(TicketId.equalsIgnoreCase(getTicketId));
		
		System.out.println("pass");
	}
}
