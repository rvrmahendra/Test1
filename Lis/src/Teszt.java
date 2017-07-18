import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ITest.class)
public class Teszt {
WebDriver driver;

@Test
public void f(){
	driver = new FirefoxDriver();
	
	
	WebListener w = new WebListener();
	EventFiringWebDriver d = new EventFiringWebDriver(driver);
	driver=d.register(w);
	driver.get("http://www.google.com");
	driver.findElement(By.id("fdw")).click();
}

public WebDriver getdriver(){
	
	return driver;
	
}


}
