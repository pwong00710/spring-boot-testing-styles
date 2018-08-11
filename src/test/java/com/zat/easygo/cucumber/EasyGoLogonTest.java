package com.zat.easygo.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/com/zat/easyGo.logon.feature",
        plugin = {"pretty", "html:target/cucumber/easy_go/logon/html", "json:target/cucumber/easy_go/logon/json/easy_go.json" },
        glue = {"com.zat.easygo.cucumber.step.logon", "com.zat.easygo.cucumber.step.common"},
        tags = {"not @Ignore"})
public class EasyGoLogonTest {
}
