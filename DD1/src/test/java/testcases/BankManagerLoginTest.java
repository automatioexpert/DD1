package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;

public class BankManagerLoginTest extends TestBase {

	@Test
	public void loginAsBankManager() {
		//driver.findElement(By.cssSelector("button[ng-click='manager()']")).click();
		String btnManager="//button[@ng-click='manager()']";
		click(btnManager);
		//System.out.println("Login is successful");
		test.log(LogStatus.INFO, "Login is successful");
		test.log(LogStatus.INFO, "I am the Top Expert on the Planet..Thank God!!");
	}

}
