package com.greedygame.samplelist;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhil on 15/2/17.
 */

public class MainActivity1 extends AppCompatActivity {
    ArrayList<String> duplicateappnameArray = new ArrayList<String>();
    ArrayList<String> originalappnameArray = new ArrayList<String>();
    ArrayList<String> packagenameArray = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView list2;
    List<ApplicationInfo> packages;
    ProgressDialog pd;
    String appname;
    PackageManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

          pm = getPackageManager();
          pd =new ProgressDialog(MainActivity1.this);
          list2 = (ListView) findViewById(R.id.listview1);
          adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, originalappnameArray);
          list2.setAdapter(adapter);
          list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id)
              {
                  Intent launchIntent = pm.getLaunchIntentForPackage(packagenameArray.get(position));
                  startActivity(launchIntent);
              }
          });
          new Numberlist().execute(null,null,null);
      }

      public class Numberlist extends AsyncTask<Void,Void,Void>
      {
          @Override
          protected Void doInBackground(Void... params) {

              packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
              for (ApplicationInfo info : packages)
                {
                    appname = pm.getApplicationLabel(info).toString();
                    duplicateappnameArray.add(appname);
                    packagenameArray.add(info.packageName);
                }
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
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
            originalappnameArray.addAll(duplicateappnameArray);
            adapter.notifyDataSetChanged();
            pd.dismiss();

        }

    }

}
