package com.example.daniel.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class loeschen extends ActionBarActivity {

    MyDBHandlerStats dbHandler;
    private ArrayList<String> results = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loeschen);
        dbHandler=new MyDBHandlerStats(this,null,null,1);
        results =  dbHandler.loeschenAusgabe();
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results);
        listView.setAdapter(adapter);

    }

    private void displayResult(){
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loeschen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
