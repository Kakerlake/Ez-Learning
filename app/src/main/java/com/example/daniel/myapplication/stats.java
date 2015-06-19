package com.example.daniel.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


public class stats extends ActionBarActivity {
    MyDBHandler dbHandler;
    float prozent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        dbHandler=new MyDBHandler(this,null,null,1);

       String dbString = dbHandler.maxToString();
       int dbLZeitAnzahl = dbHandler.lZeitToInt();
        int dbKZeitAnzahl = dbHandler.kZeitToInt();
        int nGelerntAnzahl = dbHandler.nGelerntToInt();
        int GelerntAnzahl = dbHandler.GelerntToInt();
        int totalAnzahl = dbHandler.totalToInt();
       if(totalAnzahl != 0) {
           prozent = (GelerntAnzahl * 100) / totalAnzahl;
           prozent = Math.round(prozent);
       }

        //Toast.makeText(this,"" + GelerntAnzahl*100/totalAnzahl, Toast.LENGTH_LONG).show();
        TextView textElement1 = (TextView) findViewById(R.id.Rechts7);
        TextView textElement2 = (TextView) findViewById(R.id.Rechts1);
        TextView textElement3 = (TextView) findViewById(R.id.Rechts2);
        TextView textElement4 = (TextView) findViewById(R.id.Rechts3);
        TextView textElement5 = (TextView) findViewById(R.id.Rechts4);
        TextView textElement6 = (TextView) findViewById(R.id.Rechts5);
        TextView textElement7 = (TextView) findViewById(R.id.Rechts6);

        textElement1.setText(dbString);
        textElement2.setText("" + dbLZeitAnzahl);
        textElement3.setText("" + dbKZeitAnzahl);
        textElement4.setText("" + nGelerntAnzahl);
        textElement5.setText("" + GelerntAnzahl);
        textElement6.setText("" + totalAnzahl);
        textElement7.setText(prozent + "%");

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
