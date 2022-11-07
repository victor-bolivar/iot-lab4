package com.example.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4.beans.Hito;

import java.util.ArrayList;

public class ListaHitosAdapter extends RecyclerView.Adapter<ListaHitosAdapter.HitosViewHolder> {
    private Context context;
    private ArrayList<Hito> listaHitos;

    public ListaHitosAdapter(Context context, ArrayList<Hito> listaHitos) {
        this.context = context;
        this.listaHitos = listaHitos;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Hito> getListaHitos() {
        return listaHitos;
    }

    public void setListaHitos(ArrayList<Hito> listaHitos) {
        this.listaHitos = listaHitos;
    }


    public class HitosViewHolder extends RecyclerView.ViewHolder {
        Hito hito;
        public HitosViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    @NonNull
    @Override
    public HitosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.hitos_rv, parent, false);
        return new HitosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HitosViewHolder holder, int position) {
        Hito hito = listaHitos.get(position);
        holder.hito = hito;
        TextView textNombEquip = holder.itemView.findViewById(R.id.textViewNombEquipoCamb);
        TextView textJug = holder.itemView.findViewById(R.id.textViewJugCamb);
        TextView textHit = holder.itemView.findViewById(R.id.textViewHitCamb);

        String nombMasApelli=hito.getNombreJugador()+" "+hito.getApellidoJugador();
        textNombEquip.setText(hito.getNombreEquipo());
        textJug.setText(nombMasApelli);
        textHit.setText(hito.getHito());

    }

    @Override
    public int getItemCount() {
        return getListaHitos().size();
    }
}
