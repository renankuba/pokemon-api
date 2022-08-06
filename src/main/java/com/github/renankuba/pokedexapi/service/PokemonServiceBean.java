package com.github.renankuba.pokedexapi.service;

import com.github.renankuba.pokedexapi.model.Pokemon;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PokemonServiceBean implements PokemonService{

    @Override
    public Pokemon findById(Integer id) {
        return null;
    }

    @Override
    public List<Pokemon> findAll(){
        return Collections.emptyList();
    }
}
