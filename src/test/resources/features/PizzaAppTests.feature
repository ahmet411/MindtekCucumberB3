@regression @smoke @ui @MB2P-122
Feature: Validating pizza application functionalities

  Scenario: Validating place order functionality
    Given user navigates to pizza application
    When user creates pizza order with data
      | Pizza        | Small 6 Slices - no toppings |
      | Toppings 1   | Mushrooms                    |
      | Toppings 2   | Extra cheese                 |
      | Quantity     | 1                            |
      | Name         | John Doe                     |
      | Email        | johndoe@gmail.com            |
      | Phone        | 123456789                    |
      | Payment Type | Cash on Pickup               |
    Then user validates that order is created with success message "Thank you for your order! TOTAL: 6.75 Small 6 Slices - no toppings"

