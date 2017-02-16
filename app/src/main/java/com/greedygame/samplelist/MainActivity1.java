package com.greedygame.samplelist;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DialogTitle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by nikhil on 15/2/17.
 */

public class MainActivity1 extends AppCompatActivity {
    ArrayList<Integer> innerArray = new ArrayList<Integer>();
    ArrayAdapter<Integer> adapter;
    ListView list2;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        pd =new ProgressDialog(MainActivity1.this);
        list2 = (ListView) findViewById(R.id.listview1);
        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, innerArray);
        new Numberlist().execute(null,null,null);
    }

    public class Numberlist extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            for (int i =1; i<=10000; i++)
                {
                    innerArray.add(i);
                }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("loading");
            pd.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            list2.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            pd.dismiss();
        }

    }

}
