package com.github.renankuba.pokedexapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.renankuba.pokedexapi.model.Pokemon;

public interface PokemonJpaRepository extends JpaRepository<Pokemon, Long> {

}
