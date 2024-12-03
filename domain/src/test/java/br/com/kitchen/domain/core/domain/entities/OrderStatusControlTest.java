package br.com.kitchen.domain.core.domain.entities;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "br/com/kitchen/domain/core/domain/entities/stepdefinitions",
        tags = "@OrderStatusControlTest",
        plugin = {"pretty", "json:target/cucumber-report.json"}
)
class OrderStatusControlTest {

}