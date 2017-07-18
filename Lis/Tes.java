import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Tes {
	WebDriver driver;
  @Test
  public void f() {
	  
		driver.get("http://www.google.com");
		driver.findElement(By.id("lst-ib")).sendKeys("sel");
  }
}
