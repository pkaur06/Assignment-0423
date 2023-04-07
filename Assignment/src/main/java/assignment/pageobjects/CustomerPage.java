package assignment.pageobjects;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import assignment.AbstractComponents.AbstractComponent;

public class CustomerPage extends AbstractComponent {

	WebDriver driver;
	boolean matched;
	boolean custDeleted;
	boolean sumFlag;
	Bank obj =new Bank();

	public CustomerPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".btn.btn-lg.tab[ng-class='btnClass1']")
	WebElement addCustomerBtn;

	@FindBy(css = "input[placeholder='First Name']")
	WebElement firstNameTxt;

	@FindBy(css = "input[placeholder='Last Name']")
	WebElement lastNameTxt;

	@FindBy(xpath = "//input[@placeholder='Post Code']")
	WebElement postCodeTxt;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement createCustomerBtn;

	@FindBy(css = (".btn.btn-lg.tab[ng-class='btnClass3']"))
	WebElement customersTab;

	@FindBy(css = ("tbody"))
	WebElement tableBody;

	@FindBy(css = ("tr"))
	WebElement tableRow;

	//Customer txr
	@FindBy(xpath = "//button[normalize-space()='Customer Login']")
	WebElement customerLoginBtn;

	@FindBy(id = "userSelect")
	WebElement userDrpdown;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement custLogin;

	@FindBy (id ="accountSelect")
	WebElement accountDrpdown;

	@FindBy (css =".btn.btn-lg.tab[ng-class='btnClass2']")
	WebElement depositTab;

	@FindBy (css = "input[placeholder='amount']")
	WebElement amount;

	@FindBy (css = "button[type='submit']")
	WebElement submitFund;

	@FindBy (css =".btn.btn-lg.tab[ng-class='btnClass3']")
	WebElement withdrawTab;

	@FindBy (xpath = "//button[normalize-space()='Withdraw']")
	WebElement withdrawFund;




	//Add New Customer	
	public void addNewCustomer(String firstName, String lastName, String Postcode) throws InterruptedException {

		loginAsManager();

		addCustomerBtn.isDisplayed();
		addCustomerBtn.click();
		firstNameTxt.clear();
		firstNameTxt.sendKeys(firstName);
		lastNameTxt.clear();
		lastNameTxt.sendKeys(lastName);
		postCodeTxt.clear();
		postCodeTxt.sendKeys(Postcode);

		Actions ac = new Actions(driver);
		ac.moveToElement(createCustomerBtn).click().build().perform();

		waitForAlertToAppear();

	}

	public  void clickwithJS(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}


	public String getAlertText()	{
		// Switch the driver context to the alert
		Alert alertDialog = driver.switchTo().alert();

		// Get the alert text
		String alertText = alertDialog.getText();
		return alertText;

	}


	// Click the OK button on the alert.
	public void  OK_Alert()  {
		Alert alertDialog = driver.switchTo().alert();
		alertDialog.accept();
	}


	// Verify Users function
	public boolean validateCustomer(String expectedUserDetails) throws InterruptedException {

		customersTab.click();
		tableBody.isDisplayed();
		List<WebElement> Rows = tableBody.findElements(By.tagName("tr"));
		//To find that user details are present inside the table
		for(WebElement Row : Rows) { 
			if((Row.getText()+" ").contains(expectedUserDetails)) {
				matched =true;
			}	
		}

		return matched ;

	}

	//Delete users function
	public boolean deleteCustomer(String expectedUserDetails) throws InterruptedException {

		String rowText= null;

		customersTab.click();
		tableBody.isDisplayed();
		Actions actions = new Actions(driver);
		//scroll down the table
		actions.moveToElement(tableBody).perform();

		List<WebElement> rows = tableBody.findElements(By.tagName("tr"));

		for(int i=1;i<rows.size();i++)
		{
			rowText= driver.findElement(By.xpath("//tbody/tr["+i+"]")).getText();
			if(rowText.contains(expectedUserDetails)) {
				driver.findElement(By.xpath("//tbody/tr["+i+"]/td[5]/button[1]")).click();
			}
			// find that user details are no longer available in table after deletion
			for(WebElement ro : rows) { 
				//return flag if user details are not available
				if(!((ro.getText()+" ").contains(expectedUserDetails))) {
					custDeleted =true;
				}
			}
		}
		return custDeleted;  

	}


	public int customerTransactions(String userName, String accountNumber) throws InterruptedException {

		customerLoginBtn.click();
		Select drpUserName =new Select(userDrpdown);
		drpUserName.selectByVisibleText(userName);

		custLogin.click();
		Select drpAccountNum = new Select(accountDrpdown);
		drpAccountNum.selectByVisibleText(accountNumber);
		//Credit
		depositTab.click();
		amount.isDisplayed();
		depositFund(userName,"50000",50000);
		//Withdrawal
		withdrawTab.click();
		amount.isDisplayed();
		withdrawFund(userName, "3000",3000);
		withdrawFund(userName, "2000",2000);
		//Credit
		depositTab.click();
		depositFund(userName,"5000",5000);
		//Withdrawl
		withdrawTab.click();
		amount.isDisplayed();
		withdrawFund(userName, "10000", 10000);
		amount.isDisplayed();
		withdrawFund(userName, "15000", 15000);
		//Credit
		depositTab.click();
		depositFund(userName,"1500",1500);

		if (obj.total == 26500) {
			sumFlag= true;
		}

		return obj.total;
	}

	public void depositFund(String userName, String sum, int fund ) throws InterruptedException {
		amount.clear();
		amount.sendKeys(sum);
		submitFund.click();
		obj.deposit(userName, fund);
	}

	public void withdrawFund(String userName, String sum, int fund ) throws InterruptedException {
		amount.clear();
		amount.sendKeys(sum);
		withdrawFund.submit();
		obj.withdrawn(userName, fund);
	}

}
