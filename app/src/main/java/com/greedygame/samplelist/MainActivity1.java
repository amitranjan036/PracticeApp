package com.greedygame.samplelist;
import android.app.ProgressDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhil on 15/2/17.
 */

public class MainActivity1 extends AppCompatActivity {
    ArrayList<String> innerArray = new ArrayList<String>();
    ArrayList<String> outerArray = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView list2;
    List<ApplicationInfo> packages;
    ProgressDialog pd;
    String appname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

          pd =new ProgressDialog(MainActivity1.this);
          list2 = (ListView) findViewById(R.id.listview1);
          adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, outerArray);
          list2.setAdapter(adapter);
          new Numberlist().execute(null,null,null);
      }

      public class Numberlist extends AsyncTask<Void,Void,Void>
      {
          @Override
          protected Void doInBackground(Void... params) {
              final PackageManager pm = getPackageManager();
              packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
              for (ApplicationInfo info : packages)
                {
                    appname = pm.getApplicationLabel(info).toString();
                    innerArray.add(appname);
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
            outerArray.addAll(innerArray);
            adapter.notifyDataSetChanged();
            pd.dismiss();
        }

    }

}
