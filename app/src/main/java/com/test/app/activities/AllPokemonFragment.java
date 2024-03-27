package com.test.app.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.app.R;
import com.test.app.adapters.PokemonAdapter;
import com.test.app.utils.Utils;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

public class AllPokemonFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_pokemon, container, false);
        parentActivity = getActivity();
        if(parentActivity == null)
        {
            Log.e(TAG, "Parent activity was null");
            return null;
        }

        pokemonRecyclerView = view.findViewById(R.id.recyclerViewPokemon);
        Utils.setRecyclerViewAdapterDefault(pokemonRecyclerView, parentActivity.getApplicationContext(), new PokemonAdapter());

        fragmentManager = parentActivity.getSupportFragmentManager();
        setupPokemonManagerCallback();

        MainActivity.getPokemonManager().loadPokemonPaged();

        Button nextButton = view.findViewById(R.id.btnNext);
        Button previousButton = view.findViewById(R.id.btnPrev);

        nextButton.setOnClickListener(v -> MainActivity.getPokemonManager().nextPage());

        previousButton.setOnClickListener(v -> MainActivity.getPokemonManager().previousPage());

        Button favouritesButton = view.findViewById(R.id.btnFavourites);
        favouritesButton.setOnClickListener(v -> {
            FavouritePokemonFragment favouritePokemonFragment = new FavouritePokemonFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, favouritePokemonFragment)
                    .commit();
        });

        return view;
    }

    private void setupPokemonManagerCallback() {
        MainActivity.getPokemonManager().setCallback(pkResponseResults -> {
            PokemonAdapter adapter = new PokemonAdapter();
            adapter.setOnClickListener((position, pokemon) -> {
                if (pokemon != null) {
                    MorePokemonInfoFragment morePokemonInfoFragment = new MorePokemonInfoFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("pokemon", pokemon);
                    bundle.putInt("previousPage", Activities.AllPokemon);
                    morePokemonInfoFragment.setArguments(bundle);

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, morePokemonInfoFragment)
                            .commit();
                }
            });

            Utils.setRecyclerViewAdapterDefault(pokemonRecyclerView, parentActivity.getApplicationContext(), adapter);
        });

    }

    private FragmentActivity parentActivity;
    private FragmentManager fragmentManager;
    private RecyclerView pokemonRecyclerView;
}
