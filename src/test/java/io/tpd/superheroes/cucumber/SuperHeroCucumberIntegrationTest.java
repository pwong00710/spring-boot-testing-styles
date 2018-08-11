package io.tpd.superheroes.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/io/tpd/superHero.feature",
        plugin = {"pretty", "html:target/cucumber/super_hero/html", "json:target/cucumber/super_hero/json/super_hero.json" },
        glue = {"io.tpd.superheroes.cucumber"},
        tags = {"not @Ignore"})
public class SuperHeroCucumberIntegrationTest {
}
