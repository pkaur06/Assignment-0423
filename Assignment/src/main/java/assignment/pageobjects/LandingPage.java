package assignment.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import assignment.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	String URL ="https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";


	public LandingPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}


	public CustomerPage loginApplication()
	{ 		
		driver.get(URL);
		CustomerPage addCust = new CustomerPage(driver);
		return addCust;
	}

	public void goTo()
	{
		driver.get(URL);
	}
















}
