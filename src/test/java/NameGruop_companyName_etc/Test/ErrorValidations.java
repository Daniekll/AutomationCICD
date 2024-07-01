package NameGruop_companyName_etc.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.CartPage;
import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.LandingPage;
import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.ProductCatalogue;
import NameGruop_companyName_etc.TestComponents.BaseTest;
import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.CheckoutPage;
import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.ConfirmationPage;
import NameGruop_companyName_etc.TestComponents.Retry;

public class ErrorValidations extends BaseTest{

	
	
	@Test(groups = {"ErrorHandlig"},retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		
		
		//(String productName = "ZARA COAT 3";
		
	    
		//LandingPage landingPage = launchApplication();
		
		
		
		landingPage.loginApplication("dan@dan.com", "Daniellobto1@");
		Assert.assertEquals("Incorrect email or password." ,landingPage.getErrorMessage());
	
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		
		
		String productName = "ZARA COAT 3";
		
	    
		LandingPage landingPage = launchApplication();
		Thread.sleep(2000);
		ProductCatalogue productCatalogue =landingPage.loginApplication("dan@dan.com", "Daniellobato1@");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCard(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
