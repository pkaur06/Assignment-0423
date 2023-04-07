package apiAssignment.utilities;

import org.json.JSONObject;

import apiAssignment.listeners.ExtentListeners;
import io.restassured.path.json.JsonPath;

public class TestUtil {
	
	
	
	public static boolean jsonHasKey(String json,String key) {
		
		JSONObject jsonObject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validating the presence of Key : "+key);
		
		return jsonObject.has(key);
		
	}

	
	public static String getJsonKeyValue(String json, String key) {
		
		JSONObject jsonObject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validating Value of Key : "+key);
		
		return jsonObject.get(key).toString();
		
	}
	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js =new JsonPath(response);
		return js;
	}
	
}
