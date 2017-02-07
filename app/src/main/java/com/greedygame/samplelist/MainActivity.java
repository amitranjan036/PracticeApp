package com.greedygame.samplelist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton plus;
    ArrayList<String> bollywood = new ArrayList<>();
    ArrayList<String> hollywood = new ArrayList<>();
    ArrayAdapter<String> adapter;


    String[] dummyBollywood = new String[]{
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

    String[] dummyHollywood = new String[]{
            "selena",
            "will",
            "emma",
            "tom",
            "robert",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < dummyBollywood.length; i++) {
            bollywood.add(dummyBollywood[i]);
        }

        for (int j = 0; j < dummyHollywood.length; j++) {
            hollywood.add(dummyHollywood[j]);
        }

        adapter = new ArrayAdapter<String>(this, R.layout.actingschool, bollywood);
        final ListView list1 = (ListView) findViewById(R.id.listview1);

        list1.setAdapter(adapter);

        plus = (FloatingActionButton) findViewById(R.id.fab);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hollywood.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Actor/Actress is not available", Toast.LENGTH_SHORT).show();
                } else {
                    bollywood.add(0, hollywood.get(0));
                    adapter.notifyDataSetChanged();
                    hollywood.remove(0);
                }
            }
        });
    }
}