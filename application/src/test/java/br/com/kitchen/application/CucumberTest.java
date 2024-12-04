package br.com.kitchen.application;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "br.com.kitchen.application.usecase.kitchen.stepdefinitions",
    plugin = {
        "pretty",
        "json:target/cucumber-report/cucumber.json"
    }
)
public class CucumberTest {
}