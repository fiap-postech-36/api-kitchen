package br.com.kitchen.application.usecase.kitchen;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "br/com/kitchen/application/usecase/kitchen/stepdefinitions",
        tags = "@CancelOrderStatusCase",
        plugin = {"pretty", "json:target/cucumber-report.json"}
)
class CancelOrderStatusCaseTest {
}