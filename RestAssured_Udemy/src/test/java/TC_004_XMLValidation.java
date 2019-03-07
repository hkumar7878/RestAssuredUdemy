import org.testng.annotations.Test;

import files.ResunableMethods;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TC_004_XMLValidation {

	
	@Test
	public void testPostRequest() throws IOException
	{
		
		String postData=GenerateStringFormatResource("D:\\Project Work\\Selenium Automation Framework\\RestAssured_Udemy\\postdata.xml");
		RestAssured.baseURI="http://216.10.245.166";
		Response resp=given().
			   queryParam("key", "qaclick123")
			   .body(postData).when().post("/maps/api/place/add/xml")
			   	.then().assertThat().statusCode(200).and().contentType(ContentType.XML).extract().response();
			   	//.and()
			   	//.body("status", equalTo("OK"));
		 XmlPath x= ResunableMethods.rawToXML(resp);
		 System.out.println("Place id is -->" + x.get("response.place_id"));
	}
	
	public static String GenerateStringFormatResource(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
