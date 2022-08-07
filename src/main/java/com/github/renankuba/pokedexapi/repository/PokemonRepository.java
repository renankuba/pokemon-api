package com.github.renankuba.pokedexapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.renankuba.pokedexapi.model.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long>{
    
}
