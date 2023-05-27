package com.example.gpshelp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.gpshelp.Call;
import com.example.gpshelp.MyAdapter;
import com.example.gpshelp.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseDatabase database, secondaryDatabase;
    FirebaseApp app;
    MyAdapter myAdapter;
    ArrayList<Call> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ImageButton settingsButton = view.findViewById(R.id.b_settings);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsFragment settingsFragment = new SettingsFragment();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, settingsFragment).commit();
            }
        });

        database = FirebaseDatabase.getInstance();
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("1:603830706137:android:5322f2599256ab083f03c8")
                .setApiKey("AIzaSyAiLLz3Zmo2sBktNyxzI7GvTJWxRf22rH4")
                .setDatabaseUrl("https://gpshelp2-default-rtdb.firebaseio.com/")
                .build();
        FirebaseApp.initializeApp(container.getContext(), options, "secondary");
        app = FirebaseApp.getInstance("secondary");
        secondaryDatabase = FirebaseDatabase.getInstance(app);

        recyclerView = view.findViewById(R.id.callList);
        databaseReference = secondaryDatabase.getReference("Calls");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(container.getContext(), list);
        recyclerView.setAdapter(myAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String id = dataSnapshot.getKey();
                    Call call = dataSnapshot.getValue(Call.class);
                    assert call != null;
                    call.setId(id);
                    list.add(call);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        app.delete();
    }

    @Override
    public void onStop() {
        super.onStop();
        app.delete();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        app.delete();
    }
}