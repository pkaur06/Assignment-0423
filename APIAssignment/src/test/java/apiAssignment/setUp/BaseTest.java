package apiAssignment.setUp;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import apiAssignment.utilities.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BaseTest {
	
	public static Properties config = new Properties();
	private FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\testdata.xlsx");
	
	@BeforeSuite
	public void setUp() {
		
		
		
		try {
			fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
		
		 } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			config.load(fis);
			 // return userToken;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RestAssured.baseURI=config.getProperty("baseURI");
		//RestAssured.basePath=config.getProperty("basePath");
		  

		
	}
	
	@AfterSuite
	public void tearDown() {
		
		
	}

}
