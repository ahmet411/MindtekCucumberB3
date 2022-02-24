@regression @UI @ONT-113
Feature: Validating etsy titles

  Scenario Outline: Validating etsy separate page titles
    Given user navigates to Etsy application
    When user clicks on "<Section>" section
    Then user validates title is "<Title>"

    Examples:
      | Title                                        | Section                  |
      | Jewelry & Accessories \| Etsy                | Jewelry and Accessories  |
      | End of Year Sales Event \| Etsy              | End of Year Sales Event  |
      | Clothing & Shoes \| Etsy                     | Clothing and Shoes       |
      | Home & Living \| Etsy                        | Home and Living          |
      | Wedding & Party \| Etsy                      | Wedding and Party        |
      | Toys & Entertainment \| Etsy                 | Toys and Entertainment   |
      | Art & Collectibles \| Etsy                   | Art and Collectibles     |
      | Craft Supplies & Tools \| Etsy               | Craft Supplies and Tools |
      | The Etsy Holiday Gift Guide for 2021 \| Etsy | Gift and Gift Cards      |



