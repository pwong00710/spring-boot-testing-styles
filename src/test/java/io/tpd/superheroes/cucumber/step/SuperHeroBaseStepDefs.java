package io.tpd.superheroes.cucumber.step;

import io.tpd.superheroes.domain.SuperHero;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * This class demonstrates how to test a controller using Spring Boot Test
 * (what makes it much closer to a Integration Test)
 *
 * @author moises.macero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class SuperHeroBaseStepDefs {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    protected int port;

    private final String SERVER_URL = "http://localhost";
    private final String SUPER_HEROS_ENDPOINT = "/superheroes";

    private String superherosEndpoint() {
        return SERVER_URL + ":" + port + SUPER_HEROS_ENDPOINT;
    }

    protected void createSuperHero(SuperHero superHero) {
        ResponseEntity<SuperHero> superHeroResponse = restTemplate.postForEntity(superherosEndpoint() + "/",
                superHero, SuperHero.class);
    }

    protected SuperHero retrieveSuperHeroByHeroName(String heroName) {
        ResponseEntity<SuperHero> superHeroResponse = restTemplate
                .getForEntity(superherosEndpoint()+"/?name="+heroName, SuperHero.class);
        return superHeroResponse.getBody();
    }
}