package com.test.app.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.test.app.db.entities.PokemonEntity;

import java.util.List;

@Dao
public interface PokemonDao {
    @Insert
    void insertPokemon(PokemonEntity pokemon);

    @Query("DELETE FROM Pokemon WHERE pokemon_id = :pokemonId")
    void deletePokemon(int pokemonId);

    @Query(value = "SELECT * FROM Pokemon")
    List<PokemonEntity> findAllPokemon();

    @Query(value = "SELECT * FROM Pokemon WHERE pokemon_id = :id")
    List<PokemonEntity> findPokemonWithId(int id);
}
