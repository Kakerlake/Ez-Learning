package com.example.daniel.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class schreiben extends ActionBarActivity {
    TextView deutsch;
    MyDBHandler dbHandler;
    String text2;
    String text3;
    String query3;
    EditText edit;
    ImageView image;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schreiben);
        deutsch = (TextView) findViewById(R.id.deutsch);
        edit =(EditText) findViewById(R.id.editText3);
        image = (ImageView) findViewById(R.id.imageView);
        dbHandler = new MyDBHandler(this, null, null, 1);
        next(v);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schreiben, menu);
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

    public void next(View v){

        String query2="SELECT werte FROM woerter ORDER BY werte ASC";
        if (dbHandler.kartenAusgabe(query2, "werte") != null) {
            text2 = dbHandler.kartenAusgabe(query2, "werte");
            query2 = "SELECT deutsch FROM woerter WHERE werte = '" + text2 + "' ORDER BY Random()";
            text3 = dbHandler.kartenAusgabe(query2, "deutsch");
            deutsch.setText("" + text3);
            query3 = "SELECT english FROM woerter WHERE deutsch ='" + text3 + "'";
            text2 = dbHandler.kartenAusgabe(query3, "english");
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this,"Keine Eintraege vorhanden", Toast.LENGTH_LONG).show();
        }

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




    public void buttonClicked(View view) {
    String test = edit.getText().toString();

        if(test .equals( text2)){
            updateWert(v);
            image.setImageResource(R.drawable.checkok);
            image.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    image.setVisibility(View.INVISIBLE);
                }
            }, 1000);
            edit.setText("");
            next(v);

        }else {

            image.setImageResource(R.drawable.checkx);
            image.setVisibility(View.VISIBLE);


            new Handler().postDelayed(new Runnable() {
                public void run() {
                    image.setVisibility(View.INVISIBLE);
                }
            }, 1000);
            edit.setText("");


        }






    }
    public void button4Clicked(View v) {

        String query2="SELECT english FROM woerter WHERE deutsch ='" + text3+"'";
        String text4 = dbHandler.kartenAusgabe(query2, "english");
        Toast.makeText(this, text4, Toast.LENGTH_LONG).show();
        image.setVisibility(View.VISIBLE);
        image.setImageResource(R.drawable.checkx);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                image.setVisibility(View.INVISIBLE);
}
        }, 1000);
        edit.setText("");
        next(v);

        }

        }