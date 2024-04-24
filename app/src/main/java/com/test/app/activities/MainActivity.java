package com.test.app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.test.app.R;
import com.test.app.db.AppDatabase;
import com.test.app.globals.Globals;
import com.test.app.globals.PokemonManager;
import com.test.app.globals.WebClient;

public class MainActivity extends AppCompatActivity {

    public static PokemonManager getPokemonManager() {
        return  pokemonManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebClient.Init();
        Globals.AppDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "pokemon-db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        pokemonManager = new PokemonManager(getSharedPreferences("DB", Context.MODE_PRIVATE), null);

        FirebaseAuth fbAuth = FirebaseAuth.getInstance();
        if(fbAuth.getCurrentUser() == null)
        {
            Intent loginActivityIntent = new Intent(this, LoginActivity.class);
            startActivity(loginActivityIntent);
        }

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.layout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Here we are setting what to do when a menu item is clicked
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.menuUser:
                Log.d("test", "test");
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, new UserInformationFragment())
                        // without commit, won't work
                        .commit();
                break;
        }
        return true;
    }

    private static PokemonManager pokemonManager;
}