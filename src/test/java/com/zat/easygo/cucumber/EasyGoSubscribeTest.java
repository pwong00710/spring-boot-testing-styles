package com.zat.easygo.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/com/zat/easygo.subscribe.feature",
        plugin = {"pretty", "html:target/cucumber/easy_go/subscribe/html", "json:target/cucumber/easy_go/subscribe/json/easy_go.json" },
        glue = {"com.zat.easygo.cucumber.step.subscribe", "com.zat.easygo.cucumber.step.common"})
public class EasyGoSubscribeTest {
}
