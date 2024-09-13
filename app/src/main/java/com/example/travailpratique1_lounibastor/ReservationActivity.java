package com.example.travailpratique1_lounibastor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReservationActivity extends AppCompatActivity {

    private TextView tv_placesRestantes;
    private SeekBar sb_placeRes;
    private TextView tv_placesReserve;

    private static int VALEUR = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reservation);
        Intent deuxiemeIntent = getIntent();
        String msg = deuxiemeIntent.getStringExtra("Places restantes");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv_placesReserve = findViewById(R.id.tv_placesReserve);
        sb_placeRes = findViewById(R.id.sb_placesRes);
        tv_placesRestantes = findViewById(R.id.tv_placesRestantes);

        tv_placesRestantes.setText(msg);

        sb_placeRes.setMax(10);
        sb_placeRes.setProgress(1);

        tv_placesReserve.setText(sb_placeRes.getProgress() + " places reservées");

        sb_placeRes.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progression = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progression = progressValue;
                tv_placesReserve.setText(progressValue + " places reservées" );
                Toast.makeText(getApplicationContext(), "Modification", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Démarrage", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Fin", Toast.LENGTH_SHORT).show();
            }
        });

    }
}