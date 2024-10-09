package com.example.travailpratique1_lounibastor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapt extends BaseAdapter {
    private Context dContexte;
    private List<reservation> reservationList = new ArrayList<>();
    private TextView tv_nom, tv_placesRes, tv_heureDebut, tv_heurefin;
    private ImageView iv_table;

    public ReservationAdapt(Context _contexte, List<reservation> _list){
        this.dContexte = _contexte;
        this.reservationList = _list;
    }

    @Override
    public int getCount() {
        return reservationList.size();
    }

    @Override
    public reservation getItem(int position) {
        return reservationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(dContexte).inflate(R.layout.list_view_row, parent, false);
        tv_nom = view.findViewById(R.id.tv_nom);
        tv_nom.setText(this.getItem(position).getNomPersonne());

        tv_placesRes = view.findViewById(R.id.tv_nbPlaces);
        tv_placesRes.setText(String.valueOf(this.getItem(position).getNbPlace()) + " places");

        tv_heureDebut = view.findViewById(R.id.tv_heureDebut);
        tv_heureDebut.setText(this.getItem(position).getBlocReservationDebut());

        tv_heurefin = view.findViewById(R.id.tv_heureFin);
        tv_heurefin.setText(this.getItem(position).getBlocReservationFin());

        iv_table = view.findViewById(R.id.iv_table);
        iv_table.setImageResource(R.drawable.tableresto);

        return view;
    }
}
