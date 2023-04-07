package apiAssignment.utilities;
public class Payload {

	
	public static String User(String id,String usrName, String fName,String lName, String email, String password,String phone, String userStatus) {
		return "{\r\n"
				+ "    \"id\": "+id+",\r\n"
				+ "    \"username\": \""+usrName+"\",\r\n"
				+ "    \"firstName\": \""+fName+"\",\r\n"
				+ "    \"lastName\": \""+lName+"\",\r\n"
				+ "    \"email\": \""+email+"\",\r\n"
				+ "    \"password\": \""+password+"\",\r\n"
				+ "    \"phone\": \""+phone+"\",\r\n"
				+ "    \"userStatus\": "+userStatus+"\r\n"
				+ "}";
	}
	
		
}
