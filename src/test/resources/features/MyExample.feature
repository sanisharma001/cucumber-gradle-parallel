@twoFeatureFile
Feature: Kehe Connect UAT Automation

  Background: I login to Connect Enterprise and navigate to Customer Management List Page
    Given the user navigates to Kehe Connect
    When I am signed in as an "IT" user
    Then I click "Sales" and "Manage Customers" from header menu option


  #############################
  ## kehe Connect Automation ##
  #############################

  @customerListNavigation @automation @prodSafe @customerManagement @CESmokeStable @firstScenario @myTest  @parallelTest
  Scenario: Verify Customer list
    Then  I verify user is on "Manage Customers" page
    Then I should be able to see the following columns:
      | Customer      |
      | AR Rep        |
      | DC            |
      | Business Area |
      | Store Type    |
      | Chain         |
      | Pricing Zone  |
      | Created       |
      | Territory     |
      | Status        |


  @customerListNavigation @automation @prodSafe @customerManagement @CESmokeStable @secondScenario  @parallelTest
  Scenario: Verify Customer list
    Then  I verify user is on "Manage Customers" page


  @customerListNavigation @automation @prodSafe @customerManagement @CESmokeStable @parallelTest  @ThirdScenario
  Scenario: Verify Customer list
    Then  I verify user is on "Manage Customers" page
