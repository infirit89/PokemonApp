package com.test.app.globals;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.SharedPreferences;
import android.util.Log;

import com.test.app.web.PKResponse;
import com.test.app.web.PKResponseResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonManager {


    public static void Init(SharedPreferences sharedPreferences)
    {
        currentPage = sharedPreferences.getInt("currentOffset", 0);
        editor = sharedPreferences.edit();
    }

    private static void loadPokemon(int page) {
        Call<PKResponse> call = WebClient.GetPKWebApiClient().getPokemonPaged(page, Globals.getPageLimit());
        call.enqueue(new Callback<PKResponse>() {
            @Override
            public void onResponse(Call<PKResponse> call, Response<PKResponse> response) {
                if(!response.isSuccessful())
                    return;

                PKResponse pkResponse = response.body();
                editor.putInt("currentOffset", page);
                editor.apply();

                pokemonArray = pkResponse.getResults();

//                for (PKResponseResult result : pkResponse.getResults()) {

//                }
            }

            @Override
            public void onFailure(Call<PKResponse> call, Throwable t) {
                Log.e(TAG, "Error getting sw response: " + t.getMessage());
            }
        });
    }

    public static PKResponseResult getPkResult(int position) {
        return pokemonArray[position];
    }

    public static void NextPage() {
        currentPage += Globals.getPageLimit();
        loadPokemon(currentPage);
    }

    public static void PreviousPage() {
        currentPage -= Globals.getPageLimit();
        loadPokemon(currentPage);
    }

    public static int GetCurrentPage() {
        return currentPage;
    }

    private static PKResponseResult[] pokemonArray;
    private static int currentPage;
    private static SharedPreferences.Editor editor;
}
