package Login;

import java.io.FileInputStream;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.internal.util.IOUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class CreateTicket20734 extends baselogin {
	

	
	@BeforeClass

	public void token() throws IOException {
		
		baselogin.fileload();
		baselogin.login();
		baselogin.createticket();
	}
	
	@Test
	public static void createdticketverify() {
		
        String category=abc.getString("Ticket.72.Value");
		String status=abc.getString("Ticket.97.Value");
		String title=abc.getString("Ticket.22.Value");
		
		
		System.out.println("Ticket categort=" +category);
		System.out.println("Ticket status=" +status);
		System.out.println("Ticket status=" +title);
	   
	  
	    Assert.assertTrue(category.equalsIgnoreCase("Applications"));
	    Assert.assertTrue(status.equalsIgnoreCase("Open"));
	    Assert.assertTrue(title.equalsIgnoreCase("Test 1234"));
	    
	    System.out.println("Test cases pass");
}
}