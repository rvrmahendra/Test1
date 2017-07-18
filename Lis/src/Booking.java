import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Booking {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("ignoreProtectedModeSettings", true);
		WebDriver driver=new InternetExplorerDriver(capabilities);
		
		FileWriter fw=new FileWriter(new File("code.txt"));

		//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://kbes.eticketing.my");
		driver.findElement(By.name("txtUID")).sendKeys("pje-03");
		driver.findElement(By.name("txtUPass")).sendKeys("Ctb2890768");
		driver.findElement(By.xpath("//table[@class='BlackText']/tbody/tr[7]/td/input[1]")).click();

		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alt=driver.switchTo().alert();
		alt.accept();
		Thread.sleep(500);
		for(String handle:driver.getWindowHandles())
		{
			driver.switchTo().window(handle);
		}
		System.out.println("title ="+driver.getTitle());
		Thread.sleep(500);
		driver.switchTo().frame("frameCalender");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String datestring = dateFormat.format(date); 
		String[] p=datestring.split("-");
		int year=Integer.parseInt(p[0]);
		int month=Integer.parseInt(p[1]);
		int cdate=Integer.parseInt(p[2]);
		String mvalue=driver.findElement(By.className("DisBox")).getAttribute("value");
		int mvalue1=Integer.parseInt(mvalue);
		//date need to implement

		driver.switchTo().defaultContent();
		driver.switchTo().frame("frameInfo");

		Select counter =new Select(driver.findElement(By.className("InputBox")));
		List<WebElement> l =counter.getOptions();
		System.out.println("Source Size:"+l.size());
		for(WebElement sou:l)
		{
			System.out.println("*******************************************************************");
			fw.write("\n*******************************************************************");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frameInfo");

			System.out.println("Source: "+sou.getText());
			fw.write("\nSource: "+sou.getText());
			
			counter.selectByVisibleText(sou.getText());
			driver.switchTo().defaultContent();
			driver.switchTo().frame("frameDestination");

			List<WebElement> dest=driver.findElements(By.className("DestButton"));
			int destCount=dest.size();
			System.out.println("no.of Destinations:"+destCount);
			fw.write("\nno.of Destinations:"+destCount);
			
			if(destCount==0){

				continue;
			}

			
			int i=0;
			while(i<destCount)
			{
				for(WebElement ds:dest)
				{
					driver.switchTo().defaultContent();
					driver.switchTo().frame("frameDestination");
					
					System.out.println("===============================================");
					fw.write("\n=================================================");
					System.out.println("Destination name:"+ds.getAttribute("value"));
					fw.write("\nDestination name:"+ds.getAttribute("value"));
					
					ds.click();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("frameTime");
					List<WebElement> time=driver.findElements(By.className("DestButton2"));

					for(WebElement ti:time)
					{
						driver.switchTo().defaultContent();
						driver.switchTo().frame("frameTime");
						ti.click();
						
						System.out.println("--------------------------------------------");
						fw.write("\n--------------------------------------------");

						System.out.print("Time :"+ti.getAttribute("value")+"\t");
						fw.write("\nTime :"+ti.getAttribute("value")+"\t");

						driver.switchTo().defaultContent();
						driver.switchTo().frame("frameSeat");

						List<WebElement> seat=driver.findElements(By.className("Button2"));	
						System.out.println("Available Seats: "+seat.size());
						fw.write("Available Seats: "+seat.size());

						
					}

					i++;
				}//

				if(i==15)
				{
					driver.switchTo().defaultContent();
					driver.switchTo().frame("frameDestination");
					List<WebElement> nxt=driver.findElements(By.className("pageButton"));
					if(nxt.size()==0)
					{
						break;
					}
					else
					{
						nxt.get(0).click();
					}

					dest=driver.findElements(By.className("DestButton"));
					destCount=destCount+dest.size();
					System.out.println("no.of Destinations(updated):"+destCount);
					fw.write("\nno.of Destinations(updated):"+destCount);
				}
			}

		}
		fw.close();
		//driver.close();
	}
	

}
