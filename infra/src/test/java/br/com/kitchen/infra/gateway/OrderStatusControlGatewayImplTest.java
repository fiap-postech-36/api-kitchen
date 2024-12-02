package br.com.kitchen.infra.gateway;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "br/com/kitchen/infra/gateway/stepfunction",
        tags = "@OrderStatusControlGatewayImpTest",
        plugin = {"pretty", "json:target/cucumber-report.json"}
)
class OrderStatusControlGatewayImplTest {

}

