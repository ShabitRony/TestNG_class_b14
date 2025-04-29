package testrunner;

import config.Setup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.LoginPage;
import utils.Utils;

import javax.print.attribute.ResolutionSyntax;
import java.io.IOException;

public class UserProfileTestRunner extends Setup {
    @BeforeTest
   public void setAuth() throws IOException, ParseException, InterruptedException {
//       LoginPage loginPage = new LoginPage(driver);
//       loginPage.doLogin("admin@test.com","admin123");
        Utils.setAuth(driver);
   }
   @Test(priority = 1,description = "User edit by admin")
   public void editUserInfo() throws InterruptedException {
       LoginPage loginPage = new LoginPage(driver);
       Thread.sleep(5000);
//       driver.navigate().to("https://dailyfinance.roadtocareer.net/user/a5e9a71c-9642-4c44-b91f-e8154c23d106");
       driver.navigate().to("https://dailyfinance.roadtocareer.net/user/713c2e37-5019-4ea3-bf2e-299eae0c3ed8");
//       driver.get("https://dailyfinance.roadtocareer.net/user/713c2e37-5019-4ea3-bf2e-299eae0c3ed8");
       loginPage.button.get(1).click();
       WebElement txtFristName = driver.findElement(By.name("firstName"));
       txtFristName.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
       txtFristName.sendKeys("User");
       loginPage.button.get(2).click();
       Thread.sleep(2000);
       driver.switchTo().alert().accept();
   }
//   public void uploadImage(){
//        LoginPage
//   }
}
