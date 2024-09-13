package com.example.travailpratique1_lounibastor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
    }

    public void onClick_reserverTable(View view) {
        Intent monIntent =  new Intent(MainActivity.this, ReservationActivity.class);
        monIntent.putExtra("Places restantes", tv_placesRestantes.getText().toString());
        startActivity(monIntent);
    }
}