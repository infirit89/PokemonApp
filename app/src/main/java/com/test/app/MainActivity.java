package com.test.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.test.app.adapters.PokemonAdapter;
import com.test.app.callbacks.OnItemClickListener;
import com.test.app.callbacks.PokemonsRequestSuccessCallback;
import com.test.app.globals.PokemonManager;
import com.test.app.globals.WebClient;
import com.test.app.models.Pokemon;
import com.test.app.web.PKResponseResult;

public class MainActivity extends AppCompatActivity {

    public static PokemonManager getPokemonManager() {
        return pokemonManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebClient.Init();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPokemon);
        pokemonManager = new PokemonManager(getSharedPreferences("DB", Context.MODE_PRIVATE), new PokemonsRequestSuccessCallback() {
            @Override
            public void call(PKResponseResult[] pokemon) {
                PokemonAdapter adapter = new PokemonAdapter();
                adapter.setOnClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Pokemon pokemon) {
                        if(pokemon != null)
                            Toast.makeText(getApplicationContext(), pokemon.getName(), Toast.LENGTH_LONG).show();
                    }
                });
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }
        });
        pokemonManager.loadPokemonPaged();

        Button nextButton = findViewById(R.id.btnNext);
        Button previousButton = findViewById(R.id.btnPrev);

        nextButton.setOnClickListener(v -> {
            pokemonManager.nextPage();
        });

        previousButton.setOnClickListener(v -> {
            pokemonManager.previousPage();
        });
    }
    private static PokemonManager pokemonManager;
}