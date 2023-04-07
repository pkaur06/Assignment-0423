package assignment.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import assignment.TestComponents.BaseTest;
import assignment.pageobjects.CustomerPage;

import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;



public class TransactionsTest extends BaseTest {

	CustomerPage addCust;
	int actualBalance =0;

	@Test(dataProvider="getData",groups ={"Suite"})
	public void TransTest(HashMap<String, String> data) throws IOException, InterruptedException {
		addCust = landingPage.loginApplication();
		actualBalance= addCust.customerTransactions(data.get("Name"),data.get("Account"));
		
		int val = Integer.parseInt(data.get("Balance"));
		Assert.assertEquals(actualBalance,val);
	}




	//Data provider
	@DataProvider public Object[][] getData() throws IOException {

		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+
				"//src//test//java//assignment//data//TransactionsData.json"); 
		return new Object[][]
				{ {data.get(0)}}; }

}


