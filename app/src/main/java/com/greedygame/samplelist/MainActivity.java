package com.greedygame.samplelist;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton plus;
    ArrayList<String> bollywood = new ArrayList<>();
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

    String[] hollywood = new String[]{
            "selena",
            "will",
            "emma",
            "tom",
            "robert",
    };
  //  int pos[] = {1,2,3,4,5};
        int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0;i<dummyBollywood.length;i++)
        {
            bollywood.add(dummyBollywood[i]);
        }

//        bollywood = (ArrayList) Arrays.asList(dummyBollywood);

   adapter = new ArrayAdapter<String>(this, R.layout.actingschool, bollywood);
            final ListView list1 = (ListView) findViewById(R.id.listview1);

            list1.setAdapter(adapter);

        plus = (FloatingActionButton) findViewById(R.id.fab);
        plus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(pos>4)
                {
                    Toast.makeText(getApplicationContext(),"Actor/Actress is not available",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    bollywood.add(0,hollywood[pos]);
                    adapter.notifyDataSetChanged();
                    pos++;
                }
            }
        });
    }
}