package com.example.daniel.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




public class Neu extends ActionBarActivity {

     EditText eingabe;
     EditText eingabe2;
    MyDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neu);
        eingabe = (EditText) findViewById(R.id.editText);
        eingabe2 = (EditText) findViewById(R.id.editText2);
        dbHandler=new MyDBHandler(this,null,null,1);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection S implifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void neueKarte(View arg0) {
        KarteiKarte karte = new KarteiKarte(eingabe.getText().toString(), eingabe2.getText().toString());
       dbHandler.addWords(karte);

        Toast.makeText(this, "Karte erfolgreich gespeichert", Toast.LENGTH_LONG).show();
        eingabe.setText("");
        eingabe2.setText("");
        eingabe.requestFocus();
       // printDatabase();

    }


}


