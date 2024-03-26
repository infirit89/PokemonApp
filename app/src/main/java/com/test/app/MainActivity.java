package com.test.app;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.app.models.Pokemon;
import com.test.app.web.PKResponse;
import com.test.app.web.PKResponseResult;
import com.test.app.web.PKWebClientApi;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    int page = 0;
    private List<Pokemon> pokemon = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        PKWebClientApi pkApiClient = retrofit.create(PKWebClientApi.class);
        SharedPreferences sharedPreferences = getSharedPreferences("DB", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        page = sharedPreferences.getInt("currentOffset", 0);
        loadPokemon(pkApiClient, editor);
    }

    private void loadPokemon(PKWebClientApi pkApiClient, SharedPreferences.Editor editor)
    {
        Call<PKResponse> call = pkApiClient.getPokemonPaged(page, 20);
        call.enqueue(new Callback<PKResponse>() {
            @Override
            public void onResponse(Call<PKResponse> call, Response<PKResponse> response) {
                if(!response.isSuccessful())
                    return;

                PKResponse pkResponse = response.body();
//                Log.d(TAG, pkResponse.toString());
                editor.putInt("currentOffset", page);
                editor.apply();

                for (PKResponseResult result : pkResponse.getResults()) {
                    String[] pathParts = result.getUrl().split("/");
                    Call<Pokemon> pokemonCall = pkApiClient.getPokemon(Integer.parseInt(pathParts[pathParts.length - 1]));

                    pokemonCall.enqueue(new Callback<Pokemon>() {
                        @Override
                        public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                            if(!response.isSuccessful())
                                return;

                            Pokemon pokemon = response.body();
                            Log.d(TAG, "cum");
                        }

                        @Override
                        public void onFailure(Call<Pokemon> call, Throwable t) {
                            Log.e(TAG, "Error getting sw response: " + t.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<PKResponse> call, Throwable t) {
                Log.e(TAG, "Error getting sw response: " + t.getMessage());
            }
        });
    }
}