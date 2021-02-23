package com.vytrack.step_definitions;

import com.vytrack.pages.DashboardPage;
import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class ContactsStepDefs {

// HOMEWORK 25. ( VIDEO SONUNDA VERILEN )
    @Given("the user logged in as {string}")
//    public void the_user_logged_in_as(String string) { string yerine  asagida oldugu gibi userType yazabiliriz
    public void the_user_logged_in_as(String userType) {
//  Once step def leri yazacagiz
//  go to login page
        Driver.get().get(ConfigurationReader.get("url"));
        //based on input enter that user information
        String username =null;
        String password =null;

        if(userType.equals("driver")){
            username = ConfigurationReader.get("driver_username");
            password = ConfigurationReader.get("driver_password");
        }else if(userType.equals("sales manager")){
            username = ConfigurationReader.get("sales_manager_username");
            password = ConfigurationReader.get("sales_manager_password");
        }else if(userType.equals("store manager")){
            username = ConfigurationReader.get("store_manager_username");
            password = ConfigurationReader.get("store_manager_password");
        }
        //send username and password and login
        new LoginPage().login(username,password);
    }

    @Then("the user should see following options")
//    public void the_user_should_see_following_options(io.cucumber.datatable.DataTable dataTable) { consuldan gelen hali bu.
//                                                  Bunu sileriz ve yerine asgidaki( List<String> menuOptions )satirini YAZARIZ
    public void the_user_should_see_following_options(List<String> menuOptions) {

        BrowserUtils.waitFor(3);
//        get the list of Webelement and covert them to list of string and assert
//        List<WebElement> menuOptions1 = new DashboardPage().menuOptions; // bunu bir alt satirdaki sekilde yazabiliriz
//        List<String> actualOptions = new DashboardPage().menuOptions;  //bunu bir alt satirdaki sekilde yazabiliriz
        List<String> actualOptions = BrowserUtils.getElementsText(new DashboardPage().menuOptions);

        Assert.assertEquals(menuOptions,actualOptions);
        System.out.println("menuOptions = " + menuOptions);
        System.out.println("actualOptions = " + actualOptions);
    }
// MAP
    @When("the user logs in using following credentials")
    public void theUserLogsInUsingFollowingCredentials(Map<String,String> userInfo) {
        System.out.println(userInfo);
//        use map information to login and also verify firstname and lastname
//        login with map info

            new LoginPage().login(userInfo.get("username"), userInfo.get("password"));
//verify firstname and lastname
      String actualName = new DashboardPage().getUserName();

      String expectedName = userInfo.get("firstname")+" "+userInfo.get("lastname");
      Assert.assertEquals(expectedName,actualName);
        System.out.println("expectedName = " + expectedName);
        System.out.println("actualName = " + actualName);

    }


}
