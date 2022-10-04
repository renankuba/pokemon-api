package com.github.renankuba.pokedexapi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.renankuba.pokedexapi.model.Pokemon;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PokemonRepositoryTest {

    @Autowired
    private PokemonRepository pokemonRepository;

    @BeforeEach
    public void setUp(){
        pokemonRepository.deleteAll();
    }

    @Test
    public void verigyPokemonCanBeSaved() {
        final Pokemon pokemon = new Pokemon();
        pokemon.setNumber(25L);
        pokemon.setName("Pokemon");
        pokemon.setImage("any string");
        pokemonRepository.save(pokemon);
        List<Pokemon> findAll = pokemonRepository.findAll();
        assertThat(findAll, hasSize(1));
        assertThat(findAll.get(0), is(pokemon));
    }
}
