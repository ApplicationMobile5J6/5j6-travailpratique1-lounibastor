package com.example.travailpratique1_lounibastor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestaurantActivity extends AppCompatActivity {
    private ListView maListe;
    private ReservationAdapt adaptateur;
    ArrayList<reservation> reservationListe = new ArrayList<>();
    private TextView tv_nomRestor;
    private Spinner spn_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant);

        Intent troisiemeIntent = getIntent();
        tv_nomRestor = findViewById(R.id.tv_nomRestor);
        String nomResto = troisiemeIntent.getStringExtra("NomResto");
        tv_nomRestor.setText(nomResto);

        reservationListe = troisiemeIntent.getParcelableArrayListExtra("ReservationListe");

        List<reservation> filteredReservations = new ArrayList<>();
        for (reservation res : reservationListe) {
            if (res.getNomRestaurant().equals(nomResto)) {
                filteredReservations.add(res);
            }
        }

        spn_date = findViewById(R.id.spn_date);

        Set<String> uniqueDates = new HashSet<>();
        for (reservation res : reservationListe) {
            uniqueDates.add(res.getDateReservation());
        }

        List<String> dateList = new ArrayList<>(uniqueDates);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dateList);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_date.setAdapter(adapterSpinner);

        spn_date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDate = (String) parent.getItemAtPosition(position);
                filterReservationsByDate(selectedDate, filteredReservations);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        maListe = findViewById(R.id.lv_Reservations);
        adaptateur = new ReservationAdapt(this, filteredReservations);
        maListe.setAdapter(adaptateur);

        maListe.setOnItemClickListener((parent, view, position, id) -> {
            reservation selectedReservation = adaptateur.getItem(position);
            String message = getString(R.string.num_res) + " " + selectedReservation.getNoReservation() +
                    ", " + getString(R.string.tel) + " " + selectedReservation.getTelPersonne();
            Toast.makeText(RestaurantActivity.this, message, Toast.LENGTH_SHORT).show();
        });
    }

    private void filterReservationsByDate(String date, List<reservation> filteredReservations) { // Ajouter le paramètre
        List<reservation> filteredList = new ArrayList<>();

        for (reservation res : filteredReservations) {
            if (res.getDateReservation().equals(date)) {
                filteredList.add(res);
            }
        }

        adaptateur = new ReservationAdapt(this, filteredList);
        maListe.setAdapter(adaptateur);
    }
}

