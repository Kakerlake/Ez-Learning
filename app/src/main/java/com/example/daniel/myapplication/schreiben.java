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
    private int zaehler=0;
    String woerter[];
    EditText edit;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schreiben);
        deutsch = (TextView) findViewById(R.id.deutsch);
        edit =(EditText) findViewById(R.id.editText3);
        image = (ImageView) findViewById(R.id.imageView);
        dbHandler = new MyDBHandler(this, null, null, 1);
        this.woerter=dbHandler.lernen();

        deutsch.setText(woerter[zaehler]);

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
    public void buttonClicked(View view) {
        zaehler++;
        boolean result;
        boolean end=false;

            result= dbHandler.checkEingabe(deutsch.getText().toString(), edit.getText().toString());
            if(result) {
                String query2="SELECT werte FROM woerter where deutsch ='" + deutsch.getText().toString()+"'";
                String test2 = dbHandler.kartenAusgabe(query2, "werte");
                int i=Integer.parseInt(test2.replaceAll("[\\D]", ""));
                i++;
                query2 = "UPDATE woerter SET werte= "+i +" WHERE deutsch='" + deutsch.getText().toString()+"'";
                dbHandler.update(query2);
                image.setVisibility(View.VISIBLE);
                image.setImageResource(R.drawable.checkok);



            }
            else if(result==false) {
                image.setVisibility(View.VISIBLE);
                image.setImageResource(R.drawable.checkx);

            }

        new Handler().postDelayed(new Runnable() {
            public void run() {
                image.setVisibility(View.INVISIBLE);
            }
        }, 1000);




        if(zaehler== woerter.length) {

            new Handler().postDelayed(new Runnable() {
                public void run() {
                }
            }, 1000);
            Toast.makeText(this,"Fertig", Toast.LENGTH_LONG).show();
            edit.setText("");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            deutsch.setText(woerter[zaehler]);
        edit.setText(""); }

    }
    public void button4Clicked(View v) {
     String wort=dbHandler.kartenAusgabe("Select english from woerter where deutsch='" + deutsch.getText().toString() + "'", "english");
      Toast.makeText(this, wort, Toast.LENGTH_SHORT).show();
        image.setVisibility(View.VISIBLE);
        image.setImageResource(R.drawable.checkx);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                image.setVisibility(View.INVISIBLE);
            }
        }, 1000);
        zaehler++;
        if(zaehler<woerter.length) {
            deutsch.setText(woerter[zaehler]);
        }
        else {
            Toast.makeText(this,"Fertig", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                }
            }, 1000);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
       /* Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

            }
        }, 500);*/
    }

}