Feature:My account page
Scenario: Click my account to log in
        Given a Chrome browser
        When I navigate to the home page
        And I click the my account button
        Then I will navigate to my account log in page
Scenario: My account page successfully login
            Given a Chrome browser
            When I navigate to my account page
            And a correct password
            And a correct username
            And I click the login with those credentials
            Then I can turn to a page showing Purchase History|Your Details|Your Downloads of my account
Scenario: Wrong password can't log in
            Given a Chrome browser
            When I navigate to my account page
            And a correct username
            And a incorrect password
            And I navigate to my account page
            And I click the login with those credentials
            Then I will get an error warning
Scenario: Wrong username can't log in
            Given a Chrome browser
            When I navigate to my account page
            And an incorrect username
            And a correct password
            And I navigate to my account page
            And I click the login with those credentials
            Then I will get an error warning
Scenario: Can't log in with nothing entered
            Given a Chrome browser
            When I navigate to my account page
            And I click the login with those credentials
            Then I will get a no input warning

