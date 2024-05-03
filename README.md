# A website testing framework example based on Selenium and TestNG

## Disclaimer
THIS CODE IS PROVIDED AS IS AND NOT GUARANTEE TO WORK  

## How to run
1. Build the executable jar file using the following command:
        `mvn clean install` or `mvn clean install -DskipTests`
2. Copy the created "uber" jar file **_qamission-web-example.jar_** from _target_ folder into _bin_ folder
3. Execute the tests using run_test.sh (on linux based computers) or using run_test.bat (on windows machines).

**Note: if you are working with a linux based system, you can run: `deploy.sh` command that combines the steps above.**

## Description
This code presents an example of a web site testing framework.
It demonstrates a simple but robust stracture of code organization. 
The framework is using Page Object design pattern, Selenium and TestNG frameworks.

## Tests
Initially, this framework was build as a hiring assessment for one of my clients.
The below are requirements:
Below are the steps to automate.

Goto – https://www.loblaws.ca/

1. Search for apples and sort the search results from highest price to lowest price.
   Verify: Confirm that the web site has sorted the price correctly (you can load more results and
   check for all search results or can check for the first result search page)
2. Search for apples and use the Price Reduction option under Filter By &gt; Promotion to sort the
   search by sale badges. Verify: Each product: The amount on the badge and the price reduction match, and that the price in kg is equivalent to the price in lbs.
3. As a new user to the site, add an item to your cart.
   Verify: Item was added successfully. The site should ask you to pick a store, so please perform
   all necessary operations which will allow you to add item into the cart once user is created &amp;
   logged in to the user account.
4. From the home page, click on “Change Location” or “Choose a location” if selecting location first  time, search for “QUEEN STREET WEST” and select it for pickup, Go to location details by clicking the link present on same page.
   Verify: It is displaying correct store locations details. 
5. Continuous from above step, now click on “View store Flyer &gt;” link. Verify: Page is displaying correct store flyer details


