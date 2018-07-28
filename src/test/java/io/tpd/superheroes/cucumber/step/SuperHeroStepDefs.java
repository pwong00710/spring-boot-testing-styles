package io.tpd.superheroes.cucumber.step;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.tpd.superheroes.domain.SuperHero;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.List;

@Ignore
public class SuperHeroStepDefs extends SuperHeroBaseStepDefs {

//    Scenario: Create new super hero
//    Given super hero with the following details are created:
//            |firstName |lastName  |heroName       |
//            |Robert    |Bruce     |Hulk           |
//            |Stephen   |Vincent   |Doctor Strange |
//    When super hero are created with above details
//    Then super hero with above details can be found

    List<SuperHero> superHeros;
    String heroName;
    SuperHero superHero;

    @Given("^super hero with the following details are created:$")
    public void super_hero_with_the_following_details_are_created(DataTable dataTable) throws Throwable {
        superHeros = dataTable.asList(SuperHero.class);
    }

    @When("^super hero are created with above details$")
    public void super_hero_are_created_with_above_details() throws Exception {
        superHeros.stream().forEach(superHeros -> { createSuperHero(superHeros); });
    }

    @Then("^super hero with above details can be found$")
    public void super_hero_with_above_details_can_be_found() {
        List<SuperHero> results = new ArrayList<>();
        superHeros.stream().map(SuperHero::getHeroName).forEach(heroName -> { results.add(retrieveSuperHeroByHeroName(heroName));});
        Assert.assertThat(results, Matchers.is(superHeros));
    }

//    Scenario Outline: Find super hero by hero names
//    Given input hero name '<heroName>'
//    When do search
//    Then search results are shown for '<realName>'
//
//    Examples:
//            | heroName         | realName         |
//            | Hulk             | Robert Bruce     |
//            | Doctor Strange   | Stephen Vincent  |

    @Given("^input hero name '(.*?)'$")
    public void input_hero_name(String heroName) {
        this.heroName = heroName;
    }

    @When("^do search$")
    public void do_search() {
        superHero = this.retrieveSuperHeroByHeroName(heroName);
    }

    @Then("^search results are shown for '(.*?)'$")
    public void search_results_are_show_for(String realName) {
        Assert.assertEquals(realName, superHero.getRealName());
    }
}
