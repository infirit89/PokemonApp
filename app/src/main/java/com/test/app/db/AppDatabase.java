package com.test.app.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.test.app.db.dao.PokemonDao;
import com.test.app.db.entities.PokemonEntity;

@Database(entities = {PokemonEntity.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PokemonDao pokemonDao();

}
