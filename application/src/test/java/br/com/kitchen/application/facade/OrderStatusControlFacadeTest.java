package br.com.kitchen.application.facade;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "br/com/kitchen/application/facade/stepdefinitions",
        tags = "@OrderStatusControlFacade",
        plugin = {"pretty", "json:target/cucumber-report.json"}
)
class OrderStatusControlFacadeTest {

}