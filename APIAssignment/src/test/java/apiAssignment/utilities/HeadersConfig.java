package apiAssignment.utilities;

import java.util.HashMap;
import java.util.Map;

public  class HeadersConfig {

	public static Map<String, String> defaultHeaders() {
		Map<String, String> defaultHeaders = new HashMap<>();
		defaultHeaders.put("Content-Type", "application/json");
		
		return defaultHeaders;
	}

}
