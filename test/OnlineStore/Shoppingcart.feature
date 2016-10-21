Feature:Shopping cart
Scenario: Add an item to the cart 
            Given a Chrome browser
            When navigate to an ipad page
            And I click  the add item button
            Then I add an ipad in my shopping cart

Scenario: Add an ipad to a cart and check
            Given a Chrome browser
            When navigate to an ipad page
            And I click  the add item button
            Then I will see the ipad I bought
Scenario: Remove item in a shopping cart
            Given a Chrome browser
            When navigate to an ipad page
            And I click  the add item button
            And I click remove button
            Then the shopping cart should be empty



