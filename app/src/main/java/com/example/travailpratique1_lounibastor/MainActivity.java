package com.example.travailpratique1_lounibastor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private Spinner spn_nomResto;
    private TextView tv_placesRestantes;
    ArrayList<restaurant> listeRestaurants = new ArrayList<>();
    private ArrayList<reservation> reservationListe = new ArrayList<>();
    private restaurant restaurantSelectionne;
    private ActivityResultLauncher<Intent> activityResultLauncher;

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

        ImageView img = findViewById(R.id.iv_resto);
        img.setImageResource(R.drawable.resto);

        spn_nomResto = findViewById(R.id.spn_nomResto);
        tv_placesRestantes = findViewById(R.id.tv_placesReserve);

        listeRestaurants.add(new restaurant(1, "Bnine", 15, 15));
        listeRestaurants.add(new restaurant(2, "CHEZ RAMS", 10, 10));
        listeRestaurants.add(new restaurant(3, "McHalal", 9, 9));
        listeRestaurants.add(new restaurant(4, "Tacos del caso", 20, 20));
        listeRestaurants.add(new restaurant(5, "Fruit el Goju", 35, 35));

        ArrayAdapter<restaurant> adaptateur = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listeRestaurants);
        adaptateur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_nomResto.setAdapter(adaptateur);

        spn_nomResto.setSelection(0);
        restaurantSelectionne = listeRestaurants.get(0); // Sélectionne le premier restaurant
        updatePlacesRestantesTextView(); // Met à jour l'affichage initial

        spn_nomResto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                restaurantSelectionne = (restaurant) spn_nomResto.getSelectedItem();
                updatePlacesRestantesTextView(); // Met à jour l'affichage lors de la sélection
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Aucune action nécessaire
            }
        });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    int nouvellesPlacesRestantes = data.getIntExtra("NouvellesPlacesRestantes", -1);
                    if (nouvellesPlacesRestantes != -1) {
                        restaurantSelectionne.setNbPlacesRestantes(nouvellesPlacesRestantes);
                        updatePlacesRestantesTextView(); // Met à jour l'affichage après le retour
                    }
                    ArrayList<reservation> nouvellesReservations = data.getParcelableArrayListExtra("ReservationListe");
                    if (nouvellesReservations != null) {
                        reservationListe = nouvellesReservations;
                    }
                }
            }
        });
    }

    // Méthode pour mettre à jour le TextView et changer la couleur si nécessaire
    private void updatePlacesRestantesTextView() {
        int nbPlacesRestantes = restaurantSelectionne.getNbPlacesRestantes();
        tv_placesRestantes.setText(nbPlacesRestantes + " " + getString(R.string.places_restantes));

        // Vérifier si le nombre de places restantes est inférieur à 4
        if (nbPlacesRestantes <= 4) {
            tv_placesRestantes.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.rouge));
        } else {
            tv_placesRestantes.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.gris));
        }
    }

    public void onClick_reserverTable(View view) {
        Intent monIntent = new Intent(MainActivity.this, ReservationActivity.class);
        Bundle bReservation = new Bundle();
        bReservation.putParcelableArrayList("ARRAYLIST", listeRestaurants);
        bReservation.putParcelableArrayList("ReservationListe", reservationListe);
        monIntent.putExtra("NomResto", restaurantSelectionne.getNomRestaurant());
        monIntent.putExtra("PlacesRestantes", String.valueOf(restaurantSelectionne.getNbPlacesRestantes()));
        monIntent.putExtras(bReservation);
        activityResultLauncher.launch(monIntent);
    }

    public void onClick_AfficherReservation(View view) {
        Intent monIntent = new Intent(MainActivity.this, RestaurantActivity.class);
        Bundle bReservation = new Bundle();
        List<reservation> filteredReservations = new ArrayList<>();
        for (reservation res : reservationListe) {
            if (res.getNomRestaurant().equals(restaurantSelectionne.getNomRestaurant())) {
                filteredReservations.add(res);
            }
        }
        bReservation.putParcelableArrayList("ARRAYLIST", listeRestaurants);
        bReservation.putParcelableArrayList("ReservationListe", reservationListe);
        monIntent.putExtras(bReservation);
        monIntent.putExtra("NomResto", restaurantSelectionne.getNomRestaurant());
        startActivity(monIntent);
    }
}
