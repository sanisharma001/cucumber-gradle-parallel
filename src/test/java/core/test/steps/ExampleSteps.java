package core.test.steps;

import core.test.pages.pages.ExamplePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ExampleSteps {

  private ExamplePage page;

  public ExampleSteps(ExamplePage page) {
    this.page = page;
  }

  @Given("the user navigates to Kehe Connect")
  public void theUserNavigatesToTheSite() {
    page.navigateHere();
  }

  @Given("^I am signed in as an? \"([^\"]*)\" user")
  public void theUserSignIn(String userRole) {
    if(userRole.equalsIgnoreCase("IT"))
      page.setUsernamePasswordRole(userRole.toString());
  }

  @Then("^I should be able to see the following columns:$")
  public void verifyColumns(DataTable colHeader)
  {
    page.isColumnNameDisplayedOnSPMPage(colHeader.asList(String.class));

  }



  @Then("^I click? \"([^\"]*)\" and \"([^\"]*)\" from header menu option")
  public void clickManageCustomerOption(String header, String subHeader) throws InterruptedException {
    page.clickManageCustomerSubmenu(header,subHeader);

  }

  @Then("^I verify user is on? \"([^\"]*)\" page")
  public void verifyManageCustomersPage(String pageHeader) throws InterruptedException {
    page.verifyManageCustomersPage(pageHeader);

  }


  @Then("verifies {string} is displayed")
  public void verifiesIsDisplayed(String elementField) {
    page.assertDisplayed(page.getElement(elementField), 5);
  }

  @And("opens a Neat GIF")
  public void opensANeatGIF() {
    page.openNeatGif();
  }

  @And("enters {string} text into {string} on ExamplePage")
  public void entersTextIntoOnExamplePage(String text, String elementField) {
    waitsSToVerifyIsDisplayedOnExamplePage(2,elementField);
    page.getElement(elementField).sendKeys(text);
  }

  @And("clicks {string} on ExamplePage")
  public void clicksOnExamplePage(String elementField) {
    page.click(page.getElement(elementField));
  }

  @Then("waits {int}s to verify {string} is displayed on ExamplePage")
  public void waitsSToVerifyIsDisplayedOnExamplePage(int waitSec, String elementField) {
    page.assertDisplayed(page.getElement(elementField), waitSec);
  }
}
