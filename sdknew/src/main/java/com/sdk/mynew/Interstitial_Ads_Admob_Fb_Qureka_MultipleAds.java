package com.sdk.mynew;

import static com.sdk.mynew.AppPreference.isFullScreenShow;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Arrays;
import java.util.List;

public class Interstitial_Ads_Admob_Fb_Qureka_MultipleAds {
    public static InterstitialAd mInterstitialAd_admob;

    public static void ShowAd_Full(Activity source_class, Interstitial_Ads.AdCloseListener adCloseListener, String oraganictype) {
        final List<String> adUnitIds = Arrays.asList(new AppPreference(source_class).get_Admob_Interstitial_Id1(),
                new AppPreference(source_class).get_Admob_Interstitial_Id2(),
                new AppPreference(source_class).get_Admob_Interstitial_Id3());

        AppPreference preference = new AppPreference(source_class);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            if (Constant.FullAdCounter > 2) {
                Constant.FullAdCounter = 0;
            }
            final CustomProgressDialog customProgressDialog = new CustomProgressDialog(source_class, "Showing Ad...");
            customProgressDialog.setCancelable(false);
            customProgressDialog.show();
            AdRequest adRequest = new AdRequest.Builder().build();
            Log.e("admob id", String.valueOf(Constant.FullAdCounter));
            InterstitialAd.load(source_class, adUnitIds.get(Constant.FullAdCounter), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    mInterstitialAd_admob = interstitialAd;
                    mInterstitialAd_admob.show(source_class);
                    Constant.FullAdCounter++;
                    Log.e("inside load ad", String.valueOf(Constant.FullAdCounter));
                    mInterstitialAd_admob.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            mInterstitialAd_admob = null;
                            isFullScreenShow = false;
                            if (customProgressDialog.isShowing()) {
                                customProgressDialog.dismiss();
                            }
                            if (adCloseListener != null) {
                                adCloseListener.onAdClosed();
                            }
                            Constant.IS_TIME_INTERVAL = false;
                            new Handler().postDelayed(() -> Constant.IS_TIME_INTERVAL = true, Long.parseLong(String.valueOf(preference.get_Ad_Time_Interval())) * 1000);
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            super.onAdShowedFullScreenContent();
                            mInterstitialAd_admob = null;
                            isFullScreenShow = true;
                            if (oraganictype.equalsIgnoreCase("noorganic")) {
                                Constant.Open_Url_View(source_class);
                            }
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            if (customProgressDialog.isShowing()) {
                                customProgressDialog.dismiss();
                            }
                            isFullScreenShow = false;
                            if (adCloseListener != null) {
                                adCloseListener.onAdClosed();
                            }
                            Constant.IS_TIME_INTERVAL = false;
                            new Handler().postDelayed(() -> Constant.IS_TIME_INTERVAL = true, Long.parseLong(String.valueOf(preference.get_Ad_Time_Interval())) * 1000);
                        }

                        @Override
                        public void onAdImpression() {
                            super.onAdImpression();
                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    mInterstitialAd_admob = null;
                    if (customProgressDialog.isShowing()) {
                        customProgressDialog.dismiss();
                    }
                    isFullScreenShow = false;

                    com.facebook.ads.InterstitialAd fb_interstitial = new com.facebook.ads.InterstitialAd(source_class, preference.get_Facebook_Interstitial());
                    fb_interstitial.loadAd(
                            fb_interstitial.buildLoadAdConfig()
                                    .withAdListener(new InterstitialAdListener() {
                                        @Override
                                        public void onInterstitialDisplayed(Ad ad) {
                                            isFullScreenShow = true;
                                            if (oraganictype.equalsIgnoreCase("noorganic")) {
                                                Constant.Open_Url_View(source_class);
                                            }
                                        }

                                        @Override
                                        public void onInterstitialDismissed(Ad ad) {
                                            if (customProgressDialog.isShowing()) {
                                                customProgressDialog.dismiss();
                                            }
                                            isFullScreenShow = false;

                                            if (adCloseListener != null) {
                                                adCloseListener.onAdClosed();
                                            }
                                            Constant.IS_TIME_INTERVAL = false;
                                            new Handler().postDelayed(() -> Constant.IS_TIME_INTERVAL = true, Long.parseLong(String.valueOf(preference.get_Ad_Time_Interval())) * 1000);
                                        }

                                        @Override
                                        public void onError(Ad ad, AdError adError) {
                                            Log.e("TAG", "onError: " + adError.getErrorCode());
                                            if (customProgressDialog.isShowing()) {
                                                customProgressDialog.dismiss();
                                            }
                                            isFullScreenShow = false;
                                            Interstitial_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(source_class, adCloseListener);
                                            if (oraganictype.equalsIgnoreCase("noorganic")) {
                                                Constant.Open_Url_View(source_class);
                                            }
                                            Constant.IS_TIME_INTERVAL = false;
                                            new Handler().postDelayed(() -> Constant.IS_TIME_INTERVAL = true, Long.parseLong(String.valueOf(preference.get_Ad_Time_Interval())) * 1000);
                                        }

                                        @Override
                                        public void onAdLoaded(Ad ad) {
                                            if (!fb_interstitial.isAdLoaded()) {
                                                return;
                                            }
                                            if (fb_interstitial.isAdInvalidated()) {
                                                return;
                                            }
                                            fb_interstitial.show();
                                        }

                                        @Override
                                        public void onAdClicked(Ad ad) {

                                        }

                                        @Override
                                        public void onLoggingImpression(Ad ad) {

                                        }
                                    })
                                    .build());
                }
            });
        } else {
            if (adCloseListener != null) {
                adCloseListener.onAdClosed();
            }
        }
    }
}
