package com.test.app.globals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.app.web.PKWebClientApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebClient {

    public static void Init() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        pkWebClientApi = retrofit.create(PKWebClientApi.class);
    }

    public static PKWebClientApi GetPKWebApiClient() {
        return pkWebClientApi;
    }

    private static PKWebClientApi pkWebClientApi;
}
