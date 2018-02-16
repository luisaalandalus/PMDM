package com.alandalus.luisaparragarcia.interfazusuario;

/**
 * Created by luisaparragarcia on 14/11/17.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class  MyRecycledViewAdapter
        extends RecyclerView.Adapter<MyRecycledViewAdapter.PaletteViewHolder>
        implements View.OnClickListener{

    private static String LOG_TAG = "MyRecycledViewAdapter";
    private ArrayList<Palette> datos;
    private View.OnClickListener listener;




    //Contenedor de vista para cada elemento de nuestra RecycledView
    public static class PaletteViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView hexValue;
        private CardView card;

        public PaletteViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            hexValue = (TextView) itemView.findViewById(R.id.hexValue);
            card = (CardView) itemView;
        }

    }



    // Constructor de la clase
    public MyRecycledViewAdapter(ArrayList<Palette> datos) {
        this.datos = datos;
    }


    //instanciamos la vista para añadirla a la jerarquía de vistas y devuelve el adaptador de Palette sin personalizar
    @Override
    public PaletteViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {



        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_row, viewGroup, false);

        view.setOnClickListener(this);

        PaletteViewHolder paleteViewHolder = new PaletteViewHolder(view);
        return paleteViewHolder;
    }

    // Personaliza los datos del viewHolder
    @Override
    public void onBindViewHolder(PaletteViewHolder holder, int position) {
        holder.name.setText(datos.get(position).getName());
        holder.hexValue.setText(datos.get(position).getHexValue());
        holder.card.setCardBackgroundColor(datos.get(position).getIntValue());
    }

    //Numero de elementos de la vista
    @Override
    public int getItemCount() {
        return datos.size();
    }


    //Definimos el listener para los elemnetos de nuestra vista
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

}
