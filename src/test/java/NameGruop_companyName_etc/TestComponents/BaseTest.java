package NameGruop_companyName_etc.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public  WebDriver driver;
	public  LandingPage landingPage;
	    
	
	
	  public WebDriver initializaDriver() throws IOException {
		  
		  //properties class
		  
		 
		    Properties prop = new Properties();
		   FileInputStream fis = new FileInputStream("C:/Users/Hp/eclipse-workspace/SeleniumFrameworkDesign2/"
		   		+ "src/main/java/NameGruop_companyName_etc/SeleniumFrameworkDesign2/Resources/GlobalData.properties");
		    prop.load(fis);
		    String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser"); 
		   // prop.getProperty("browser");
		  
		  if(browserName.contains("chrome")) 
		  {
			  ChromeOptions options = new ChromeOptions(); 
			   System.setProperty("windriver.chrome.driver","C:/Users/Hp/eclipse-workspace/chromedriver-win64/chromedriver.exe");
			   if(browserName.contains("headlees")) 
			   {
			   options.addArguments("headless"); // this is for running with out the execution of the browsers
			   }
			   driver = new ChromeDriver(options);
			   driver.manage().window().setSize(new Dimension(1440,900)); // to run in full screen
		  }
	 
		   else if (browserName.equalsIgnoreCase("firefox"))
		   {
			  System.setProperty("windriver.gecko.driver", "C:/Users/Hp/eclipse-workspace/geckodrive/geckodriver.exe");
		      driver = new FirefoxDriver();
		   }
		   
		   else if (browserName.equalsIgnoreCase("edge"))
		   {
			   System.setProperty("windriver.edge.driver", "C:/Users/Hp/eclipse-workspace/edgedriver/msedgedriver.exe");
		       driver = new EdgeDriver();
		   }
		   
		   
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			//driver.get("https://rahulshettyacademy.com/client");
			driver.manage().window().maximize();
		  return driver;	
		   
	  }
	  
	  public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException {
			
			
			//reading json as String
		String jsonContent=	FileUtils.readFileToString(new File (filePath), StandardCharsets.UTF_8);
		
		   // String to HashMap - will need Jackson databid
		
		ObjectMapper  mapper = new ObjectMapper();
		 List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		 return data;
			 
		 
		}
	  
	  
	  public String getScreenshot(String testCaseName, WebDriver driver ) throws IOException {
			
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File("C:/Users/Hp/eclipse-workspace/SeleniumFrameworkDesign2/Screenshots"+testCaseName+".png");
			FileUtils.copyFile(source,file);
			return "C:/Users/Hp/eclipse-workspace/SeleniumFrameworkDesign2/Screenshots"+testCaseName+".png";
		}
		
	  
	  
	  @BeforeMethod(alwaysRun=true)
	  public LandingPage launchApplication() throws IOException {
		  
		  driver = initializaDriver();
		  landingPage = new LandingPage(driver);
		  landingPage.goTo();
		  return landingPage;
	  }
	  
	  @AfterMethod(alwaysRun=true)
	  public void tearDown() {
		  driver.close();
	  }
	 
}
