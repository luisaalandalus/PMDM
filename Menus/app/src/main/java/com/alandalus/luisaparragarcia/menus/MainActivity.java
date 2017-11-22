package com.alandalus.luisaparragarcia.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final String MENU_TAG ="ITEM_SELECTED";
    private String subMenuType = "";
    private Boolean cambiado = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.stores:
                showStore();
                return true;
            case R.id.coupons:
                showCoupons();
                return true;
            case R.id.cashback:
                showCashback();
                return true;
            case R.id.deals:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        if("coupons".equals(subMenuType)&&!cambiado){
            inflater.inflate(R.menu.menu_dinamico, menu);
            cambiado=true;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void invalidateOptionsMenu(String type){
        subMenuType = type;
    }

    public void showCoupons(){
        //call invalidate to update menu
        invalidateOptionsMenu("coupons");

        TextView tv = (TextView)this.findViewById(R.id.textView);
        tv.setText("CUPONES");


    }
    public void showStore(){
        Intent i = new Intent();
        i.setClass(this, Main2Activity.class);
        startActivity(i);
    }
    public void showCashback(){
        Intent i = new Intent();
        i.setClass(this, Main3Activity.class);
        startActivity(i);
    }
}
