package stepdefinations;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;

import commonlibrary.Utility;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.DriverInstance;
import pageobjects.InsurancePage;
import pageobjects.LoginPage;
import pageobjects.MyProfilePage;

public class WalletHubStepDefinations {
	
static 	LoginPage loginPage;
static InsurancePage insurancePage;
static MyProfilePage myProfilePage;
static String reviewComment;
static String starRating;
		

@Given("^I have opened wallethubg website$")
public void i_have_opened_wallethubg_website() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	DriverInstance.createDriverInstance();
	DriverInstance.goToWebSite();
}

@When("^I am on wallethub login page$")
public void i_am_on_wallethub_login_page() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	loginPage  = PageFactory.initElements(DriverInstance.driver, LoginPage.class);
	loginPage.verifyUserOnLoginScreen();
}

@When("^I enter credentials and hit login button$")
public void i_enter_credentials_and_hit_login_button(DataTable creds) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	List<Map<String,String>> testData = creds.asMaps(String.class,String.class);
	loginPage.doLogin(testData.get(0).get("user_id"), testData.get(0).get("password"));
}

@Then("^I must be able to login$")
public void i_must_be_able_to_login() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	loginPage.verifyUserLoggedInSucessfully();
}

@Then("^I must see my home page$")
public void i_must_see_my_home_page() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	myProfilePage  = PageFactory.initElements(DriverInstance.driver, MyProfilePage.class);
	myProfilePage.verifyOnMyProfilePage();
}

@When("^I naviagte to Insurance Page$")
public void i_naviagte_to_Insurance_Page(DataTable links) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	insurancePage  = PageFactory.initElements(DriverInstance.driver, InsurancePage.class);
	List<Map<String,String>> testData = links.asMaps(String.class,String.class);
	insurancePage.naviagetToInsurancePage(testData.get(0).get("insurance_company_url"));
}

@Then("^I should see the insurance page$")
public void i_should_see_the_insurance_page() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	insurancePage.verifyOnInsurancePage();
}

@When("^I try to enter the rating$")
public void i_try_to_enter_the_rating(DataTable ratings) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	List<Map<String,String>> testData = ratings.asMaps(String.class,String.class);
	starRating = testData.get(0).get("star_rating");
	insurancePage.navigateToReviewPage(starRating);	
}

@Then("^It should take me to rating page\\.$")
public void it_should_take_me_to_rating_page() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	insurancePage.verifyOnInsuranceRatingPage();
}

@When("^I give star ratings$")
public void i_give_star_ratings(DataTable ratings) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	List<Map<String,String>> testData = ratings.asMaps(String.class,String.class);
	insurancePage.enterTheStarRating(testData.get(0).get("star_rating"));
}


@When("^I select the policy type$")
public void i_select_the_policy_type(DataTable policies) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	List<Map<String,String>> testData = policies.asMaps(String.class,String.class);
	insurancePage.selectThePolicy(testData.get(0).get("policy_type"));
}


@When("^I enter the review comment$")
public void i_enter_the_review_comment() throws Throwable {
	reviewComment = Utility.randomString(205);
	insurancePage.enterTheReview(reviewComment);
}

@When("^I submit the review$")
public void i_submit_the_review() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	insurancePage.submitReview();
}

@Then("^It should get added$")
public void it_should_get_added() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	insurancePage.verifyReviewSubmited();
}
@When("^I move to my profile page$")
public void i_move_to_my_profile_page() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	
	myProfilePage.navigateToMyProfilePage();
}

@Then("^I must see review is listed in my activities$")
public void i_must_see_review_is_listed_in_my_activities() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	myProfilePage.verifyReviewListedInActivity(reviewComment);
}


@When("^I move to rating page$")
public void i_move_to_rating_page(DataTable userNames) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	List<Map<String,String>> testData = userNames.asMaps(String.class,String.class);
	myProfilePage.navigateToReviewPageByUser(testData.get(0).get("user_name"));
}

@Then("^I must see the created review$")
public void i_must_see_the_created_review() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	myProfilePage.verifyReviewListedInReviews(reviewComment);
}

@When("^I hit logout$")
public void i_hit_logout() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	loginPage.logOut();
}

@Then("^I must be logged out from wallet page$")
public void i_must_be_logged_out_from_wallet_page() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	myProfilePage.verifyOnMyProfilePage();
	DriverInstance.destroyDriverInstance();
	DriverInstance.verifyDriverDestroyed();
}

}
