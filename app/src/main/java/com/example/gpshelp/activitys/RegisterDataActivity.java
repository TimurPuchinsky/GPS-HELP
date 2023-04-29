package com.example.gpshelp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpshelp.R;
import com.example.gpshelp.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterDataActivity extends AppCompatActivity {

    TextInputEditText surname, name, fname, birthdate, passport, snils, liveplace, workplace;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference users;
    Button register;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerdata);

        register = findViewById(R.id.btn_register);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        surname = findViewById(R.id.surname);
        name = findViewById(R.id.name);
        fname = findViewById(R.id.fname);
        birthdate = findViewById(R.id.birthdate);
        passport = findViewById(R.id.passport);
        snils = findViewById(R.id.snils);
        liveplace = findViewById(R.id.liveplace);
        workplace = findViewById(R.id.workplace);

        textView = findViewById(R.id.back);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ssurname, sname, sfname, sbirthdate, spassport, ssnils, slivepalce, sworkplace;
                ssurname = String.valueOf(surname.getText());
                sname = String.valueOf(name.getText());
                sfname = String.valueOf(fname.getText());
                sbirthdate = String.valueOf(birthdate.getText());
                spassport = String.valueOf(passport.getText());
                ssnils = String.valueOf(snils.getText());
                slivepalce = String.valueOf(liveplace.getText());
                sworkplace = String.valueOf(workplace.getText());

                if (TextUtils.isEmpty(ssurname) || TextUtils.isEmpty(sname) || TextUtils.isEmpty(sfname) || TextUtils.isEmpty(sbirthdate)
                    || TextUtils.isEmpty(spassport) || TextUtils.isEmpty(ssnils) || TextUtils.isEmpty(slivepalce)|| TextUtils.isEmpty(sworkplace)) {
                    Toast.makeText(RegisterDataActivity.this, "Не все поля заполнены!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String email, password;
                email = getIntent().getStringExtra("email");
                password = getIntent().getStringExtra("password");

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                User user = new User();
                                user.setSurname(ssurname);
                                user.setName(sname);
                                user.setFname(sfname);
                                user.setBirthdate(sbirthdate);
                                user.setPassport(spassport);
                                user.setSnils(ssnils);
                                user.setLiveplace(slivepalce);
                                user.setWorkplace(sworkplace);
                                user.setEmail(email);
                                user.setPassword(password);

                                users.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(user);
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterDataActivity.this, "Учетная запись создана.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(RegisterDataActivity.this, "Ошибка при регистрации.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}