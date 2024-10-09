package com.example.travailpratique1_lounibastor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
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

    private TextView tv_placesRestantes, tv_placesReserve, et_date, tv_nomResto, et_heureFin,et_nomPersonne, et_telPersonne;
    private SeekBar sb_placeRes;
    private static int VALEUR = 5;
    private SimpleDateFormat dateFormater, heureFormater;
    private Spinner spn_heureDebut;
    private ArrayList<reservation> listeReservations= new ArrayList<reservation>();



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
        et_nomPersonne = findViewById(R.id.et_nom);
        et_telPersonne =findViewById(R.id.et_numeroTel);

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
        String placeRestante = intent.getStringExtra("PlacesRestantes");
        tv_placesRestantes.setText(placeRestante + " places restantes");

        int nbPlacesRestantes = Integer.parseInt(placeRestante);

// Vérifier si le nombre de places restantes est inférieur ou égal à 4
        if (nbPlacesRestantes <= 4) {
            tv_placesRestantes.setTextColor(Color.RED); // Changer la couleur du texte en rouge
        } else {
            tv_placesRestantes.setTextColor(Color.GRAY); // Optionnel : changer la couleur si supérieur à 4
        }


        sb_placeRes.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progression = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progression = progressValue;
                tv_placesReserve.setText(progressValue + " places reservées" );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }
    private void updateHeureFin(String heureDebut) {
        String[] parts = heureDebut.split(":");
        int heure = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        // Ajouter 1 heure et 30 minutes
        minute += 29;
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

    public void onClick_ReserverTable(View view) {
        String nomPersonne = et_nomPersonne.getText().toString().trim();
        String telPersonne = et_telPersonne.getText().toString().trim();
        String dateReservation = et_date.getText().toString().trim();
        String heureDebut = spn_heureDebut.getSelectedItem().toString();
        int nbPlacesRestantes = Integer.parseInt(tv_placesRestantes.getText().toString().split(" ")[0]);
        int placesReservees = sb_placeRes.getProgress();

        // Validation des champs
        if (nomPersonne.isEmpty() || telPersonne.isEmpty() || dateReservation.isEmpty() || heureDebut.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs.", Toast.LENGTH_LONG).show();
            return;
        }

        // Vérification des places restantes
        if (placesReservees > nbPlacesRestantes) {
            Toast.makeText(this, "Pas assez de places disponibles.", Toast.LENGTH_LONG).show();
            return;
        }
        int nouvellesPlacesRestantes = nbPlacesRestantes - placesReservees;

        tv_placesRestantes.setText(nouvellesPlacesRestantes + " places restantes");

        // Créer une nouvelle réservation
        reservation nouvelleReservation = new reservation("Amine Re", "17h00", "22h00", 2, "test", "2");

        nouvelleReservation.setNoReservation(listeReservations.size() + 1);
        nouvelleReservation.setDateReservation(dateReservation);
        nouvelleReservation.setNbPlace(placesReservees);
        nouvelleReservation.setBlocReservationDebut(heureDebut);
        nouvelleReservation.setBlocReservationFin(et_heureFin.getText().toString());
        nouvelleReservation.setNomPersonne(nomPersonne);
        nouvelleReservation.setTelPersonne(telPersonne);

        // Ajouter la réservation à la liste
        listeReservations.add(nouvelleReservation);


        Toast.makeText(this, "Réservation sauvegardée.", Toast.LENGTH_LONG).show();
        et_nomPersonne.setText("");
        et_telPersonne.setText("");
        et_date.setText("");
        sb_placeRes.setProgress(1);

        Log.d("Reservation", "Réservation #" + nouvelleReservation.getNoReservation() +
                " - Places: " + placesReservees +
                " - Date: " + nouvelleReservation.getDateReservation() +
                " - Heure de début: " + heureDebut);

        // Retourner à MainActivity avec les nouvelles places restantes
        /*Intent retourIntent = new Intent();
        retourIntent.putExtra("NouvellesPlacesRestantes", nouvellesPlacesRestantes);
        setResult(RESULT_OK, retourIntent);
        finish();*/

    }@Override
    public void onBackPressed() {
        // Récupérer le nombre de places restantes après la réservation
        super.onBackPressed();
        int nbPlacesRestantes = Integer.parseInt(tv_placesRestantes.getText().toString().split(" ")[0]);

        // Retourner à MainActivity avec les nouvelles places restantes
        Intent retourIntent = new Intent();
        retourIntent.putExtra("NouvellesPlacesRestantes", nbPlacesRestantes);
        setResult(RESULT_OK, retourIntent);

        // Appeler finish() pour fermer l'activité et revenir à la précédente
        finish();
    }

}