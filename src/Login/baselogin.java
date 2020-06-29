package Login;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class baselogin {

	
	public static RequestSpecification request;
	public static Response response;
	public static Properties obj = new Properties(); 
	public static  String TokenId;
	public static String TicketId;
	public static JsonPath abc;
	
	public static void fileload() throws IOException {
		
		 File f=new File("C:\\seleniumclass\\TrackitAPI\\src\\Login\\locator.properties");
		   FileInputStream fis =new FileInputStream(f);
		   obj.load(fis);
		}
	
	public static void login() {
		
		RestAssured.baseURI = obj.getProperty("url");

		request = RestAssured.given();

		request.body("scope=&grant_type=password&username=AUTOGROUP5\\AUTOSATECH5&password=welcome");
        response = request.request(Method.GET);
		System.out.println(response.statusCode());
		System.out.println(response.asString());
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		 TokenId= jsonPathEvaluator.get("access_token").toString();
		System.out.println(TokenId);
	}
	
	public static void createticket() {
		
		RestAssured.baseURI = obj.getProperty("createticket");

		request = RestAssured.given();

			request.header("Authorization", "Bearer " + TokenId);
			request.header("Content-Type", "application/json");
			
			
		    JSONObject obj=new JSONObject();
			obj.put("Ticket Summary","Test 1234");
			obj.put("Category", "Applications");
		    
			JSONObject pro=new JSONObject();
			pro.put("Properties", obj);
				
			request.body(pro.toJSONString());
		
	        response = request.request(Method.POST);
			System.out.println(response.statusCode());
			String a=response.asString();
			System.out.println(a);
			
			 abc = new JsonPath(a);
		    TicketId = abc.getString("Ticket.1.Value");
			
			System.out.println("Ticket ID =" +TicketId);
					
	}
	
}

