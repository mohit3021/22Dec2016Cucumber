package Runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


	@RunWith(Cucumber.class)
	@CucumberOptions(features="featuresfile",   glue={"StepDef"}, tags = {"@smoke"}, format = {"pretty", "html:target/cucumber-html-report"})
	public class TestRunnerForScenarios 
	{
		
	}

