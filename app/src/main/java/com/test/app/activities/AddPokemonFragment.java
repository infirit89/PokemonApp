package com.test.app.activities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.test.app.R;
import com.test.app.db.dao.PokemonDao;
import com.test.app.db.entities.PokemonEntity;
import com.test.app.globals.Globals;

public class AddPokemonFragment extends Fragment {

    ImageView galleryImage;
    StorageReference imageStorage;
    Uri spriteUri;
    ActivityResultLauncher<Intent> galleryActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        spriteUri = data.getData();
                        galleryImage.setImageURI(spriteUri);
                    }
                }
            });


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_pokemon, container, false);
        galleryImage = view.findViewById(R.id.galleryImage);

        FragmentActivity parentActivity = getActivity();
        if(parentActivity == null)
        {
            Log.e(TAG, "Parent activity was null");
            return null;
        }

        FragmentManager fragmentManager = parentActivity.getSupportFragmentManager();

        Button browseButton = view.findViewById(R.id.btnBrowse);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        imageStorage = storage.getReference();

        browseButton.setOnClickListener(v ->
        {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK);
            galleryIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryActivityLauncher.launch(galleryIntent);
        });

        view.findViewById(R.id.btnCreate).setOnClickListener(v ->
        {
            EditText editText = view.findViewById(R.id.editTextPokemonName);

            StorageReference imageRef = imageStorage.child("images/" + spriteUri.getLastPathSegment());
            UploadTask uploadTask = imageRef.putFile(spriteUri);
            uploadTask.continueWithTask(task -> {
                if (!task.isSuccessful())
                    throw task.getException();

                return imageRef.getDownloadUrl();
            }).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    Log.i(TAG, downloadUri.toString());
                    PokemonDao pokemonDao = Globals.AppDatabase.pokemonDao();
                    PokemonEntity pokemonEntity = new PokemonEntity(-1, editText.getText().toString(), downloadUri.toString());
                    pokemonDao.insertPokemon(pokemonEntity);

                    AllPokemonFragment allPokemonFragment = new AllPokemonFragment();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, allPokemonFragment)
                            .commit();
                } else {
                    Log.e(TAG, task.getException().getMessage());
                }
            });
        });

        return view;
    }
}
