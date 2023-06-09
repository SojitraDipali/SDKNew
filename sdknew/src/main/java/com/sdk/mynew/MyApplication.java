package com.sdk.mynew;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MyApplication extends MultiDexApplication {
    private static MyApplication instance;

    public static void loadAds(OnInitializationCompleteListener listener) {
        MobileAds.initialize(
                instance,
                initializationStatus -> {
                    listener.onInitializationComplete(initializationStatus);
                    if (new AppPreference(instance).getOpenflag().equalsIgnoreCase("on")) {
                        new AppOpenManager(instance);
                    }
                    if (new AppPreference(instance).get_Ad_Status().equalsIgnoreCase("on")) {
                        if (new AppPreference(instance).getNativeflag().equalsIgnoreCase("on")) {
                            new Native_Ads_Load(instance);
                        }
                    }
                });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AudienceNetworkAds.initialize(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
