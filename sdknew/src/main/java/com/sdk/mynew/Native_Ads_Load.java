package com.sdk.mynew;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;


public class Native_Ads_Load {
    public static Context context;
    public static AppPreference preference;
    public static ArrayList<NativeAd> mNativeAdsGHome;
    public static ArrayList<String> mNativeAdsId;
    int native_ads_count = 0;
    static int adCounter = -1;

    public static NativeAd getNextNativeAd() {
        if (mNativeAdsGHome != null && mNativeAdsGHome.size() > 0) {
            return mNativeAdsGHome.get(getCounter());
        } else {
            return null;
        }
    }

    public static int getCounter() {
        updateCounter();
        return adCounter;
    }

    public static void updateCounter() {
        adCounter++;
        if (adCounter >= mNativeAdsGHome.size()) {
            adCounter = 0;
        }
    }

    public Native_Ads_Load(Context activity) {
        context = activity;
        mNativeAdsId = new ArrayList<>();
        preference = new AppPreference(activity);
        if (!preference.get_Admob_Native_Id1().isEmpty()) {
            mNativeAdsId.add(preference.get_Admob_Native_Id1());
        }
        if (!preference.get_Admob_Native_Id2().isEmpty()) {
            mNativeAdsId.add(preference.get_Admob_Native_Id2());
        }
        if (!preference.get_Admob_Native_Id3().isEmpty()) {
            mNativeAdsId.add(preference.get_Admob_Native_Id3());
        }

        native_ads_count = mNativeAdsId.size();
        loadGNativeIntermediate(0);
    }

    public void loadGNativeIntermediate(int adCount) {
        if (adCount == 0) {
            mNativeAdsGHome = new ArrayList<>();
        }
        AdLoader.Builder builder;

        String adUnitId = mNativeAdsId.get(adCount);
        Log.e("Ads ", "NativeAd adUnitId:  " + adUnitId);
        Log.e("NativeAd", "adUnitId:" + adUnitId);
        if (adUnitId == null) {
            return;
        }
        if (TextUtils.isEmpty(adUnitId)) {
            return;
        }
        builder = new AdLoader.Builder(context, adUnitId);

        builder.forNativeAd(nativeAd -> {
            mNativeAdsGHome.add(nativeAd);
            int nextConunt = adCount + 1;
            if (nextConunt < native_ads_count) {
                Log.e("Ads ", "NativeAd nextConunt: " + nextConunt);
                loadGNativeIntermediate(nextConunt);
            }

            if (nextConunt == native_ads_count) {
                Log.e("Ads ", "NativeAd " + nextConunt + ":Last");
                Log.e("NativeAds: ", "last == ");

            }
        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .setStartMuted(true)
                .build();

        com.google.android.gms.ads.nativead.NativeAdOptions adOptions = new com.google.android.gms.ads.nativead.NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);
        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                Log.e("Ads ", "NativeAd onAdFailedToLoad: " + adError.getMessage());
            }
        }).build();

        if (preference.get_Ad_Flag().equals("admob")) {
            AdRequest.Builder builerRe = new AdRequest.Builder();
            adLoader.loadAd(builerRe.build());
        } else {
            AdManagerAdRequest.Builder builerRe = new AdManagerAdRequest.Builder();
            adLoader.loadAd(builerRe.build());
        }
    }

}
