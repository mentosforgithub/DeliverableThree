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
import cucumber.api.java.en.And;
import static junit.framework.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
/**
 *
 * @author AsphaltPanthers
 */
public class StepDefinitions {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private final String HOME_PAGE = "http://store.demoqa.com/";
    private final String MY_ACCOUNT_PAGE ="http://store.demoqa.com/products-page/your-account/";
    private final String LOGEDIN_PAGE ="http://store.demoqa.com/products-page/your-account/?login=1";
    private final String IPAD_PAGE="http://store.demoqa.com/products-page/product-category/ipads/apple-ipad-6-32gb-white-3d/";
    private final String SHOPPINGCART_PAGE="http://store.demoqa.com/products-page/checkout/";
    private final String ALL_PRODUCT="http://store.demoqa.com/products-page/product-category/";
    
    @Given("a Chrome browser")
    public void openFirefox() {
        System.setProperty("webdriver.chrome.driver", "libs\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
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
    /**************Login-Scenario1**********/
    @And("I click the my account button")
    public void ClickMyAccount(){
        driver.findElement(By.xpath(".//*[@id='account']/a")).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    @Then("I will navigate to my account log in page")
    public void NavigateMyaccount(){
        assertEquals(driver.getCurrentUrl(),MY_ACCOUNT_PAGE);
    }
    /*****************Login-Scenario2*****************/
    @And("a correct username")
    public void CorrectUsername(){
        driver.findElement(By.id("log")).sendKeys("testuserliu");
    }
    @And("a correct password")
    public void CorrectPassword(){
        driver.findElement(By.id("pwd")).sendKeys("Test@lajbrc");
    }
    @When("I navigate to my account page")
    public void PageMyaccount(){
        driver.get(MY_ACCOUNT_PAGE);
    }
    @And("I click the login with those credentials")
    public void ClickLogin(){
        driver.findElement(By.xpath("//*[@id=\"login\"]")).submit();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\\\"ajax_loginform\\")));
    }
    @Then("I can turn to a page showing my account")
    public void ShowMyAccount(){
         //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         assertEquals(driver.getCurrentUrl(),LOGEDIN_PAGE);//compare 2 current urls
    }
    /*********Login-Scenario3**********/
    @And("a incorrect password")
    public void IncorrectPassword(){
        driver.findElement(By.id("pwd")).sendKeys("7894");
    }
    @Then("I will get an error warning")
    public void ErrorWarning(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals(driver.findElement(By.xpath("//*[@id=\"ajax_loginform\"]/p[1]")).getText(),"ERROR");
    }
    /************Login-Scenario4*************/
    @And("an incorrect username")
    public void IncorrectUsername(){
         driver.findElement(By.id("log")).sendKeys("testuserwang");
    }
    /***********Login-Scenario5*************/
    @Then("I will get a no input warning")
    public void NoIput(){
        String r =driver.findElement(By.className("response")).getText();
        System.out.println(r);
        assertEquals(r,"Please enter your username and password.");
        //assertEquals(driver.findElement(By.xpath("//*[@id=\"ajax_loginform\"]/p[1]")).getText(),"Please enter your username and password.");
    }
    /**************Shopping cart-Scenario1**********/
    @When("navigate to an ipad page")
    public void IpadPage(){
        driver.get(IPAD_PAGE);
    }
    @And("I click  the add item button")
    public void ClickAddButton(){
        driver.findElement(By.name("Buy")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"fancy_notification_content\"]/a[1]")).click();
    }
    @Then("I add an ipad in my shopping cart")
    public void AddIpad(){
        assertEquals(driver.findElement(By.className("count")).getText(),"1");
    }
    /**************Shopping cart-Scenario2****************/
    @Then("I will see the ipad I bought")
    public void BoughtIpad(){
        String s;
        s=driver.findElement(By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[2]/a")).getText();
        assertEquals(s,"Apple iPad 6 32GB (White, 3D)");
    }
    /**********Shopping  cart-Scenario3*****************/
    @And("I click remove button")
    public void RemoveItem(){
        driver.findElement(By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[6]/form/input[4]")).click();
    }
    @Then("the shopping cart should be empty")
    public void CleanShoppingcart(){
        assertEquals(driver.findElement(By.xpath("//*[@id=\"post-29\"]/div")).getText(),"Oops, there is nothing in your cart.");
    }
    /************Browse-Scenario1**************/
    @And("I click the picture button")
    public void ClickPictureButton(){
        driver.findElement(By.xpath("//*[@id=\"slide_menu\"]/a[1]")).click();
    }
    @Then("I will see the right picture")
    public void RightPicture(){
        String src = driver.findElement(By.xpath("//*[@id=\"slides\"]/div[1]/div[2]/a/img")).getAttribute("src");
        String s ="http://store.demoqa.com/wp-content/themes/mio/sp-framework/timthumb/timthumb.php?src=http://store.demoqa.com/wp-content/uploads/2014/03/magicmouse.png&h=500&w=500&zc=1&q=90&a=c&s=&f=&cc=&ct=";
        assertEquals(src,s);
    }
    /*************Browse-Scenario2****************/
    @And("I click all products")
    public void ClickAllProducts(){
        driver.findElement(By.xpath("//*[@id=\"menu-item-72\"]/a")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Then("I will see all products")
    public void AllProducts(){
        assertEquals(driver.getCurrentUrl(),ALL_PRODUCT);
    }
}
