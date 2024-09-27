package com.example.travailpratique1_lounibastor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ReservationActivity extends AppCompatActivity {

    private TextView tv_placesRestantes, tv_placesReserve, et_date, tv_nomResto, et_heureFin;
    private SeekBar sb_placeRes;
    private static int VALEUR = 5;
    private SimpleDateFormat dateFormater, heureFormater;
    private Spinner spn_heureDebut;

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

        et_date = findViewById(R.id.et_date);
        tv_placesReserve = findViewById(R.id.tv_placesReserve);
        sb_placeRes = findViewById(R.id.sb_placesRes);
        tv_placesRestantes = findViewById(R.id.tv_placesRestantes);
        tv_nomResto = findViewById(R.id.tv_nomResto);
        tv_placesRestantes.setText(msg);
        spn_heureDebut = findViewById(R.id.spn_heureDebut);
        et_heureFin = findViewById(R.id.et_heureFin);
        ArrayList<String> listHeure = new ArrayList<>();
        listHeure.add("16:00");
        listHeure.add("17:30");
        listHeure.add("19:00");
        listHeure.add("20:30");
        listHeure.add("22:00");

        ArrayAdapter<String> adaptateur = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listHeure);

        adaptateur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_heureDebut.setAdapter(adaptateur);
        et_date.setInputType(InputType.TYPE_NULL);
        dateFormater = new SimpleDateFormat("yyyy-MM--dd", Locale.CANADA_FRENCH);

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                DatePickerDialog calendrier = new DatePickerDialog(ReservationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        Calendar nouvelleDate = Calendar.getInstance();
                        nouvelleDate.set(year, month, dayOfMonth);
                        et_date.setText(dateFormater.format(nouvelleDate.getTime()));
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                calendrier.show();
            }
        });

        spn_heureDebut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTime = (String) parent.getItemAtPosition(position);
                updateHeureFin(selectedTime);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Aucune action nécessaire
            }
        });

        sb_placeRes.setMax(10);
        sb_placeRes.setProgress(1);

        tv_placesReserve.setText(sb_placeRes.getProgress() + " places reservées");
        Intent intent = getIntent();
        Bundle bReservation = intent.getExtras();
        String nomResto = intent.getStringExtra("NomResto");
        tv_nomResto.setText(nomResto);


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
    private void updateHeureFin(String heureDebut) {
        String[] parts = heureDebut.split(":");
        int heure = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        // Ajouter 1 heure et 30 minutes
        minute += 30;
        if (minute >= 60) {
            minute -= 60;
            heure++;
        }
        heure += 1; // Ajouter 1 heure

        // Gérer l'heure au-delà de 23
        if (heure >= 24) {
            heure -= 24;
        }

        // Formatage de l'heure de fin
        String formattedHeureFin = String.format(Locale.CANADA_FRENCH, "%02d:%02d", heure, minute);
        et_heureFin.setText(formattedHeureFin);
    }
}