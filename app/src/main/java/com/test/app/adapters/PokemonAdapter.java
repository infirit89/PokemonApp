package com.test.app.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.test.app.globals.Globals;
import com.test.app.globals.PokemonManager;
import com.test.app.globals.WebClient;
import com.test.app.models.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

public class PokemonAdapter extends BaseAdapter {


    public PokemonAdapter(Context context)
    {
        this.inflater = LayoutInflater.from(context);
        this.pokemons = new Pokemon[Globals.getPageLimit()];
    }

    @Override
    public int getCount() {
        return Globals.getPageLimit();
    }

    @Override
    public Object getItem(int position) {
        return getPokemon(position);
    }

    @Override
    public long getItemId(int position) {
        return getPokemon(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    private Pokemon getPokemon(int position) {
        if(pokemons[position] == null)
            loadPokemon(position);

        return pokemons[position];
    }

    private void loadPokemon(int position) {
        String[] pathParts = PokemonManager.getPkResult(position).getUrl().split("/");
        Call<Pokemon> pokemonCall = WebClient.GetPKWebApiClient().getPokemon(Integer.parseInt(pathParts[pathParts.length - 1]));

        pokemonCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if(!response.isSuccessful())
                    return;

                pokemons[position] = response.body();
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e(TAG, "Error getting sw response: " + t.getMessage());
            }
        });
    }

    private LayoutInflater inflater;
    private Pokemon[] pokemons;
}
