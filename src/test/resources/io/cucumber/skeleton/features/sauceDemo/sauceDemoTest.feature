@sauceDemo
Feature: To Test basic Sauce Demo validation
  Background:
    Given user is in the login page

  Scenario: Check user is logged in to the website

    When the user enter the login and password
    Then the user is navigated to the login page

    @lab
    Scenario Outline: Check user login with multiple users
      When the user enter the login "<userName>" and password "<password>"
      Then the user is navigated to the login page
      Examples:
      |userName     |password    |
      |standard_user|secret_sauce|
      #|problem_user |secret_sauce|

      @products
      Scenario: Validate products
        When the user enter the login and password
        Then the user validates the products available in the homepage
        |products                         |
        |Sauce Labs Backpack              |
        |Sauce Labs Bike Light            |
        |Sauce Labs Bolt T-Shirt          |
        |Sauce Labs Fleece Jacket         |
        |Sauce Labs Onesie                |
        |Test.allTheThings() T-Shirt (Red)|

