package com.github.renankuba.pokedexapi.rest;

import org.springframework.web.bind.annotation.RestController;

import com.github.renankuba.pokedexapi.exception.PokemonNotFoundException;
import com.github.renankuba.pokedexapi.model.Pokemon;
import com.github.renankuba.pokedexapi.service.PokemonService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/pokedex/pokemons")
@CrossOrigin(origins = "http://localhost:3000")
public class PokemonRestService {
    
    @Autowired
	private PokemonService service;

    @GetMapping("/{id}")
    public Pokemon getOne(@PathVariable Long id){
        return service.findById(id)
            .orElseThrow(() -> new PokemonNotFoundException(id, "/pokedex/pokemons/"+id));
    }

    @GetMapping
    public List<Pokemon> getAll(Optional<Integer> page){
        return service.findAll(page.orElse(0));
    }
}
