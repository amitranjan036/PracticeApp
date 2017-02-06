package com.greedygame.samplelist;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton plus;

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
            final ListView list = (ListView) findViewById(R.id.listview1);

            list.setAdapter(adapter);

        plus = (FloatingActionButton) findViewById(R.id.fab);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


            }
        });



    }


        }