package com.herprogramacion.revistatempor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Creado por Hermosa Programacion
 */
public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.RevistaViewHolder> {

    private List<Lista> items;

    /*
    Habilita o Desahabilita la aparición de miniaturas
     */
    private boolean conMiniaturas;

    public static class RevistaViewHolder extends RecyclerView.ViewHolder {
        // Campos de la lista
        public ImageView imagen;
        public TextView titulo;
        public TextView votos;

        public RevistaViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            titulo = (TextView) v.findViewById(R.id.titulo);
            votos = (TextView) v.findViewById(R.id.votos);
        }
    }

    public ListaAdapter(List<Lista> items, boolean conMiniaturas) {
        this.items = items;
        this.conMiniaturas = conMiniaturas;
    }

    /*
    Añade una lista completa de items
     */
    public void addAll(List<Lista> lista) {
        items.addAll(lista);
        notifyDataSetChanged();
    }

    /*
    Permite limpiar todos los elementos del recycler
     */
    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RevistaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lista, viewGroup, false);
        return new RevistaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RevistaViewHolder viewHolder, int i) {
        if (conMiniaturas) {
            viewHolder.imagen.setVisibility(View.VISIBLE);
            viewHolder.imagen.setImageResource(items.get(i).getIdImagen());
        } else viewHolder.imagen.setVisibility(View.GONE);

        viewHolder.titulo.setText(items.get(i).getTitulo());
        viewHolder.votos.setText("Votos: " + String.valueOf(items.get(i).getVotos()));
    }

    public void setConMiniaturas(boolean conMiniaturas) {
        if (conMiniaturas != this.conMiniaturas) {
            this.conMiniaturas = conMiniaturas;
            // Notificar que las miniaturas fueron afectadas
            notifyDataSetChanged();
        }

    }
}