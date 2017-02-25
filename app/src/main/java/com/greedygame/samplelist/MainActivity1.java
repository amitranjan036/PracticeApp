package com.greedygame.samplelist;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhil on 15/2/17.
 */

public class MainActivity1 extends AppCompatActivity {
    ArrayList<AppObject> duplicateAppObjectArray = new ArrayList<AppObject>();
    ArrayList<AppObject> originalappobjectArray = new ArrayList<AppObject>();
    ArrayList<String> packagenameArray = new ArrayList<String>();
    CustomAdapter adapter;
    ListView list2;
    List<ApplicationInfo> packages;
    ProgressDialog pd;
    String appname;
    PackageManager pm;
    String s;
    Drawable icon;
    EditText searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        pm = getPackageManager();
        pd =new ProgressDialog(MainActivity1.this);
        list2 = (ListView) findViewById(R.id.listview1);
        searchBar = (EditText) findViewById(R.id.searchBar);

        //Nikhil Code to remove
        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();

        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.switch_layout, null);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        //

        adapter = new CustomAdapter(this, android.R.layout.simple_list_item_1, originalappobjectArray);
        list2.setAdapter(adapter);

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id)
              {
                  Intent launchIntent = pm.getLaunchIntentForPackage(packagenameArray.get(position));

                  try{startActivity(launchIntent);}
                  catch (NullPointerException n) {n.printStackTrace();}
              }
          });

        searchBar.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text ["+s+"]");
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        new NumberList().execute(null,null,null);
      }


    public class CustomAdapter extends ArrayAdapter<AppObject> implements Filterable {
            private List<AppObject>originalData = null;
            private List<AppObject>filteredData = null;




        public CustomAdapter(Context context, int resource, List<AppObject> amit) {
            super(context, resource, amit);
            this.filteredData = amit;
            this.originalData = amit;
        }

        public int getCount() {
            return filteredData.size();
        }

        public AppObject getItem(int position) {
            return filteredData.get(position);
        }

        public long getItemId(int position) {
            return position;
        }
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.customlayout, null);

            }

            AppObject appobj = getItem(position);

            if (appobj != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.text_app_name);
                TextView tt2 = (TextView) v.findViewById(R.id.text_package_name);
                ImageView iconview = (ImageView) v.findViewById(R.id.appicon);

                if (tt1 != null) {
                    tt1.setText(appobj.getappName());
                }

                if (tt2 != null) {
                    tt2.setText(appobj.getpacketName());
                }

                try {
                    iconview.setImageDrawable(pm.getApplicationIcon(appobj.getpacketName()));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

            }
            return v;
        }

        @NonNull
        @Override
        public Filter getFilter() {


            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    FilterResults results = new FilterResults();
                    ArrayList<AppObject> filteredAppObjects = new ArrayList<AppObject>();
                    final List<AppObject> list = originalData;
                    String filterString = constraint.toString().toLowerCase();
                    int count = list.size();

                    for(int i = 0; i< count; i++){
                        String appNameToCheck = list.get(i).getappName();
                        String packetNameToCheck = list.get(i).getpacketName();
                        if(appNameToCheck.toLowerCase().contains(filterString))
                        {
                            filteredAppObjects.add(list.get(i));
                        } else if (packetNameToCheck.toLowerCase().contains(filterString)) {
                            filteredAppObjects.add(list.get(i));
                        }

                    }

                    results.count = filteredAppObjects.size();
                    results.values = filteredAppObjects;

                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    filteredData = (ArrayList<AppObject>) results.values;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }
    }


    public class NumberList extends AsyncTask<Void,Void,Void>
      {
          @Override
          protected Void doInBackground(Void... params) {

              packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
              for (ApplicationInfo info : packages)
                {
                    appname = pm.getApplicationLabel(info).toString();
                    packagenameArray.add(info.packageName);
                    s = info.packageName;
                    icon = pm.getApplicationIcon(info);
                    AppObject a = new AppObject();
                    a.setappName(appname);
                    a.setpacketName(s);
                    duplicateAppObjectArray.add(a);
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
            originalappobjectArray.addAll(duplicateAppObjectArray);

            adapter.notifyDataSetChanged();
            pd.dismiss();
        }
    }

}
