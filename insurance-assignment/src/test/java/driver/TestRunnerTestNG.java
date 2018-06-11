package driver;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = {"src/test/resources/features/wallethub-review.feature"},
		glue = {"stepdefinations"},
	    plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
					"junit:target/cucumber-reports/Cucumber.xml",
					"html:target/cucumber-reports"},
		monochrome= true
		)
public class TestRunnerTestNG extends AbstractTestNGCucumberTests  {

}
	


