package com.github.renankuba.pokedexapi.service;

import com.github.renankuba.pokedexapi.model.Pokemon;
import com.github.renankuba.pokedexapi.repository.PokemonRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

@Service
public class PokemonServiceBean implements PokemonService{

    @Autowired
    private PokemonRepository repository;

    @Override
    public Optional<Pokemon> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Pokemon> findAll(){
        return repository.findAll(Sort.by(Sort.Direction.ASC, "number"));
    }
}
