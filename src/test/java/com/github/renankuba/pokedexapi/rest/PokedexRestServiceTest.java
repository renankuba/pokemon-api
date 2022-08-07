package com.github.renankuba.pokedexapi.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.github.renankuba.pokedexapi.model.Pokemon;
import com.github.renankuba.pokedexapi.service.PokemonService;
@SpringBootTest
@AutoConfigureMockMvc
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
            .andExpect(status().isOk());
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
        when(service.findById(1L)).thenReturn(Optional.of(new Pokemon(1L, "Bulbassaur", "img")));
        mockMvc.perform(get(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
    }
}
