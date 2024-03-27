package com.test.app.activities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.squareup.picasso.Picasso;
import com.test.app.R;
import com.test.app.models.Pokemon;

public class MorePokemonInfoFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more_pokemon_info, container, false);

        TextView nameTextView = view.findViewById(R.id.textViewName);
        TextView weightTextView = view.findViewById(R.id.textViewWeight);
        TextView experienceTextView = view.findViewById(R.id.textViewBaseExperience);
        TextView defaultTextView = view.findViewById(R.id.textViewIsDefault);
        TextView orderTextView = view.findViewById(R.id.textViewOrder);
        ImageView spriteImageView = view.findViewById(R.id.imageView);

        Bundle bundle = getArguments();
        if(bundle == null) {
            Log.e(TAG, "Arguments bundle was null");
            return null;
        }

        Pokemon pokemon = bundle.getSerializable("pokemon", Pokemon.class);

        if(pokemon == null) {
            Log.e(TAG, "Pokemon serializable was null");
            return null;
        }

        nameTextView.setText(pokemon.getName());
        weightTextView.setText(String.valueOf(pokemon.getWeight()));
        experienceTextView.setText(String.valueOf(pokemon.getBaseExperience()));
        defaultTextView.setText(String.valueOf(pokemon.isDefault()));
        orderTextView.setText(String.valueOf(pokemon.getOrder()));
        Picasso.get().load(pokemon.getSprites().getFrontDefault()).into(spriteImageView);

        FragmentActivity parentActivity = getActivity();
        if(parentActivity == null)
        {
            Log.e(TAG, "Parent activity was null");
            return null;
        }

        FragmentManager fragmentManager = parentActivity.getSupportFragmentManager();
        Button button = view.findViewById(R.id.btnBack);
        button.setOnClickListener(v -> {
            int previousPage = bundle.getInt("previousPage");

            Fragment fragment = null;
            switch (previousPage) {
                case Activities.AllPokemon:
                    fragment = new AllPokemonFragment();
                    break;
                case Activities.FavouritePokemon:
                    fragment = new FavouritePokemonFragment();
                    break;
            }

            if(fragment == null) {
                Log.e(TAG, "Couldn't deduce the previous fragment");
                return;
            }

            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .commit();
        });

        return view;
    }
}
