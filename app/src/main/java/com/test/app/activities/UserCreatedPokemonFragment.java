package com.test.app.activities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

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
import com.test.app.adapters.FavouritePokemonAdapter;
import com.test.app.adapters.UserPokemonAdapter;
import com.test.app.db.dao.PokemonDao;
import com.test.app.db.entities.PokemonEntity;
import com.test.app.globals.Globals;
import com.test.app.utils.Utils;

import java.util.List;

public class UserCreatedPokemonFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_created_pokemon, container, false);

        FragmentActivity parentActivity = getActivity();
        if(parentActivity == null)
        {
            Log.e(TAG, "Parent activity was null");
            return null;
        }

        FragmentManager fragmentManager = parentActivity.getSupportFragmentManager();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewUserCreatedPokemon);
        PokemonDao pokemonDao = Globals.AppDatabase.pokemonDao();
        List<PokemonEntity> pokemonEntities = pokemonDao.findPokemonWithId(-1);

        UserPokemonAdapter userPokemonAdapter = new UserPokemonAdapter(pokemonEntities);

        Utils.setRecyclerViewAdapterDefault(recyclerView, parentActivity.getApplicationContext(), userPokemonAdapter);

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
