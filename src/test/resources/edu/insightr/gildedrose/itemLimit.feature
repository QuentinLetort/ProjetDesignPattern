Feature: item quality limits
  # item quality is never under 0 and over 50 (except sulfuras)

  Scenario: test quality limits
    Given I have a new inventory
    Then the quality value of an item is over 0 and under 50
    When I update the inventory 60 times
    Then the quality value of an item is over 0 and under 50
