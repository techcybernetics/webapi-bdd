Feature: FizzBuzz game play

  Background: Creating FizzBuzz Object
    Given Create a FizzBuzz game play
  @fizz
  Scenario: Play the FizzBuzz game to get Fizz

    When I play with number 5
    Then the result is Fizz

  Scenario: Play the FizzBuzz game to get Buzz

    When I play with number 3
    Then the result is Buzz

  @fizzBuzz
  Scenario: Play the FizzBuzz game to get FizzBuzz

    When I play with number 15
    Then the result is FizzBuzz


    @dataTest
    Scenario Outline: Play the FizzBuzz game
      When I play with number <num>
      Then the results are "<result>"
      Examples:
      |num |   result         |
      | 5  |   Fizz           |
      | 3  |   Buzz           |
      | 15 |   FizzBuzz       |
      | 45 |   Fizz           |

