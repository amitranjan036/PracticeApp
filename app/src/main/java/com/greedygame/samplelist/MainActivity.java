package com.greedygame.samplelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] Bollywood = new String[]{
            "Akshay",
            "Aamir",
            "Amitabh",
            "Ajay",
            "Hrithik",
            "Salman",
            "Shahrukh",
            "Nawaz",
            "John",
            "Varun",
            "Manoj",
            "Siddharth",
            "Alia",
            "Deepika",
            "Katrina",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.actingschool, Bollywood);
            ListView list = (ListView) findViewById(R.id.listview1);

            list.setAdapter(adapter);
    }
}