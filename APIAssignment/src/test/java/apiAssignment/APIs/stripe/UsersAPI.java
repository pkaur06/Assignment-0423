package apiAssignment.APIs.stripe;

import static io.restassured.RestAssured.given;

import java.util.Map;

import apiAssignment.setUp.BaseTest;
import apiAssignment.utilities.Payload;
import apiAssignment.utilities.HeadersConfig;
import io.restassured.response.Response;


public class UsersAPI extends BaseTest{

	static Map<String, String> defaultHeaders = HeadersConfig.defaultHeaders();


	public static Response login() {

		Response response= given()
				.headers(defaultHeaders)
				.when().get(config.getProperty("userLoginEndPoint"))
				.then().assertThat().statusCode(200)
				.extract().response();
		return response;
	}



	//Create User
	public static String createNewUser(String id,String username, String fName,String lName, String email, String password,String phone, String userStatus) {

		String response= given().log().all()
				.headers(defaultHeaders)
				.body(Payload.User(id,username,fName,lName,email,password,phone,userStatus))
				.when().post(config.getProperty("addUserAPIEndPoint"))
				.then().assertThat().statusCode(200)
				.extract().response().asString();
		return response;  

	}


	//Update User	
	public static String updateUser(String id, String username, String fName,String lName, String email, String password,String phone, String userStatus) {

		System.out.print(config.getProperty("updateUserAPIEndPoint")+ username);
		String response= given().log().all()
				.headers(defaultHeaders)
				.body(Payload.User(id,username,fName,lName,email,password,phone,userStatus))
				.when().put(config.getProperty("updateUserAPIEndPoint")+ username)
				.then().assertThat().statusCode(200)
				.extract().response().asString();
		return response;  

	}

	//Delete User	
	public static Response deleteUser( String username ) {

		Response response= given().log().all()
				.headers(defaultHeaders)
				.when().delete(config.getProperty("updateUserAPIEndPoint")+ username)
				.then().assertThat().statusCode(200)
				.extract().response();
		return response;  

	}



}
