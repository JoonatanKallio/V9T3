package com.example.v9t3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    areaXML areaXML = new areaXML();
    Spinner dropdown;
    TextView movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView movies = (TextView) findViewById(R.id.movies);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Spinner dropdown = (Spinner) findViewById(R.id.area);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, areaXML.readXML());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = dropdown.getSelectedItem().toString(); //Get the name of the selection
                movies.setText(areaXML.specificDate(name).toString()); //Sets the text for scrollview with the different movies returned in stringbuilder from .specificDate
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}