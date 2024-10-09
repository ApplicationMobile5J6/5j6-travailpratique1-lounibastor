package com.example.travailpratique1_lounibastor;

import android.content.Intent;
import android.os.Bundle;
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

        spn_date = findViewById(R.id.spn_date);

        reservationListe.add(new reservation("Amine Re", "17h00", "22h00", 2, "2024-10-09", "0123456789"));
        reservationListe.add(new reservation("Sarah L", "19h00", "21h00", 4, "2024-10-10", "0987654321"));
        reservationListe.add(new reservation("Jean P", "18h00", "20h00", 3, "2024-10-09", "0555555555"));
        reservationListe.add(new reservation("Jean P", "18h00", "20h00", 3, "2030-10-09", "0555555555"));

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
                filterReservationsByDate(selectedDate);
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
        adaptateur = new ReservationAdapt(this, reservationListe);
        maListe.setAdapter(adaptateur);

        maListe.setOnItemClickListener((parent, view, position, id) -> {
            reservation selectedReservation = adaptateur.getItem(position);
            String message = "Numéro de réservation: " + selectedReservation.getNoReservation() +
                    ", Téléphone: " + selectedReservation.getTelPersonne();
            Toast.makeText(RestaurantActivity.this, message, Toast.LENGTH_SHORT).show();
        });
    }

    private void filterReservationsByDate(String date) {
        List<reservation> filteredList = new ArrayList<>();

        for (reservation res : reservationListe) {
            if (res.getDateReservation().equals(date)) {
                filteredList.add(res);
            }
        }

        adaptateur = new ReservationAdapt(this, filteredList);
        maListe.setAdapter(adaptateur);
    }

}

