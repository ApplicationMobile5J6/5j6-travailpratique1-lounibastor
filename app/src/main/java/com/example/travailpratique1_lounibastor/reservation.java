package com.example.travailpratique1_lounibastor;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class reservation implements Parcelable {
    private static int compteur = 0;
    private int noReservation, nbPlace;
    private String dateReservation, blocReservationDebut, blocReservationFin, nomPersonne, telPersonne;

    public reservation(String nomPersonne, String blocReservationDebut, String blocReservationFin, int nbPlace, String dateReservation, String telPersonne) {
        this.noReservation = ++compteur;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(noReservation);
        parcel.writeInt(nbPlace);
        parcel.writeString(dateReservation);
        parcel.writeString(blocReservationDebut);
        parcel.writeString(blocReservationFin);
        parcel.writeString(nomPersonne);
        parcel.writeString(telPersonne);
    }
}