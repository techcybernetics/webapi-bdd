Feature: Smoke Test
Background:
Given user is in the login page

@login
Scenario: Check user is logged in to the website

When the user enter the login and password
Then the user is navigated to the login page