package com.alandalus.luisaparragarcia.menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView store1;
    private TextView lblMensaje;
    private ListView lstLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        store1 = (TextView)findViewById(R.id.store1);
        lstLista = (ListView)findViewById(R.id.LstLista);

        //Rellenamos la lista con datos de ejemplo
        String[] datos =
                new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, datos);

        lstLista.setAdapter(adaptador);


        lblMensaje = (TextView)findViewById(R.id.mensaje);
        //asociamos menu contextual al componente
        registerForContextMenu(store1);
        registerForContextMenu(lstLista);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        if(v.getId() == R.id.store1)
            inflater.inflate(R.menu.menu_ctx_etiqueta, menu);
        else if(v.getId() == R.id.LstLista)
        {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) menuInfo;

            menu.setHeaderTitle(
                    lstLista.getAdapter().getItem(info.position).toString());

            inflater.inflate(R.menu.menu_ctx_lista, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.CtxLblOpc1:
                lblMensaje.setText("Etiqueta: Opcion 1 pulsada!");
                return true;
            case R.id.CtxLblOpc2:
                lblMensaje.setText("Etiqueta: Opcion 2 pulsada!");
                return true;
            case R.id.CtxLstOpc1:
                lblMensaje.setText("Lista[" + info.position + "]: Opcion 1 pulsada!");
                return true;
            case R.id.CtxLstOpc2:
                lblMensaje.setText("Lista[" + info.position + "]: Opcion 2 pulsada!");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
