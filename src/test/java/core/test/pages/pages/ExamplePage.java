package core.test.pages.pages;

import com.google.common.base.Function;
import core.setup.Config;
import core.test.pages.base.PageObjectBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static core.utilities.Tools.logger;

public class ExamplePage extends PageObjectBase {

  @FindBy(xpath = "//img[@title='KeHE']")
  private WebElement logo;

  @FindBy(css = "div.search-input-wrapper > input")
  private WebElement searchInput;

  @FindBy(css = "div.video-player-container.player-container")
  private WebElement neatGif;

  @FindBy(css = "button.search-button")
  private WebElement search;

  @FindBy(className = "grid-gfy-item")
  private List<WebElement> gifs;

  @FindBy(id = "username")
  private WebElement userNameTxt;

  @FindBy(css = "button.login-button")
  private WebElement nextButton;

  @FindBy(id = "password")
  private WebElement passwordTxt;

  @FindBy(css = "button[value='login'")
  private WebElement loginBtn;

  @FindAll(@FindBy(xpath = ".//th"))
  List<WebElement> tableHeader;

  @FindBy(xpath = "//div[@class='manage-list-page-title-layout']")
  WebElement pageTitle;

  @FindBy(xpath = "//a[contains(.,'Sales')]")
  WebElement salesHeaderMenu;



  @Override
  public void trait() {
    assertDisplayed(logo, 5);
  }

  @Override
  public void navigateHere() {
    loadEnv(Config.getEnv());
    trait();
  }

  public void openNeatGif() {
    waitForNotStale(gifs.get(0), 5);
    click(gifs.get(0));

    /**
     * DO NOT USE THIS. It is only meant to show the gif that we found and clicked on as a visual
     * since it is so fast :)
     */
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void setUsernamePasswordRole(String userRole) {
    String username = "Auto.it.kehe@gmail.com";
    String password = "password";
    if (userRole.equalsIgnoreCase("IT")) {
      signIn(username, password);
    }
  }

  private void signIn(String userName, String password) {
    userNameTxt.sendKeys(userName);
    nextButton.click();
    if (passwordTxt.isDisplayed()) {
      passwordTxt.sendKeys(password);
    }
    loginBtn.click();
    logger().traceEntry();
  }

  public List<String> getStringList(List<WebElement> elements) {
    return elements.
            stream().
            map(WebElement::getText).
            collect(Collectors.toList());
  }

  public void isColumnNameDisplayedOnSPMPage(List<String> colHeader){
    List<String> headerNameList =getStringList(tableHeader);
    int counter = 0;
    for (String var : colHeader) {
      Assert.assertTrue("Actual columns did not match with expected column:" + var + "Actual Column:" + headerNameList.get(counter), headerNameList.contains(var));
      counter++;
    }
  }

  public void verifyManageCustomersPage(String pageHeader) throws InterruptedException {
    Thread.sleep(4000);
    Assert.assertEquals("Page title is not displayed", pageTitle.getText(), pageHeader);
  }

  public void clickManageCustomerSubmenu(String header, String subHeader) throws InterruptedException {

    waitForPageLoad();
    Thread.sleep(3000);
    if (header.equals("Sales") && subHeader.equals("Manage Customers")) {
      salesHeaderMenu.click();
      String SubHeader1 = salesHeaderMenu.getText();
      if (SubHeader1.equals(header)) {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@class='k-link k-menu-link ng-star-inserted'][contains(.,'"+subHeader+"')]")).click();
      }
    }

  }

  public void waitForPageLoad(){
    new WebDriverWait(driver, 30)
            .until((Function<WebDriver, Boolean>) driver1 -> String
                    .valueOf(executeJS("return document.readyState"))
                    .equals("complete"));
  }

  public String executeJS(String jsCode) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    return (String) js.executeScript(jsCode);
  }

}
