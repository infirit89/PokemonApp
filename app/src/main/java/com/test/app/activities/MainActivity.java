package com.test.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.test.app.R;
import com.test.app.adapters.PokemonAdapter;
import com.test.app.callbacks.OnItemClickListener;
import com.test.app.callbacks.PokemonsRequestSuccessCallback;
import com.test.app.db.AppDatabase;
import com.test.app.globals.Globals;
import com.test.app.globals.PokemonManager;
import com.test.app.globals.WebClient;
import com.test.app.models.Pokemon;
import com.test.app.web.PKResponseResult;

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