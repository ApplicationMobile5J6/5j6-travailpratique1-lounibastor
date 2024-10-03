package com.example.travailpratique1_lounibastor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {
    private ListView maListe;
    private ReservationAdapt adaptateur;
    ArrayList<reservation> reservationListe = new ArrayList<>();
    private TextView tv_nomRestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant);

        Intent troisiemeIntent = getIntent();
        tv_nomRestor = findViewById(R.id.tv_nomRestor);
        Bundle bReservation = troisiemeIntent.getExtras();
        String nomResto = troisiemeIntent.getStringExtra("NomResto");
        tv_nomRestor.setText(nomResto);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        maListe = (ListView) findViewById(R.id.lv_Reservations);
        reservationListe.add(new reservation("Amine Re", "17h00", "22h00", 2, "test"));
        adaptateur = new ReservationAdapt(this, reservationListe);
        maListe.setAdapter(adaptateur);
    }
}