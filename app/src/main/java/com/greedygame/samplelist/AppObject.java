package com.greedygame.samplelist;

import android.graphics.drawable.Drawable;

/**
 * Created by nikhil on 21/2/17.
 */

public class AppObject
    {
        public String appName;
        public String packetName;
        public Drawable d_Icon;

        public String getappName()
        {
            return appName;
        }

        public String getpacketName()
        {
            return packetName;
        }

        public  Drawable getd_Icon()
        {
            return getd_Icon();
        }

        public void setappName(String appName)
        {
            this.appName = appName;
        }

        public void setpacketName(String packetName)
        {
            this.packetName = packetName;
        }

        public void setd_Icon(Drawable d_Icon)
        {
            this.d_Icon = d_Icon;
        }
    }
