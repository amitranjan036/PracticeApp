package com.greedygame.samplelist;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nikhil on 27/2/17.
 */

public class MainActivity2 extends AppCompatActivity {

    ApplicationInfo appInfo;
    Drawable singleIcon;
    String singleAppName;
    PackageInfo pInfo;
    String version;
    int verCode;
    private View.OnClickListener buttonListener;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        final String s = b.getString("myApp");

        try {
            appInfo = this.getPackageManager().getApplicationInfo(s, 0);
            singleAppName = getPackageManager().getApplicationLabel(appInfo).toString();
            singleIcon = getPackageManager().getApplicationIcon(appInfo);
            pInfo = getPackageManager().getPackageInfo(s, 0);
            version = pInfo.versionName;
            verCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        ImageView ivSingleIcon = (ImageView) findViewById(R.id.single_icon);
        TextView tvAppName = (TextView) findViewById(R.id.single_app_name);
        TextView tvPackage = (TextView) findViewById(R.id.single_s);
        TextView tvVersionName = (TextView) findViewById(R.id.single_version);
        TextView tvVersionCode = (TextView) findViewById(R.id.single_ver_code);


        ivSingleIcon.setImageDrawable(singleIcon);
        tvAppName.setText(singleAppName);
        tvPackage.setText(s);
        tvVersionName.setText(version);
        tvVersionCode.setText(""+verCode);


        buttonListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {
                    i = getPackageManager().getLaunchIntentForPackage(s);
                    if (i == null)
                        throw new PackageManager.NameNotFoundException();
                    i.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivity(i);
                    }
                catch (PackageManager.NameNotFoundException e)
                {
                    e.printStackTrace();
                }

                Button btLaunch = (Button) findViewById(R.id.LaunchButton);
                btLaunch.setOnClickListener(buttonListener);


            }
        };
        Button btLaunch = (Button) findViewById(R.id.LaunchButton);
        btLaunch.setOnClickListener(buttonListener);
    }
}