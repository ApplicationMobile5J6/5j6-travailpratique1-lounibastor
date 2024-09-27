package com.example.travailpratique1_lounibastor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spn_nomResto;
    private TextView tv_placesRestantes;
    ArrayList<restaurant> listeRestaurants= new ArrayList<>();
    private restaurant restaurantSelectionne;

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

        ImageView img = (ImageView) findViewById(R.id.iv_resto);
        img.setImageResource(R.drawable.resto);

        spn_nomResto = (Spinner) findViewById(R.id.spn_nomResto);
        tv_placesRestantes = (TextView) findViewById(R.id.tv_placesReserve);

        ArrayList<restaurant> listeRestaurants= new ArrayList<>();
        listeRestaurants.add(new restaurant(1,"Bnine",15,15));
        listeRestaurants.add(new restaurant(2,"CHEZ RAMS",10,10));
        listeRestaurants.add(new restaurant(3,"McHalal",9,4));
        listeRestaurants.add(new restaurant(4,"Tacos del caso",20,20));
        listeRestaurants.add(new restaurant(1,"Fruit el Goju",35,35));
        ArrayAdapter<restaurant> adaptateur = new ArrayAdapter<restaurant>(this, android.R.layout.simple_spinner_item,listeRestaurants);
        adaptateur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_nomResto.setAdapter(adaptateur);

        spn_nomResto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                restaurantSelectionne = (restaurant) spn_nomResto.getSelectedItem();
                if(restaurantSelectionne.getNbPlacesRestantes() <= 4){
                    tv_placesRestantes.setTextColor(ContextCompat.getColor(MainActivity.this,
                            R.color.rouge));
                } else{
                    tv_placesRestantes.setTextColor(Color.GRAY);
                }
                tv_placesRestantes.setText(String.valueOf(restaurantSelectionne.getNbPlacesRestantes()) + " places restantes");
                Toast.makeText(parent.getContext(),"Selection:   " + restaurantSelectionne,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void onClick_reserverTable(View view) {
        Intent monIntent =  new Intent(MainActivity.this, ReservationActivity.class);
        Bundle bReservation = new Bundle();
        bReservation.putParcelableArrayList("ARRAYLIST",listeRestaurants);
        monIntent.putExtra("NomResto", restaurantSelectionne.getNomRestaurant());
        monIntent.putExtra("PlacesRestantes", String.valueOf(restaurantSelectionne.getNbPlacesRestantes()));
        monIntent.putExtras(bReservation);
        startActivity(monIntent);
    }

    public void onClick_AfficherReservation(View view) {
        Intent monIntent =  new Intent(MainActivity.this, RestaurantActivity.class);
        Bundle bReservation = new Bundle();
        bReservation.putParcelableArrayList("ARRAYLIST",listeRestaurants);
        monIntent.putExtra("NomResto", restaurantSelectionne.getNomRestaurant());
        startActivity(monIntent);
    }
}