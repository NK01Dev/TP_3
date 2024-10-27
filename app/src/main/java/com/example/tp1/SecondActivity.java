package com.example.tp1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        System.out.println("la phase de creation");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("la phase de  Restoration ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("la phase de  onDestroy ");

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("la phase de  Start ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("la phase de  onStop ");

    }
}