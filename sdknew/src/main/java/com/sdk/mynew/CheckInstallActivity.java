package com.sdk.mynew;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CheckInstallActivity {
    static InstallReferrerClient referrerClient;
    public static String referrerUrl = "NA";

    public static boolean checkReferrer(String url, String medium) {
        String[] splitParts = medium.split(",");
        if (TextUtils.isEmpty(url)) {
            return true;
        }
        for (String abc : splitParts) {
            if (url.toLowerCase(Locale.getDefault()).contains(abc.toLowerCase(Locale.getDefault())))
                return true;
        }
        return false;
    }

    public static boolean checkScreenFlag(Activity activity) {
        boolean isOrganic = checkIsOrganic(activity);
        AppPreference preference = new AppPreference(activity);
        boolean isScreenOn = preference.getScreen().equals("on");
        return isScreenOn && !isOrganic;
    }

    public static void startSDKActivity(Activity activity, Intent intent) {
        AppPreference preference = new AppPreference(activity);
        boolean check = checkReferrer(referrerUrl, preference.getMedium());
        if (check) {
            preference.setCheckinstallbool(false);
            startAdLoading(activity, preference, intent);
        } else {
            preference.setCheckinstallbool(true);
            startAdLoading(activity, preference, intent);
        }
    }

    public static void CallOpenAd(AppPreference preference, Activity activity, Intent intent) {
        String string = preference.get_Splash_OpenApp_Id();
        if (AppPreference.isFullScreenShow) {
            return;
        }
        try {
            AppOpenAd.AppOpenAdLoadCallback loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
                public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                    super.onAdLoaded(appOpenAd);
                    FullScreenContentCallback r0 = new FullScreenContentCallback() {
                        @Override
                        public void onAdShowedFullScreenContent() {
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            activity.startActivity(intent);
                            activity.finish();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            activity.startActivity(intent);
                            activity.finish();
                        }
                    };
                    appOpenAd.show(activity);
                    appOpenAd.setFullScreenContentCallback(r0);
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    activity.startActivity(intent);
                    activity.finish();
                }
            };
            AppOpenAd.load(activity, string, new AdRequest.Builder().build(), 1, loadCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void startAdLoading(Activity activity, AppPreference preference, Intent intent) {
        MyApplication.loadAds(initializationStatus -> {
            if (preference.get_splash_flag().equalsIgnoreCase("open")) {
                if (preference.getOpenflag().equalsIgnoreCase("on")) {
                    CallOpenAd(preference, activity, intent);
                } else {
                    activity.startActivity(intent);
                }
            } else {
                if (preference.getFullflag().equalsIgnoreCase("on")) {
                    new Handler().postDelayed(() -> new Interstitial_Ads_Splash().Show_Ads(activity, intent, true), 1000);
                } else {
                    new Handler().postDelayed(() -> activity.startActivity(intent), 1000);
                }
            }
        });
    }


    public static boolean checkIsOrganic(Activity activity) {
        AppPreference preference = new AppPreference(activity);
        String[] splitParts = preference.getMedium().split(",");
        if (TextUtils.isEmpty(preference.getReferrerUrl())) {
            return true;
        }
        for (String abc : splitParts) {
            if (preference.getReferrerUrl().toLowerCase(Locale.getDefault()).contains(abc.toLowerCase(Locale.getDefault())))
                return true;
        }
        return false;
    }

    //check install
    public static void checkinstallreferre(Activity activity, ReferrerListener referrerListener) {
        AppPreference preference = new AppPreference(activity);
        referrerClient = InstallReferrerClient.newBuilder(activity).build();
        referrerClient.startConnection(new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                switch (responseCode) {
                    case InstallReferrerClient.InstallReferrerResponse.OK:
                        try {
                            ReferrerDetails response = referrerClient.getInstallReferrer();
                            referrerUrl = response.getInstallReferrer();
                            preference.setReferrerUrl(referrerUrl);
                            if (preference.getShowinstall().equalsIgnoreCase("on")) {
                                Toast.makeText(activity, "referrer :" + referrerUrl, Toast.LENGTH_SHORT).show();
                            }
                            referrerListener.referrerDone();
                        } catch (RemoteException e) {
                            referrerListener.referrerCancel();
                            Log.e("insref", "" + e.getMessage());
                        }
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                        referrerListener.referrerCancel();
                        Log.w("insref", "InstallReferrer Response.FEATURE_NOT_SUPPORTED");
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                        referrerListener.referrerCancel();
                        Log.w("insref", "InstallReferrer Response.SERVICE_UNAVAILABLE");
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_DISCONNECTED:
                        referrerListener.referrerCancel();
                        Log.w("insref", "InstallReferrer Response.SERVICE_DISCONNECTED");
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.DEVELOPER_ERROR:
                        referrerListener.referrerCancel();
                        Log.w("insref", "InstallReferrer Response.DEVELOPER_ERROR");
                        break;
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {
                Log.w("insref", "InstallReferrer onInstallReferrerServiceDisconnected()");
            }
        });
    }

    private static void postDataUsing(Activity activity, String country, String
            vconnectstatus, String pkg) {
        // url to post our data
        String url = "http://143.110.180.86/userdata/package.php?";
        RequestQueue queue = Volley.newRequestQueue(activity);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            // inside on response method we are
            // hiding our progress bar
            // and setting data to edit text as empty

            // on below line we are displaying a success toast message.
            //Toast.makeText(activity, "Data added to API" + response, Toast.LENGTH_SHORT).show();
        }, error -> {
            // method to handle errors.
            Toast.makeText(activity, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<>();

                // on below line we are passing our key
                // and value pair to our parameters.
                params.put("country", country);
                params.put("vpn", vconnectstatus);
                params.put("packagename", pkg);
                params.put("medium", referrerUrl);

                // at last we are
                // returning our params.
                return params;
            }
        };
        queue.add(request);
        // creating a new variable for our request queue

    }
}
