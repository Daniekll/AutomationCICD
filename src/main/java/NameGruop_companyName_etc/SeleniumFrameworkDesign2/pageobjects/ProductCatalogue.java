package NameGruop_companyName_etc.SeleniumFrameworkDesign2.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import NameGruop_companyName_etc.SeleniumFrameworkDesign2.AbstarctCompenent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
		@FindBy(css=".ng-animating")
	WebElement spinner;
	
		
	By productBy = By.cssSelector(".mb-3");
	By addToCard = By.cssSelector(".card-body button:last-of-type");
	By toastMessage =By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		WaitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText()
				.equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCard(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCard).click();	
		WaitForElementToAppear (toastMessage);
		WaitForElementToDisappear (spinner);
		
	}
	

}
