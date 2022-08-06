package com.github.renankuba.pokedexapi.service;

import java.util.List;

import com.github.renankuba.pokedexapi.model.Pokemon;

public interface PokemonService {
    Pokemon findById(Integer id);

    List<Pokemon> findAll();
}
