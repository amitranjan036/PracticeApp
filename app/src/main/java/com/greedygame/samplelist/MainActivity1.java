package com.greedygame.samplelist;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhil on 15/2/17.
 */

public class MainActivity1 extends AppCompatActivity {
    ArrayList<AppObject> duplicateappobjectArray = new ArrayList<AppObject>();
    ArrayList<AppObject> originalappobjectArray = new ArrayList<AppObject>();
    ArrayList<String> packagenameArray = new ArrayList<String>();
    CustomAdapter adapter;
    ListView list2;
    List<ApplicationInfo> packages;
    ProgressDialog pd;
    String appname;
    PackageManager pm;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

          pm = getPackageManager();
          pd =new ProgressDialog(MainActivity1.this);
          list2 = (ListView) findViewById(R.id.listview1);
         adapter = new CustomAdapter(this, android.R.layout.simple_list_item_1, originalappobjectArray);
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


    public class CustomAdapter extends ArrayAdapter<AppObject>
    {


        public CustomAdapter(Context context, int resource, List<AppObject> nikhil) {
            super(context, resource, nikhil);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v==null)
            {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.customlayout, null);

            }

             AppObject appobj = getItem(position);

            if (appobj != null)
            {
                TextView tt1 = (TextView) v.findViewById(R.id.text_app_name);
                TextView tt2 = (TextView) v.findViewById(R.id.text_package_name);

                if (tt1 != null) {
                    tt1.setText(appobj.getappName());
                }

                if (tt2 != null) {
                    tt2.setText(appobj.getpacketName());
                }
            }
            return v;
        }
    }




    public class Numberlist extends AsyncTask<Void,Void,Void>
      {
          @Override
          protected Void doInBackground(Void... params) {

              packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
              for (ApplicationInfo info : packages)
                {
                    appname = pm.getApplicationLabel(info).toString();
                    packagenameArray.add(info.packageName);
                    s = info.packageName;
                    AppObject a = new AppObject();
                    a.setappName(appname);
                    a.setpacketName(s);
                    duplicateappobjectArray.add(a);
                }
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pd.setMessage("loading");
            pd.show();
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            originalappobjectArray.addAll(duplicateappobjectArray);
            adapter.notifyDataSetChanged();
            pd.dismiss();
        }
    }
}
