package assignment.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import assignment.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;


	public static Properties config = new Properties();

	private FileInputStream fis;



	public WebDriver initializeDriver() throws IOException

	{

		try { 	
			fis = new FileInputStream(System.getProperty("user.dir")  +"\\src\\main\\java\\assignment\\resources\\GlobalData.properties");

		} catch (FileNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace(); }

		try { config.load(fis); // return userToken;

		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace(); }



		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :config.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//full screen
			driver.manage().deleteAllCookies();

		} 

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
				StandardCharsets.UTF_8);

		//String to HashMap- Jackson Databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;


	}

	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";


	}

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {

		driver = initializeDriver(); 
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;


	}



	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}
}
