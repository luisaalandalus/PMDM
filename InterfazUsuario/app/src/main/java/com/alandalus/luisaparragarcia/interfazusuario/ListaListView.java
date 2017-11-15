package com.alandalus.luisaparragarcia.interfazusuario;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;

public class ListaListView extends AppCompatActivity {
    private TextView lblEtiqueta;
    private ListView lstOpciones;

    private Titular[] datos =
            new Titular[]{
                    new Titular("Título 1", "Subtítulo largo 1"),
                    new Titular("Título 2", "Subtítulo largo 2"),
                    new Titular("Título 3", "Subtítulo largo 3"),
                    new Titular("Título 4", "Subtítulo largo 4"),
                    new Titular("Título 5", "Subtítulo largo 5"),
                    new Titular("Título 6", "Subtítulo largo 6"),
                    new Titular("Título 7", "Subtítulo largo 7"),
                    new Titular("Título 8", "Subtítulo largo 8"),
                    new Titular("Título 9", "Subtítulo largo 9"),
                    new Titular("Título 10", "Subtítulo largo 10"),
                    new Titular("Título 11", "Subtítulo largo 11"),
                    new Titular("Título 12", "Subtítulo largo 12"),
                    new Titular("Título 13", "Subtítulo largo 13"),
                    new Titular("Título 14", "Subtítulo largo 14"),
                    new Titular("Título 15", "Subtítulo largo 15")};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_list_view);


        //Ejemplo básico
//        final String[] datos =
//                new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};
//
//        ArrayAdapter<String> adaptador =
//                new ArrayAdapter<String>(this,
//                        android.R.layout.simple_list_item_1, datos);
//
//        lstOpciones = (ListView)findViewById(R.id.LstOpciones);
//
//        lstOpciones.setAdapter(adaptador);



        lblEtiqueta = (TextView)findViewById(R.id.LblEtiqueta);
        lstOpciones = (ListView)findViewById(R.id.LstOpciones);

        //Cabecera
        View header = getLayoutInflater().inflate(R.layout.list_header, null);




        lstOpciones.addHeaderView(header,null,false);

        //Adaptador
        AdaptadorTitulares adaptador =
                new AdaptadorTitulares(this, datos);

        lstOpciones.setAdapter(adaptador);

        //Eventos
        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                //Alternativa 1:
                String opcionSeleccionada =
                        ((Titular)a.getItemAtPosition(position)).getTitulo();

                //Alternativa 2:
                //String opcionSeleccionada =
                //		((TextView)v.findViewById(R.id.LblTitulo))
                //			.getText().toString();

                lblEtiqueta.setText("Opción seleccionada: " + opcionSeleccionada);
            }
        });




    }

    class AdaptadorTitulares extends ArrayAdapter<Titular> {

        public AdaptadorTitulares(Context context, Titular[] datos) {
            super(context, R.layout.listitem_titutar, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

// CÓDIGO SIN OPTIMIZAR
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            View item = inflater.inflate(R.layout.listitem_titutar, null);
//
//            TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
//            lblTitulo.setText(datos[position].getTitulo());
//
//            TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
//            lblSubtitulo.setText(datos[position].getSubtitulo());

            // CODIGO USANDO convertView y ViewHolder para optimización



            View item = convertView;
            ViewHolder holder;

            if(item == null)
            {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                item = inflater.inflate(R.layout.listitem_titutar, null);

                holder = new ViewHolder();
                holder.titulo = (TextView)item.findViewById(R.id.LblTitulo);
                holder.subtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);

                item.setTag(holder);
            }
            else
            {
                holder = (ViewHolder)item.getTag();
            }

            holder.titulo.setText(datos[position].getTitulo());
            holder.subtitulo.setText(datos[position].getSubtitulo());

            return(item);
        }
    }

    static class ViewHolder {
        TextView titulo;
        TextView subtitulo;
    }
}
