package com.github.renankuba.pokedexapi.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.renankuba.pokedexapi.model.Pokemon;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PokedexRestServiceIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllFirstPage() throws Exception{
        var response = this.restTemplate.getForEntity("http://localhost:" + port + "/pokedex/pokemons", Pokemon[].class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        Pokemon[] body = response.getBody();
        assertThat(body.length, equalTo(50));
        assertThat(body[0].getNumber(), equalTo(1L));
        assertThat(body[49].getNumber(), equalTo(50L));
    }

    @Test
    public void getAllSecondPage() throws Exception{
        var response = this.restTemplate.getForEntity("http://localhost:" + port + "/pokedex/pokemons?page=1", Pokemon[].class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        Pokemon[] body = response.getBody();
        assertThat(body.length, equalTo(50));
        assertThat(body[0].getNumber(), equalTo(51L));
        assertThat(body[49].getNumber(), equalTo(100L));
    }

    @Test
    public void getOnePokemon() throws Exception{
        var response = this.restTemplate.getForEntity("http://localhost:" + port + "/pokedex/pokemons/1", Pokemon.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        Pokemon body = response.getBody();
        assertThat(body.getNumber(), equalTo(1L));
    }

    @Test
    public void getInexistingPokemon(){
        var response = this.restTemplate.getForEntity("http://localhost:" + port + "/pokedex/pokemons/999", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }
}
