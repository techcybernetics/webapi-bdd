package io.cucumber.skeleton;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( features = "src/test/resources/io/cucumber/skeleton/features",
        monochrome = true, plugin = {"pretty","html:target/HtmlReports.html","json:target/JSONReports/report.json","json:target/JunitReports/report.xml"},
        tags ="@lab",publish = true)

public class RunCucumberTest {
}

