package io.tpd.superheroes.controller;

import io.tpd.superheroes.domain.SuperHero;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * This class demonstrates how to test a controller using Spring Boot Test
 * (what makes it much closer to a Integration Test)
 *
 * @author moises.macero
 */
@Ignore
public class SuperHeroControllerSpringBootIntegrationTest {

    private TestRestTemplate restTemplate = new TestRestTemplate();

    protected int port = 8080;

    private final String SERVER_URL = "http://localhost";
    private final String SUPER_HEROS_ENDPOINT = "/superheroes";

    private String superherosEndpoint() {
        return SERVER_URL + ":" + port + SUPER_HEROS_ENDPOINT;
    }

    @Ignore
    @Test
    public void canRetrieveByIdWhenExists() {
        // when
        ResponseEntity<SuperHero> superHeroResponse = restTemplate.getForEntity(superherosEndpoint()+"/2", SuperHero.class);

        // then
        assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(superHeroResponse.getBody().equals(new SuperHero("Rob", "Mannon", "RobotMan")));
    }

    @Ignore
    @Test
    public void canRetrieveByIdWhenDoesNotExist() {
        // when
        ResponseEntity<SuperHero> superHeroResponse = restTemplate.getForEntity(superherosEndpoint()+"/5", SuperHero.class);

        // then
        assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(superHeroResponse.getBody()).isNull();
    }

    @Ignore
    @Test
    public void canRetrieveByNameWhenExists() {
        // when
        ResponseEntity<SuperHero> superHeroResponse = restTemplate
                .getForEntity(superherosEndpoint()+"/?name=Batman", SuperHero.class);

        // then
        assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(superHeroResponse.getBody().equals(new SuperHero("Bruce", "Wayne", "Batman")));
    }

    @Ignore
    @Test
    public void canRetrieveByNameWhenDoesNotExist() {
        // when
        ResponseEntity<SuperHero> superHeroResponse = restTemplate
                .getForEntity(superherosEndpoint()+"/?name=RobotMan", SuperHero.class);

        // then
        assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(superHeroResponse.getBody()).isNull();
    }

    @Ignore
    @Test
    public void canCreateANewSuperHero() {
        // when
        ResponseEntity<SuperHero> superHeroResponse = restTemplate.postForEntity(superherosEndpoint()+"/",
                new SuperHero("Rob", "Mannon", "RobotMan"), SuperHero.class);

        // then
        assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Ignore
    @Test
    public void headerIsPresent() throws Exception {
        // when
        ResponseEntity<SuperHero> superHeroResponse = restTemplate.getForEntity(superherosEndpoint()+"/2", SuperHero.class);

        // then
        assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(superHeroResponse.getHeaders().get("X-SUPERHERO-APP")).containsOnly("super-header");
    }

}