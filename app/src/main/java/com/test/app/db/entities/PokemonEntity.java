package com.test.app.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Pokemon")
public class PokemonEntity {

    public PokemonEntity(int pokemonId, String name, String frontDefaultSprite) {
        this.id = pokemonId;
        this.pokemonId = pokemonId;
        this.name = name;
        this.frontDefaultSprite = frontDefaultSprite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrontDefaultSprite() {
        return frontDefaultSprite;
    }

    public void setFrontDefaultSprite(String frontDefaultSprite) {
        this.frontDefaultSprite = frontDefaultSprite;
    }


    @PrimaryKey
    private long id;

    @ColumnInfo(name = "pokemon_id")
    private int pokemonId;
    private String name;
    @ColumnInfo(name = "front_default_sprite")
    private String frontDefaultSprite;
}
