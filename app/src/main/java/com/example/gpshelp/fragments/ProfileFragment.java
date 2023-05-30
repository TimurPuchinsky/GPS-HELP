package com.example.gpshelp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gpshelp.R;
import com.example.gpshelp.activitys.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class ProfileFragment extends Fragment {

    FirebaseAuth auth;
    Button button, mainButton;
    TextView tvEmail, tvSurname, tvName, tvFname, tvBirthdate, tvPassport, tvSnils, tvLiveplace, tvWorkplace;
    FirebaseUser user;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        button = view.findViewById(R.id.buttonChangeUser);
        tvEmail = view.findViewById(R.id.textViewEmail);
        tvSurname = view.findViewById(R.id.textViewFamily);
        tvName = view.findViewById(R.id.textViewName);
        tvFname = view.findViewById(R.id.textViewFatherName);
        tvBirthdate = view.findViewById(R.id.textViewBirth);
        tvPassport = view.findViewById(R.id.textViewPassport);
        tvSnils = view.findViewById(R.id.textViewSnils);
        tvLiveplace = view.findViewById(R.id.textViewLiveplace);
        tvWorkplace = view.findViewById(R.id.textViewWorkplace);

        //mainButton = view.findViewById(R.id.buttonRedact);

        auth = FirebaseAuth.getInstance();

        user = auth.getCurrentUser();
        if (user == null){
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
            ((Activity) getActivity()).overridePendingTransition(0, 0);
        } else {
            db = FirebaseDatabase.getInstance();
            users = db.getReference("Users");
            users.child(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    } else {
                        tvSurname.setText(String.valueOf(task.getResult().child("surname").getValue()));
                        tvName.setText(String.valueOf(task.getResult().child("name").getValue()));
                        tvFname.setText(String.valueOf(task.getResult().child("fname").getValue()));
                        tvBirthdate.setText(String.valueOf(task.getResult().child("birthdate").getValue()));
                        tvPassport.setText(String.valueOf(task.getResult().child("passport").getValue()));
                        tvSnils.setText(String.valueOf(task.getResult().child("snils").getValue()));
                        tvLiveplace.setText(String.valueOf(task.getResult().child("liveplace").getValue()));
                        tvWorkplace.setText(String.valueOf(task.getResult().child("workplace").getValue()));
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    }
                }
            });
            tvEmail.setText(user.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

//        mainButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainFragment mainFragment = new MainFragment();
//                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mainFragment).commit();
//            }
//        });
        return view;
    }
}