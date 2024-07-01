package NameGruop_companyName_etc.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.CartPage;
import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.LandingPage;
import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.OrderPage;
import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.ProductCatalogue;
import NameGruop_companyName_etc.TestComponents.BaseTest;
import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.CheckoutPage;
import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.ConfirmationPage;

public class SubmitOrderTest extends BaseTest{

	
	public String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void submitOrder(HashMap <String,String> input) throws IOException, InterruptedException {
		
		
		
		
	    
		LandingPage landingPage = launchApplication();
		Thread.sleep(1000);
		ProductCatalogue productCatalogue =landingPage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCard(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage CheckoutPage = cartPage.goToCheckOut();
		CheckoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = CheckoutPage.submitOrder();
		String confirmationMessage = confirmationPage.getConfirmation();
		
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANYOU FOR THE ORDER."));
		
		
	}
	
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() throws InterruptedException {
		
		ProductCatalogue productCatalogue =landingPage.loginApplication("dan@dan.com", "Daniellobato1@");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	@DataProvider 
	public Object [][] getData() throws IOException {
	/*	HashMap<String,String> map = new HashMap<String,String>();
		map.put("email","dan@dan.com");
		map.put("password","Daniellobato1@");
		map.put("productName","ZARA COAT 3");
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email","shetty@gmail.com");
		map1.put("password","Iamking@000");
		map1.put("productName","ADIDAS ORIGINAL");  */
		
		List<HashMap<String,String>> data = getJsonDataToMap("C:/Users/Hp/eclipse-workspace/SeleniumFrameworkDesign2/"
				+ "src/test/java/NameGruop_companyName_etc/data/PurchaseOrder.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
}
