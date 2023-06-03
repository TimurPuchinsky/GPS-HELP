package com.example.gpshelp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gpshelp.R;
import com.example.gpshelp.fragments.MainFragment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CallinfoActivity extends AppCompatActivity {

    Button b_back, b_delete;
    TextView t_address, t_info;
    DatabaseReference databaseReference;
    FirebaseDatabase database, secondaryDatabase;
    FirebaseApp app;
    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callinfo);

        b_back = findViewById(R.id.back);
        t_address = findViewById(R.id.textViewAddress);
        t_info = findViewById(R.id.textViewInfo);
        b_delete = findViewById(R.id.delete);
        mainFragment = new MainFragment();

        Intent intent = getIntent();
        String address = intent.getExtras().getString("address");
        String info = intent.getExtras().getString("info");
        String id = intent.getExtras().getString("id");

        t_address.setText(address);
        t_info.setText(info);

        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setApplicationId("1:603830706137:android:5322f2599256ab083f03c8")
                        .setApiKey("AIzaSyAiLLz3Zmo2sBktNyxzI7GvTJWxRf22rH4")
                        .setDatabaseUrl("https://gpshelp2-default-rtdb.firebaseio.com/")
                        .build();
                FirebaseApp.initializeApp(CallinfoActivity.this, options, "third");
                app = FirebaseApp.getInstance("third");
                secondaryDatabase = FirebaseDatabase.getInstance(app);

                databaseReference = secondaryDatabase.getReference("Calls");

                databaseReference.child(id).removeValue();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(app != null) {app.delete();}
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(app != null) {app.delete();}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(app != null) {app.delete();}
    }
}