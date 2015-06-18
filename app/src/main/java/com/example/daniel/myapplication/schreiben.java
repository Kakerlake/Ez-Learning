package com.example.daniel.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class schreiben extends ActionBarActivity {
    TextView deutsch;
    MyDBHandler dbHandler;
    private int zaehler=0;
    String woerter[];
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schreiben);
        deutsch = (TextView) findViewById(R.id.deutsch);
        edit =(EditText) findViewById(R.id.editText3);
        dbHandler = new MyDBHandler(this, null, null, 1);
        this.woerter=dbHandler.lernen();

        deutsch.setText(woerter[zaehler]);
        zaehler++;
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
        boolean result;
        if (zaehler <= (dbHandler.anzahl)) {

            result= dbHandler.checkEingabe(deutsch.getText().toString(), edit.getText().toString());
            if(result) {
                String query2="SELECT werte FROM woerter where deutsch ='" + deutsch.getText().toString()+"'";
                String test2 = dbHandler.kartenAusgabe(query2, "werte");
                int i=Integer.parseInt(test2.replaceAll("[\\D]", ""));
                i++;
                query2 = "UPDATE woerter SET werte= "+i +" WHERE deutsch='" + deutsch.getText().toString()+"'";
                dbHandler.update(query2);
                Toast.makeText(this,"richtig", Toast.LENGTH_LONG).show();

            }
            else if(result==false) {
                Toast.makeText(this,"falsch", Toast.LENGTH_LONG).show();
            }
            deutsch.setText(woerter[zaehler]);


            zaehler++;
            edit.setText("");
        }
        else {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }


}