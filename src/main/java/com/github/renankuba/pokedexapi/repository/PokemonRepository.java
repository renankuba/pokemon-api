package com.github.renankuba.pokedexapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.renankuba.pokedexapi.model.Pokemon;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Long>{
    
}
