package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.UserRegistrationPage;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class RegistrationTestRunner extends Setup {
    @Test(description = "User Registration")
    public static void userRegistration() throws InterruptedException, IOException, ParseException {
        driver.findElement(By.partialLinkText("Register")).click();
        UserRegistrationPage user = new UserRegistrationPage(driver);
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email =("shabitalahi123+1015@gmail.com");
        String password = "1234";
        String phoneNumber = "0160"+ Utils.generateRandomNumber(1000000,9999999);
        String address = faker.address().fullAddress();


        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
//        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phoneNumber);
//        userModel.setAddress(address);

        user.userRegistration(userModel);


        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("Toastify__toast")));
        String successfulMessageActual= driver.findElement(By.className("Toastify__toast")).getText();
        System.out.println(successfulMessageActual);
        String successfulMessageExpected ="registered successfully!";


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName",firstName);
//        jsonObject.put("lastName",lastName);
        jsonObject.put("email",email);
        jsonObject.put("password",password);
        jsonObject.put("phoneNumber",phoneNumber);
//        jsonObject.put("address",address);
        Utils.saveUserData("./src/test/resources/users.json",jsonObject);
        Assert.assertTrue(successfulMessageActual.contains(successfulMessageExpected));
    }
}
