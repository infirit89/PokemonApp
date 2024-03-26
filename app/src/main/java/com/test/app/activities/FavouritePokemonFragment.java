package com.test.app.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.app.R;
import com.test.app.adapters.FavouritePokemonAdapter;
import com.test.app.db.dao.PokemonDao;
import com.test.app.db.entities.PokemonEntity;
import com.test.app.globals.Globals;

import java.util.List;

public class FavouritePokemonFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_pokemon, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFavouritePokemon);
        PokemonDao pokemonDao = Globals.AppDatabase.pokemonDao();
        List<PokemonEntity> pokemonEntities = pokemonDao.findAllPokemon();

        FavouritePokemonAdapter favouritePokemonAdapter = new FavouritePokemonAdapter(pokemonEntities);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(favouritePokemonAdapter);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        Button allPokemonButton = view.findViewById(R.id.btnAll);
        allPokemonButton.setOnClickListener(v -> {
            AllPokemonFragment allPokemonFragment = new AllPokemonFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, allPokemonFragment)
                    .commit();
        });

        return view;
    }
}
