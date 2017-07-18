import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ITest implements ITestListener {

	Logger g=Logger.getLogger("Testlog");
	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("log4j.properties");
		g.info("Test finish");
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		g.info("Test started");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		g.info("onTestFailedButWithinSuccessPercentage" + arg0);
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		
		g.info("Test failure"+arg0);
		String methodName=arg0.getName().toString().trim();
    	takeScreenShot(methodName);
	}


    public void takeScreenShot(String methodName) {
    	//get the driver
    	Teszt f=new Teszt();
    	WebDriver driver = f.getdriver();
    	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in d drive with test method name 
            try {
				FileUtils.copyFile(scrFile, new File("C:\\sel\\Lis"+methodName+".png"));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		g.info("Test skip"+arg0.getTestName());
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
	g.info("Test started"+arg0.getName());
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
	g.info("Test sucess"+arg0);
	}

}
