package com.test.app.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
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

        Pokemon pokemon = getArguments().getSerializable("pokemon", Pokemon.class);

        nameTextView.setText(pokemon.getName());
        weightTextView.setText(String.valueOf(pokemon.getWeight()));
        experienceTextView.setText(String.valueOf(pokemon.getBaseExperience()));
        defaultTextView.setText(String.valueOf(pokemon.isDefault()));
        orderTextView.setText(String.valueOf(pokemon.getOrder()));
        Picasso.get().load(pokemon.getSprites().getFrontDefault()).into(spriteImageView);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Button button = view.findViewById(R.id.btnBack);
        button.setOnClickListener(v -> {
            Fragment fragment = null;
            String previousPage = getArguments().getString("previousPage");
            if(previousPage == AllPokemonFragment.class.getName())
                fragment = new AllPokemonFragment();
            else if(previousPage == FavouritePokemonFragment.class.getName())
                fragment = new FavouritePokemonFragment();

            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .commit();
        });

        return view;
    }
}
