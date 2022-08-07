CREATE TABLE pokemon
(
    id bigint,
    name varchar(200),
    image varchar(200),
    CONSTRAINT pokemon_pk PRIMARY KEY (id)
);

insert into pokemon (id, name, image) values (1, 'bulbasaur', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png');