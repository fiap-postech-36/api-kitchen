package br.com.kitchen.infra.entity;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "br/com/kitchen/infra/entity/stepfunction",
        tags = "@OrderStatusControlEntityTest",
        plugin = {"pretty", "json:target/cucumber-report.json"}
)
class OrderStatusControlEntityTest {

}