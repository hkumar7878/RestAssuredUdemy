package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ResunableMethods {
	
	
	public static XmlPath rawToXML(Response res)
	{
		String response= res.asString();
		System.out.println("String is --->" + response);
		XmlPath xp=new XmlPath(response);
		return xp;
	}
	
	public static JsonPath rawToJson(Response res)
	{
		String response= res.asString();
		System.out.println("String is --->" + response);
		JsonPath json=new JsonPath(response);
		return json;
	}

}
