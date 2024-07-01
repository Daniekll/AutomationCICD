package NameGruop_companyName_etc.data.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.CartPage;
import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.CheckoutPage;
import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.ConfirmationPage;
import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.LandingPage;
import NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects.ProductCatalogue;
import NameGruop_companyName_etc.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImpl extends BaseTest{

	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Eccomerce Page")
	  public void I_landed_on_Eccomerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	 @Given ("^Logged in with username (.+) and password (.+)$")
	  public void logged_in_username_and_password(String username, String password) throws InterruptedException {
		 productCatalogue =landingPage.loginApplication(username,password);
	 }
	
	 @When ("^I add product (.+) to Cart$")
	 public void I_am_product_to_card(String productName) throws InterruptedException {
		 List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCard(productName);
	 }
	 
	 @When ("^Checkout (.+) and submit the order$")
	 public void checkout_and_submit_the_order(String productName) throws InterruptedException {
		 CartPage cartPage = productCatalogue.goToCartPage();
			
			Boolean match = cartPage.VerifyProductDisplay(productName);
			Assert.assertTrue(match);
			CheckoutPage CheckoutPage = cartPage.goToCheckOut();
			CheckoutPage.selectCountry("india");
			confirmationPage = CheckoutPage.submitOrder();
	 }
	 
	
	 @Then ("{string} message displayed on ConfirmationPage")
	  public void message_displayed_on_ConfirmationPage(String string) {
		 String confirmationMessage = confirmationPage.getConfirmation();
		  Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
		  driver.close();
	 }
	 
	 @Then("{string} message is displayed")
	 public void message_is_displayed(String string) {
		 Assert.assertEquals(string,landingPage.getErrorMessage());
		 driver.close();
	 }
	 
}
