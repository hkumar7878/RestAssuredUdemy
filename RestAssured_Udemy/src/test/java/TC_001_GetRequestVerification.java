import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class TC_001_GetRequestVerification {
	
	
	//@Test
	public void verifyGet()
	{
		RestAssured.baseURI="https://samples.openweathermap.org";
		given()
			   .param("q", "London")
			   .param("appid", "b1b15e88fa797225412429c1c50c122a1")
			   .when()
			   .get("/data/2.5/weather")
			   .then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
	}
	
	
	@Test
	public void testJsonBody()
	{
		RestAssured.baseURI="https://samples.openweathermap.org";
		given()
			   .param("q", "London")
			   .param("appid", "b1b15e88fa797225412429c1c50c122a1").log().all()
			   .when()
			   .get("/data/2.5/weather")
			   //.then().body("sys.country", equalTo("GB"));
			   
			   // User json path editor to parse json : https://jsoneditoronline.org/
			   .then().assertThat().statusCode(200).and().body("weather[0].main", equalTo("Drizzle"))
			                 .and().body("weather[0].description",equalTo("light intensity drizzle"))
			                 .and().header("server","openresty/1.9.7.1");
	}

}
