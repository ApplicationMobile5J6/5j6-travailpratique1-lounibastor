package com.example.travailpratique1_lounibastor;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class restaurant implements Parcelable {
    private int noRestaurant;
    private String nomRestaurant;
    private int nbPlacesMax;
    private int nbPlacesRestantes;
    private ArrayList<reservation> listeReservations;

    public restaurant(int noRestaurant, String nomRestaurant, int nbPlacesMax, int nbPlacesRestantes) {
        this.noRestaurant = noRestaurant;
        this.nomRestaurant = nomRestaurant;
        this.nbPlacesMax = nbPlacesMax;
        this.nbPlacesRestantes = nbPlacesRestantes;
        this.listeReservations = new ArrayList<>();
    }

    public int getNoRestaurant() {
        return noRestaurant;
    }

    public void setNoRestaurant(int noRestaurant) {
        this.noRestaurant = noRestaurant;
    }

    public String getNomRestaurant() {
        return nomRestaurant;
    }

    public void setNomRestaurant(String nomRestaurant) {
        this.nomRestaurant = nomRestaurant;
    }

    public int getNbPlacesMax() {
        return nbPlacesMax;
    }

    public void setNbPlacesMax(int nbPlacesMax) {
        this.nbPlacesMax = nbPlacesMax;
    }

    public int getNbPlacesRestantes() {
        return nbPlacesRestantes;
    }

    public void setNbPlacesRestantes(int nbPlacesRestantes) {
        this.nbPlacesRestantes = nbPlacesRestantes;
    }

    public ArrayList<reservation> getListeReservations() {
        return listeReservations;
    }

    public void setListeReservations(ArrayList<reservation> listeReservations) {
        this.listeReservations = listeReservations;
    }

    protected restaurant(Parcel in) {
        noRestaurant = in.readInt();
        nomRestaurant = in.readString();
        nbPlacesMax = in.readInt();
        nbPlacesRestantes = in.readInt();
        listeReservations = in.createTypedArrayList(reservation.CREATOR);
    }

    public static final Creator<restaurant> CREATOR = new Creator<restaurant>() {
        @Override
        public restaurant createFromParcel(Parcel in) {
            return new restaurant(in);
        }

        @Override
        public restaurant[] newArray(int size) {
            return new restaurant[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(noRestaurant);
        dest.writeString(nomRestaurant);
        dest.writeInt(nbPlacesMax);
        dest.writeInt(nbPlacesRestantes);
        dest.writeTypedList(listeReservations);
    }

    @NonNull
    @Override
    public String toString() {
        return nomRestaurant;
    }
}
