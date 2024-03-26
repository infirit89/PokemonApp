package com.test.app.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.app.R;
import com.test.app.adapters.PokemonAdapter;
import com.test.app.callbacks.OnItemClickListener;
import com.test.app.callbacks.PokemonsRequestSuccessCallback;
import com.test.app.globals.PokemonManager;
import com.test.app.models.Pokemon;
import com.test.app.web.PKResponseResult;

public class AllPokemonFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_pokemon, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPokemon);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        MainActivity.getPokemonManager().setCallback(new PokemonsRequestSuccessCallback() {
            @Override
            public void call(PKResponseResult[] pokemon) {
                PokemonAdapter adapter = new PokemonAdapter();
                adapter.setOnClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Pokemon pokemon) {
                        if (pokemon != null) {
                            MorePokemonInfoFragment morePokemonInfoFragment = new MorePokemonInfoFragment();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("pokemon", pokemon);
                            bundle.putString("previousPage", AllPokemonFragment.class.getName());
                            morePokemonInfoFragment.setArguments(bundle);

                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragmentContainerView, morePokemonInfoFragment)
                                    .commit();
                        }
                    }
                });
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }
        });
        MainActivity.getPokemonManager().loadPokemonPaged();

        Button nextButton = view.findViewById(R.id.btnNext);
        Button previousButton = view.findViewById(R.id.btnPrev);

        nextButton.setOnClickListener(v -> {
            MainActivity.getPokemonManager().nextPage();
        });

        previousButton.setOnClickListener(v -> {
            MainActivity.getPokemonManager().previousPage();
        });

        Button favouritesButton = view.findViewById(R.id.btnFavourites);
        favouritesButton.setOnClickListener(v -> {
            FavouritePokemonFragment favouritePokemonFragment = new FavouritePokemonFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, favouritePokemonFragment)
                    .commit();
        });

        return view;
    }
}
