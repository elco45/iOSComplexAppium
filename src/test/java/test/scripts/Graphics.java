package test.scripts;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class Graphics {

    private IOSDriver driver;
    private Wait<WebDriver> wait;

    @Before
    public void startApp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "10.3"); //Replace this with your Android version
        caps.setCapability("deviceName", "iPhone 6"); //Replace this with your simulator/device 
        caps.setCapability("app", "http://appium.github.io/appium/assets/UICatalog7.1.app.zip"); //Replace this with app path in your system
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
    }

    @After
    public void quitApp() {
        driver.quit();
    }

    @Given("^The list of elements in main page$")
    public void The_list_of_elements_in_main_page() {

    }

    @When("^Click on \"([^\"]*)\" button$")
    public void Click_on_element_button(String arg1) throws InterruptedException {
        if (arg1.equals("Action Sheets")) {
            driver.findElementByAccessibilityId("Action Sheets").click();
        } else if (arg1.equals("Text View")) {
            Dimension dimensions = driver.manage().window().getSize();
            Double screenHeightStart = dimensions.getHeight() * 0.5;
            int scrollStart = screenHeightStart.intValue();
            Double screenHeightEnd = dimensions.getHeight() * 0.2;
            int scrollEnd = screenHeightEnd.intValue();
            driver.swipe(0, scrollStart, 0, scrollEnd, 2000);
            driver.findElementByAccessibilityId("Text View").click();
            Thread.sleep(5000);
        }
    }

    @Then("^I can view a \"([^\"]*)\" element")
    public void I_can_view_a_element_element(String arg1) {
        Assert.assertEquals(true, driver.findElement(By.xpath("//*[1]//*[2]//*[1]//*[2]//*[1]//*[1]//*[1]")).isDisplayed());
    }
}
