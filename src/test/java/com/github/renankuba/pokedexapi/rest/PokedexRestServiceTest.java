package com.github.renankuba.pokedexapi.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.github.renankuba.pokedexapi.model.Pokemon;
import com.github.renankuba.pokedexapi.service.PokemonService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PokemonRestService.class)
public class PokedexRestServiceTest {
    private static final String BASE_URL = "/pokedex/pokemons";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonService service;

    @Test
    public void testFindPokemonById() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(new Pokemon(1L, "Bulbassaur", "img")));
        mockMvc.perform(get(BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.number").isNumber())
            .andExpect(jsonPath("$.number").value(1))
            .andExpect(jsonPath("$.name").value("Bulbassaur"))
            .andExpect(jsonPath("$.image").value("img"));
    }

    @Test
    public void testFindInexistingPokemonByIdShouldReturn4xx() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.ofNullable(null));
        mockMvc.perform(get(BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.code").isNumber())
            .andExpect(jsonPath("$.code").value(404))
            .andExpect(jsonPath("$.url").isString())
            .andExpect(jsonPath("$.message").isString());
    }

    @Test
    public void testFindAll() throws Exception {
        when(service.findAll(0)).thenReturn(List.of(new Pokemon(1L, "Bulbassaur", "img")));
        mockMvc.perform(get(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.[0].number").isNumber())
            .andExpect(jsonPath("$.[0].number").value(1))
            .andExpect(jsonPath("$.[0].name").value("Bulbassaur"));
    }
}
