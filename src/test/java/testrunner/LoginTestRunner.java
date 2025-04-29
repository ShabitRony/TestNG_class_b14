package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends Setup {
    @Test(priority = 1, description = "Admin tries to Login with wrong creds")
    public void loginWithWrongCreds(){
        LoginPage loginPage =new LoginPage(driver);
        loginPage.doLogin("admin@test.com","Wrongcreds");
        String messageActual = driver.findElement(By.tagName("p")).getText();
        String expectedMessage = "Invalid email or password";
        Assert.assertEquals(messageActual,expectedMessage);
        clearData();
    }
    public void clearData(){
        LoginPage loginPage =new LoginPage(driver);
        loginPage.txtEmail.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        loginPage.txtPassword.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
    }
    @Test(priority = 2,description = "Admin user Login",groups = "smoke")
    public void adminLogin() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        if(System.getProperty("email")!=null && System.getProperty("password")!=null){
        loginPage.doLogin(System.getProperty("email"),System.getProperty("password"));
        }else{
        loginPage.doLogin("admin@test.com","admin123");
        }
        Utils.getToken();
    }
    @Test(priority = 3, description = "Admin Logout",groups = "smoke")
    public void logout() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Thread.sleep(2000);
        loginPage.doLogout();
//        driver.quit();
    }
    @Test(priority=4,description = "User Login",groups = "smoke")
    public void userLogin() throws IOException, ParseException {

            JSONParser parser = new JSONParser();
            JSONArray jsonArray= (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
            JSONObject userObj =(JSONObject) jsonArray.get(jsonArray.size()-1);
            String email = userObj.get("email").toString();
            String password = userObj.get("password").toString();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.doLogin(email,password);

        }
    }

