package com.example.bruno.truco.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bruno.truco.R;
import com.example.bruno.truco.model.Partida;

import java.util.List;

public class PartidaAdapter extends ArrayAdapter<Partida> {

    private int rId;


    public PartidaAdapter(@NonNull Context context, int resource, @NonNull List<Partida> objects) {
        super(context, resource, objects);

        rId = resource;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(rId, null);
        }

        Partida partida = getItem(position);

        TextView textJogador1 = mView.findViewById(R.id.textJogador1);
        TextView textJogador2 = mView.findViewById(R.id.textJogador2);
        TextView textResultado = mView.findViewById(R.id.textResultado);

        textJogador1.setText(partida.getJogador1());
        textJogador2.setText(partida.getJogador2());

        if(partida.isVitoria() == true)
            textResultado.setText("Sim");
        else
            textResultado.setText("Não");

        return mView;
    }
}
