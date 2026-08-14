	package Cucumber;
	
	import io.cucumber.testng.AbstractTestNGCucumberTests;
	import io.cucumber.testng.CucumberOptions;
	
	@CucumberOptions(features="src/test/java/Cucumber",glue="SupriyaShinde.stepDefinitions",monochrome=true,tags="@ErrorValidaton", plugin= {"html:target/Cucumber.html"})
	//run all feature files present in this package
	//map then to step definitions
	//prints results in readable format and generate report of html plugin
	//inbuilt cucumber do not have power of scanning Testng code,so extend this class
	
	public class TestNGTestRunner extends AbstractTestNGCucumberTests{
		
		
	
	}
