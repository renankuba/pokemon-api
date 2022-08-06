package com.github.renankuba.pokedexapi.exception;

public class PokemonNotFoundException extends RuntimeException {
    private final Long id;
    private final String url;

    public PokemonNotFoundException(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
