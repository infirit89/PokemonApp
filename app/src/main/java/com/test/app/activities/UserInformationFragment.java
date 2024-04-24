package com.test.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.test.app.R;

public class UserInformationFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_information, container, false);

        FirebaseAuth fbAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = fbAuth.getCurrentUser();

        TextView usernameTextView = view.findViewById(R.id.textViewUserName);
        usernameTextView.setText(currentUser.getEmail());

        Button logoutButton = view.findViewById(R.id.buttonLogout);
        Button backButton = view.findViewById(R.id.buttonBack);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        logoutButton.setOnClickListener(v ->
        {
            fbAuth.signOut();
            Intent loginAcitvityIntent = new Intent(getActivity(), LoginActivity.class);
            startActivity(loginAcitvityIntent);
        });

        backButton.setOnClickListener(v ->
        {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, new AllPokemonFragment())
                    .commit();
        });

        return view;
    }
}
