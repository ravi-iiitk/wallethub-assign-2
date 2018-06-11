Feature: Review Insurance Company

Scenario: To Verify User able to submit review for insurance company. 
Given I have opened wallethubg website
When I am on wallethub login page
And I enter credentials and hit login button
|user_id			 |password	   |
|rsk.ravi@gmail.com  |W@1letHubPwd |
Then I must be able to login 
And I must see my home page
When I naviagte to Insurance Page
|insurance_company_url 					   			 |	
|http://wallethub.com/profile/test_insurance_company/|
Then I should see the insurance page
When I try to enter the rating
|star_rating|
|4          |
Then It should take me to rating page.
When I select the policy type
|policy_type    |	
|Health			|
And I give star ratings
|star_rating|
|4          |
And I enter the review comment
When I submit the review 
Then It should get added
When I move to my profile page
Then I must see review is listed in my activities
When I move to rating page
|user_name  |
|rsk_ravi   |
Then I must see the created review
When I hit logout
Then I must be logged out from wallet page
