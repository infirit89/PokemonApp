package com.test.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import com.test.app.R;
import com.test.app.db.AppDatabase;
import com.test.app.globals.Globals;
import com.test.app.globals.PokemonManager;
import com.test.app.globals.WebClient;

public class MainActivity extends AppCompatActivity {

    public static PokemonManager getPokemonManager() {
        return  pokemonManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebClient.Init();
        Globals.AppDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "pokemon-db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        pokemonManager = new PokemonManager(getSharedPreferences("DB", Context.MODE_PRIVATE), null);
    }

    private static PokemonManager pokemonManager;
}