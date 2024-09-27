package com.example.travailpratique1_lounibastor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RestaurantActivity extends AppCompatActivity {
    private TextView tv_nomRestor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant);

        Intent troisiemeIntent = getIntent();
        tv_nomRestor =findViewById(R.id.tv_nomRestor);
        Bundle bReservation = troisiemeIntent.getExtras();
        String nomResto = troisiemeIntent.getStringExtra("NomResto");
        tv_nomRestor.setText(nomResto);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}