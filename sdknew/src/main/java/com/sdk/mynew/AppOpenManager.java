package com.sdk.mynew;

import static androidx.lifecycle.Lifecycle.Event.ON_START;
import static com.sdk.mynew.AppPreference.isFullScreenShow;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AppOpenManager implements Application.ActivityLifecycleCallbacks, LifecycleObserver {
    private static final String LOG_TAG = "AppOpenManager";
    private AppOpenAd appOpenAd = null;
    private static boolean isShowingAd = false;
    public static boolean isSplash = false;
    private long loadTime = 0;
    private Activity currentActivity;
    private final MyApplication myApplication;

    /**
     * Constructor
     */
    public AppOpenManager(MyApplication myApplication) {
        this.myApplication = myApplication;
        this.myApplication.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

    }

    /**
     * Request an ad
     */
    public void fetchAd() {
        final List<String> adUnitIds = Arrays.asList(new AppPreference(myApplication).get_Admob_OpenApp_Id1(),
                new AppPreference(myApplication).get_Admob_OpenApp_Id2(),
                new AppPreference(myApplication).get_Admob_OpenApp_Id3());
        if (isAdAvailable()) {
            return;
        }

        AppOpenAd.AppOpenAdLoadCallback loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                Log.e("TAG", "onAdLoaded: ");
                super.onAdLoaded(appOpenAd);
                AppOpenManager.this.appOpenAd = appOpenAd;
                AppOpenManager.this.loadTime = (new Date()).getTime();
                Constant.OpenAdCounter++;
                Log.e("OpenAdCounter", String.valueOf(Constant.OpenAdCounter));
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.e("TAG", "onAdFailedToLoad: " + loadAdError.getMessage());
            }
        };
        if (new AppPreference(myApplication).get_Ad_Status().equalsIgnoreCase("on")) {
            if (Constant.OpenAdCounter > 2) {
                Constant.OpenAdCounter = 0;
            }
            Log.e("OpenAdCounter out", String.valueOf(Constant.OpenAdCounter));
            AdRequest request = getAdRequest();
            AppOpenAd.load(
                    myApplication, adUnitIds.get(Constant.OpenAdCounter), request,
                    AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
        }
        // We will implement this below.
    }

    /**
     * Creates and returns ad request.
     */
    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    /**
     * Utility method that checks if ad exists and can be shown.
     */


    public void showAdIfAvailable() {
        // Only show ad if there is not already an app open ad currently showing
        // and an ad is available.
        if (isFullScreenShow) {
            return;
        }
        if (!isShowingAd && isAdAvailable()) {
            Log.e(LOG_TAG, "Will show ad.");

            FullScreenContentCallback fullScreenContentCallback =
                    new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Set the reference to null so isAdAvailable() returns false.
                            AppOpenManager.this.appOpenAd = null;
                            isShowingAd = false;
                            fetchAd();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            isShowingAd = true;
                        }
                    };
            appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
            appOpenAd.show(currentActivity);

        } else {
            Log.e(LOG_TAG, "Can not show ad.");
            fetchAd();
        }
    }

    public boolean isAdAvailable() {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo();
    }

    private boolean wasLoadTimeLessThanNHoursAgo() {
        long dateDifference = (new Date()).getTime() - this.loadTime;
        long numMilliSecondsPerHour = 3600000;
        return (dateDifference < (numMilliSecondsPerHour * (long) 4));
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        currentActivity = null;
    }

    @OnLifecycleEvent(ON_START)
    public void onStart() {
        if (isSplash) {
            Log.d(LOG_TAG, "no open ad for splash");
        } else {
            showAdIfAvailable();
        }
    }
}