package com.example.travailpratique1_lounibastor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spn_nomResto;
    private TextView tv_placesRestantes;


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
        listeRestaurants.add(new restaurant(3,"McHalal",9,9));
        listeRestaurants.add(new restaurant(4,"Tacos del caso",20,20));
        listeRestaurants.add(new restaurant(1,"Fruit el Goju",35,35));
        ArrayAdapter<restaurant> adaptateur = new ArrayAdapter<restaurant>(this, android.R.layout.simple_spinner_item,listeRestaurants);
        adaptateur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_nomResto.setAdapter(adaptateur);
    }

    public void onClick_reserverTable(View view) {
        Intent monIntent =  new Intent(MainActivity.this, ReservationActivity.class);
        monIntent.putExtra("Places restantes", tv_placesRestantes.getText().toString());
        startActivity(monIntent);
    }
}