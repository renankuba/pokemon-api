package com.github.renankuba.pokedexapi.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.openapi4j.operation.validator.adapters.spring.mock.mvc.OpenApiMatchers;

import com.github.renankuba.pokedexapi.model.Pokemon;
import com.github.renankuba.pokedexapi.service.PokemonService;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PokedexRestServiceOpenApiTest {
    @Mock
    private PokemonService service;

    @InjectMocks
    private PokemonRestService controllerUnderTest;

    private MockMvc mockMvc;

    private final String BASE_URL = "/pokedex/pokemons";

    private Pokemon pokemon = new Pokemon(1L, "Bulbassaur", "img");

    @BeforeEach
    void init() {
        Resource spec = new ClassPathResource("openapi.yaml");
        mockMvc = MockMvcBuilders.standaloneSetup(controllerUnderTest).apply(OpenApiMatchers.openApi(spec)).build();
    }

    @Test
    public void testGetPokemonOpenApi() throws Exception {
        Long id = 1L; 
        when(service.findById(id)).thenReturn(Optional.of(pokemon));
        mockMvc.perform(get(BASE_URL + "/" + id)).andExpect(status().isOk());
    }

    @Test
    public void testGetAllOpenApi() throws Exception {
        when(service.findAll()).thenReturn(List.of(pokemon));
        mockMvc.perform(get(BASE_URL)).andExpect(status().isOk());
    }
}
