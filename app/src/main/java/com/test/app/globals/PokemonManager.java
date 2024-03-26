package com.test.app.globals;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.SharedPreferences;
import android.util.Log;

import com.test.app.adapters.PokemonAdapter;
import com.test.app.callbacks.PokemonRequestSuccessCallback;
import com.test.app.callbacks.PokemonsRequestSuccessCallback;
import com.test.app.models.Pokemon;
import com.test.app.web.PKResponse;
import com.test.app.web.PKResponseResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonManager {


    public PokemonManager(SharedPreferences sharedPreferences, PokemonsRequestSuccessCallback callback)
    {
        currentPage = sharedPreferences.getInt("currentOffset", 0);
        editor = sharedPreferences.edit();
        this.callback = callback;
    }

    public void loadPokemonPaged() {
        Call<PKResponse> call = WebClient.GetPKWebApiClient().getPokemonPaged(currentPage, Globals.getPageLimit());
        call.enqueue(new Callback<PKResponse>() {
            @Override
            public void onResponse(Call<PKResponse> call, Response<PKResponse> response) {
                if(!response.isSuccessful())
                    return;

                PKResponse pkResponse = response.body();
                editor.putInt("currentOffset", currentPage);
                editor.apply();

                pokemonArray = pkResponse.getResults();
                if(callback != null)
                    callback.call(pokemonArray);
            }

            @Override
            public void onFailure(Call<PKResponse> call, Throwable t) {
                Log.e(TAG, "Error getting sw response: " + t.getMessage());
            }
        });
    }

    public void getPokemon(int position, PokemonRequestSuccessCallback callback) {
        Call<Pokemon> pokemonCall = WebClient.GetPKWebApiClient().getPokemon(position);

        pokemonCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if(!response.isSuccessful())
                    return;

                callback.call(response.body());
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e(TAG, "Error getting sw response: " + t.getMessage());
            }
        });
    }

    public PKResponseResult getPkResult(int position) {
        return pokemonArray[position];
    }

    public void nextPage() {
        currentPage += Globals.getPageLimit();
        loadPokemonPaged();
    }

    public void previousPage() {
        currentPage -= Globals.getPageLimit();
        currentPage = Math.max(currentPage, 0);
        loadPokemonPaged();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCallback(PokemonsRequestSuccessCallback callback) {
        this.callback = callback;
    }

    private PKResponseResult[] pokemonArray = new PKResponseResult[Globals.getPageLimit()];
    private int currentPage;
    private SharedPreferences.Editor editor;

    private PokemonsRequestSuccessCallback callback;
}
