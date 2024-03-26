package com.test.app.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.test.app.R;


public class PokemonViewHolder extends RecyclerView.ViewHolder {
    public PokemonViewHolder(@NonNull View itemView) {
        super(itemView);
        PokemonFrontSprite = itemView.findViewById(R.id.imageView);
        PokemonName = itemView.findViewById(R.id.textViewName);
        FavouriteButton = itemView.findViewById(R.id.btnFavourite);
    }

    public void setPokemonName(String name) {
        PokemonName.setText(name);
    }

    public void setPokemonFrontSprite(String url) {
        Picasso.get().load(url).into(PokemonFrontSprite);
    }

    public void setFavouriteButtonText(String buttonText) {
        FavouriteButton.setText(buttonText);
    }

    public TextView PokemonName;
    public ImageView PokemonFrontSprite;
    public Button FavouriteButton;
}
