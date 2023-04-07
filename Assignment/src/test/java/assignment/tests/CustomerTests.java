package assignment.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import assignment.TestComponents.BaseTest;
import assignment.pageobjects.CustomerPage;

import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;



public class CustomerTests extends BaseTest {

	CustomerPage addCust;


	@Test(dataProvider="getData", groups ={"Suite"}) 
	public void CustomerTest(HashMap<String, String> data) throws IOException, InterruptedException {
		boolean customerExists = false;
		boolean deletedCustomer =false;
		addCust = landingPage.loginApplication();
		addCust.addNewCustomer(data.get("FirstName"), data.get("LastName"), data.get("PostCode"));

		//Create users
		String ActualResult = addCust.getAlertText();
		addCust.OK_Alert();

		Assert.assertTrue(ActualResult.contains("Customer added successfully with customer id"));
		String addedUserDetails = data.get("FirstName") + " " + data.get("LastName") + " " + data.get("PostCode");

		//verify users
		customerExists = addCust.validateCustomer(addedUserDetails);
		Assert.assertTrue(customerExists,"User " +addedUserDetails +" is added sucessfully in Customers Table");

		//delete users
		String delUser = data.get("FirstName")+ " " + data.get("LastName")+ " " + data.get("PostCode");
		String user1 = "Jackson Frank L789C349";
		String user2 = "Christopher Connely L789C349";

		if (delUser.contains(user1)||delUser.contains(user2))	{
			deletedCustomer = addCust.deleteCustomer(delUser);
			Assert.assertTrue(deletedCustomer,"User is deleted sucessfully inside Customers Table");
		}
	}


	//Data provider
	@DataProvider public Object[][] getData() throws IOException {

		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+
				"//src//test//java//assignment//data//CustomerData.json"); 
		return new Object[][]
				{ {data.get(0)}, {data.get(1)},{data.get(2)}, {data.get(3) },{data.get(4)}, {data.get(5)},{data.get(6)}}; }



}
