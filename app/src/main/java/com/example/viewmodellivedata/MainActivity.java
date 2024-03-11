package com.example.viewmodellivedata;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("log", "Activity Destroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("log", "Activity onResume");
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.buttonPanel);
        // create a View model create -----------------------
        MainActivityViewModel mainActivityViewModel = new ViewModelProvider(MainActivity.this).get(MainActivityViewModel.class);



        // live data observer create --------------------------------------
        LiveData<String> myRandomNumber = mainActivityViewModel.getRandomText();
        // this is Observe OnChange function -------------------
        myRandomNumber.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                // get value and show ui -----------------------
                textView.setText(s);
                Log.d("log", "live data OnChange");
            }
        });

        // create new random number -------------------------
        button.setOnClickListener(v->{
            mainActivityViewModel.createRandomNumber();
            Log.d("log", "onCreate: get random number");
        });






    }
}