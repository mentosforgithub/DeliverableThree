/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineStore;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static junit.framework.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author AsphaltPanthers
 */
public class StepDefinitions {
    private WebDriver driver;
    
    private final String HOME_PAGE = "http://store.demoqa.com/";
    
    @Given("a Firefox browser")
    public void openFirefox() {
        System.setProperty("webdriver.gecko.driver", "libs\\geckodriver.exe");
        driver = new FirefoxDriver();
    }
    
    @When("I navigate to the home page")
    public void navigateHome() {
        driver.get(HOME_PAGE);
    }
    
    @Then("the title should be (.*)")
    public void checkPageTitle(String title) {
        assertEquals(title, driver.getTitle());
    }
    
    @After
    public void cleanUp() {
        driver.quit();
    }
}
