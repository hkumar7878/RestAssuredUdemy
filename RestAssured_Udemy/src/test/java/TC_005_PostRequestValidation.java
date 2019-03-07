import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.ResunableMethods;
import files.payLoad;
import files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC_005_PostRequestValidation {
	
Properties prop=new Properties();
	
	@BeforeTest
	public void setConfig() throws IOException 
	{
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void testJsonBody1()
	{
		RestAssured.baseURI=prop.getProperty("HOST1");
		Response resp=given()
			   .param("q", "London")
			   .param("appid", "b1b15e88fa797225412429c1c50c122a1")
			   .when()
			   .get("/data/2.5/weather")
			   // User json path editor to parse json : https://jsoneditoronline.org/
			   .then().assertThat().statusCode(200).and().body("weather[0].main", equalTo("Drizzle"))
			                 .and().body("weather[0].description",equalTo("light intensity drizzle"))
			                 .and().header("server","openresty/1.9.7.1").extract().response();
		System.out.println("Response is-----------------------------------------> " + resp);
		
		JsonPath js=ResunableMethods.rawToJson(resp);
		int count= js.get("weather.size()");
		System.out.println("Size of weather array is -->" + count);
		
		
		for(int i=0;i<count;i++)
		{
			//String id =js.get("weather["+i+"].id");
			System.out.println("Id is --->" +js.get("weather["+i+"].main") );
			
		}
		
		
	}
}
