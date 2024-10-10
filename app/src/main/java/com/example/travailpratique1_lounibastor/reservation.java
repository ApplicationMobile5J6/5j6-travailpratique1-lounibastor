package com.example.travailpratique1_lounibastor;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class reservation implements Parcelable {
    private static int compteur = 0;
    private int noReservation, nbPlace; // Retirer noRestaurant
    private String dateReservation, blocReservationDebut, blocReservationFin, nomPersonne, telPersonne;
    private String nomRestaurant; // Ajouter nomRestaurant

    public reservation(String nomRestaurant, String nomPersonne, String blocReservationDebut, String blocReservationFin, int nbPlace, String dateReservation, String telPersonne) {
        this.noReservation = ++compteur;
        this.nomRestaurant = nomRestaurant;
        this.nomPersonne = nomPersonne;
        this.blocReservationDebut = blocReservationDebut;
        this.blocReservationFin = blocReservationFin;
        this.nbPlace = nbPlace;
        this.dateReservation = dateReservation;
        this.telPersonne = telPersonne;
    }

    protected reservation(Parcel in) {
        noReservation = in.readInt();
        nbPlace = in.readInt();
        dateReservation = in.readString();
        blocReservationDebut = in.readString();
        blocReservationFin = in.readString();
        nomPersonne = in.readString();
        telPersonne = in.readString();
        nomRestaurant = in.readString(); // Lecture de nomRestaurant
    }

    public static final Creator<reservation> CREATOR = new Creator<reservation>() {
        @Override
        public reservation createFromParcel(Parcel in) {
            return new reservation(in);
        }

        @Override
        public reservation[] newArray(int size) {
            return new reservation[size];
        }
    };

    public int getNoReservation() {
        return noReservation;
    }

    public void setNoReservation(int noReservation) {
        this.noReservation = noReservation;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getBlocReservationDebut() {
        return blocReservationDebut;
    }

    public void setBlocReservationDebut(String blocReservationDebut) {
        this.blocReservationDebut = blocReservationDebut;
    }

    public String getBlocReservationFin() {
        return blocReservationFin;
    }

    public void setBlocReservationFin(String blocReservationFin) {
        this.blocReservationFin = blocReservationFin;
    }

    public String getNomPersonne() {
        return nomPersonne;
    }

    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    public String getTelPersonne() {
        return telPersonne;
    }

    public void setTelPersonne(String telPersonne) {
        this.telPersonne = telPersonne;
    }

    public String getNomRestaurant() {
        return nomRestaurant;
    }

    public void setNomRestaurant(String nomRestaurant) {
        this.nomRestaurant = nomRestaurant;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeInt(noReservation);
        parcel.writeInt(nbPlace);
        parcel.writeString(dateReservation);
        parcel.writeString(blocReservationDebut);
        parcel.writeString(blocReservationFin);
        parcel.writeString(nomPersonne);
        parcel.writeString(telPersonne);
        parcel.writeString(nomRestaurant);
    }
}
