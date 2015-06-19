package com.example.daniel.myapplication;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class loeschen extends ActionBarActivity {

    MyDBHandler dbHandler;
    private ArrayList<String> results = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loeschen);
        dbHandler=new MyDBHandler(this,null,null,1);
        results =  dbHandler.loeschenAusgabe();
        ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results);
        listView.setAdapter(adapter);

        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dbHandler.loeschenMain("" + ((TextView) view).getText());

                adapter.clear();
                ArrayList<String> resultsUpdate = dbHandler.loeschenAusgabe();
                adapter.notifyDataSetChanged();

            }
        });
    }

    public void update (){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Alle Daten erfolgreich geloescht", Toast.LENGTH_SHORT).show();


    }

    private void displayResult(){
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results);

    }
    public void deleteAll(View arg0) {
        dbHandler.allesLoeschen();
        update();
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
