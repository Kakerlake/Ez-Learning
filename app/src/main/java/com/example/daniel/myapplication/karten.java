package com.example.daniel.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class karten extends ActionBarActivity {
    MyDBHandler dbHandler;
    TextView eins ;
    String text2;
    ImageView richtig;
    Button drei;
    ImageView falsch;
    View v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karten);

        dbHandler=new MyDBHandler(this,null,null,1);
        eins = (TextView) findViewById(R.id.textView);
        richtig = (ImageView) findViewById(R.id.richtig);
        drei = (Button) findViewById(R.id.button3);
        falsch = (ImageView) findViewById(R.id.falsch);
        richtig.setImageResource(R.drawable.checkok);
        falsch.setImageResource(R.drawable.checkx);
        richtig.setClickable(false);
        falsch.setClickable(false);
        next(v);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Hanjke action bar item clicks here. The action bar will
        // automatig cally handle clicks on othe Home/Up button, so long
        // as y ou specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void next(View v){
        drei.setEnabled(true);
        richtig.setClickable(false);
        falsch.setClickable(false);
        richtig.setVisibility(View.INVISIBLE);
        falsch.setVisibility(View.INVISIBLE);
        String query2="SELECT werte FROM woerter ORDER BY werte ASC";
        text2 = dbHandler.kartenAusgabe(query2, "werte");
        query2 = "SELECT deutsch FROM woerter WHERE werte = '"+ text2 +"' ORDER BY Random()";
        text2 = dbHandler.kartenAusgabe(query2, "deutsch");
        eins.setText("" + text2);



    }

    public void onClick(View v){
        drei.setEnabled(false);
        richtig.setClickable(true);
        falsch.setClickable(true);
        String query2="SELECT english FROM woerter WHERE deutsch ='" + text2+"'";
        text2 = dbHandler.kartenAusgabe(query2, "english");
        eins.setText("" + text2);
        richtig.setVisibility(View.VISIBLE);
        falsch.setVisibility(View.VISIBLE);

    }

    public void updateWert(View v){
        String query2="SELECT werte FROM woerter where english ='" + text2+"'";
        String test2 = dbHandler.kartenAusgabe(query2, "werte");
        int i=Integer.parseInt(test2.replaceAll("[\\D]",""));
        i++;
        query2 = "UPDATE woerter SET werte= "+i +" WHERE english='" + text2+"'";
        dbHandler.update(query2);
        next(v);
    }



}



