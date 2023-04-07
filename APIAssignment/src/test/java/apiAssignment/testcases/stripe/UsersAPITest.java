package apiAssignment.testcases.stripe;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import apiAssignment.APIs.stripe.UsersAPI;
import apiAssignment.setUp.BaseTest;
import apiAssignment.utilities.DataUtil;
import apiAssignment.utilities.TestUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UsersAPITest extends BaseTest {

	static String Username;

	// login to app
	@Test
	public void loginTest() {
		Response response = UsersAPI.login();
		Assert.assertEquals(response.statusCode(), 200);

	}

	// Create User Test
	@Test(priority=1,dataProviderClass = DataUtil.class, dataProvider = "data",dependsOnMethods ={"loginTest"})
	public void createNewUserTest(Hashtable<String, String> data) {

		String response = UsersAPI.createNewUser(data.get("id"),data.get("userName"),data.get("firstName"),data.get("lastName"),data.get("email"),data.get("password"),data.get("phone"),data.get("userStatus"));
		JsonPath js= TestUtil.rawToJson(response);
		Username =js.getString("username");
		Assert.assertTrue(Username.equalsIgnoreCase(data.get("userName")));

	}

	// Update User Test 
	@Test(dependsOnMethods ={"createNewUserTest"},dataProviderClass = DataUtil.class, dataProvider = "data")
	public void updateUserTest(Hashtable<String, String> data) {

		String response = UsersAPI.updateUser(data.get("id"),data.get("userName"),data.get("firstName"),data.get("lastName"),data.get("email"),data.get("password"),data.get("phone"),data.get("userStatus"));
		JsonPath js= TestUtil.rawToJson(response);
		String actualFName=  js.getString("firstName");
		String actualUserName =  js.getString("username");
		Assert.assertEquals(actualFName.trim(),data.get("firstName"));
		Assert.assertEquals(actualUserName.trim(),data.get("userName"));

	} 

	// Delete User Test 
	@Test(dependsOnMethods = {"updateUserTest"})
	public void deleteUserTest() {

		Response response =UsersAPI.deleteUser(Username); 
		Assert.assertEquals(response.statusCode(), 200);
	}
}
