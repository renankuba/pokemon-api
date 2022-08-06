package com.github.renankuba.pokedexapi.service;

import java.util.List;
import java.util.Optional;

import com.github.renankuba.pokedexapi.model.Pokemon;

public interface PokemonService {
    Optional<Pokemon> findById(Long id);

    List<Pokemon> findAll();
}
