import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.payLoad;
import files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC_003_DeleteReqeust {
	
	
	
	Properties prop=new Properties();
	
	@BeforeTest
	public void setConfig() throws IOException 
	{
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\files\\env.properties");
		prop.load(fis);
	}
	@Test
	public void deletePlace() 
	{
	
		RestAssured.baseURI=prop.getProperty("HOST");
		Response response=given().
			   queryParam("key", prop.getProperty("KEY"))
			   .body(payLoad.getPostData()).when().post(resources.placePostData())
			   .then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
			   .and()
			   .body("status", equalTo("OK")).extract().response();
		
		
		String responseString=response.asString();
		System.out.println("Response string is ----->" + responseString);
		
		
		// Step 2: Get the place id from above response and delete it
		
		
		// Convert the above response string into Json
		
		JsonPath js= new JsonPath(responseString);
		String placeID=js.get("place_id");
		System.out.println("Place id  is --->" + placeID);
		
		
		// Step 3: Delete the above request
		
		given().queryParam("key", "qaclick123")
		.body("{" +
		"\"place_id\":\""+ placeID+"\""+
				"}").when().post("/maps/api/place/delete/json").then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).body("status", equalTo("OK"));
		
		
		
		
		
		
		
	}

}
