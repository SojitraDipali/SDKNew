package com.sdk.mynew;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class Interstitial_Ads_Fb_Admob {

    public static InterstitialAd mInterstitialAd_admob;
    public static AdManagerInterstitialAd adManagerInterstitialAd;

    public static void ShowAd_FullFb(Activity source_class, Interstitial_Ads.AdCloseListener adCloseListener, String oraganictype) {
        AppPreference preference = new AppPreference(source_class);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            final CustomProgressDialog customProgressDialog = new CustomProgressDialog(source_class, "Showing Ad...");
            customProgressDialog.setCancelable(false);
            customProgressDialog.show();
            com.facebook.ads.InterstitialAd fb_interstitial = new com.facebook.ads.InterstitialAd(source_class, preference.get_Facebook_Interstitial());
            fb_interstitial.loadAd(
                    fb_interstitial.buildLoadAdConfig()
                            .withAdListener(new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {
                                    AppPreference.isFullScreenShow = true;
                                    if (oraganictype.equalsIgnoreCase("noorganic")) {
                                        Constant.Open_Url_View(source_class);
                                    }
                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    AppPreference.isFullScreenShow = false;
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
                                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                                    AppPreference.isFullScreenShow = false;
                                    Log.e("TAG", "onError: " + adError.getErrorCode());
                                    ShowAd_Full(source_class, adCloseListener, customProgressDialog, oraganictype);
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
        } else {
            if (adCloseListener != null) {
                adCloseListener.onAdClosed();
            }
        }
    }

    public static void ShowAd_Full(Activity source_class, Interstitial_Ads.AdCloseListener adCloseListener, CustomProgressDialog customProgressDialog, String oraganictype) {
        AppPreference appPreference = new AppPreference(source_class);
        if (appPreference.get_Ad_Flag().equals("admob")) {
            ShowAd_FullAdmob(source_class, adCloseListener, customProgressDialog, oraganictype);
        } else {
            ShowAd_FullAdx(source_class, adCloseListener, customProgressDialog, oraganictype);
        }
    }

    public static void ShowAd_FullAdmob(Activity source_class, Interstitial_Ads.AdCloseListener adCloseListener, CustomProgressDialog customProgressDialog, String oraganictype) {
        AppPreference preference = new AppPreference(source_class);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(source_class, new AppPreference(source_class).get_Admob_Interstitial_Id1(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    mInterstitialAd_admob = interstitialAd;
                    mInterstitialAd_admob.show(source_class);
                    mInterstitialAd_admob.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            mInterstitialAd_admob = null;
                            AppPreference.isFullScreenShow = false;
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
                            AppPreference.isFullScreenShow = true;
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
                            if (adCloseListener != null) {
                                adCloseListener.onAdClosed();
                            }
                            AppPreference.isFullScreenShow = false;

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
                    adFailedToLoadG(source_class, adCloseListener, customProgressDialog, preference, oraganictype);
                }
            });
        } else {
            if (adCloseListener != null) {
                adCloseListener.onAdClosed();
            }
        }
    }

    private static void adFailedToLoadG(Activity source_class, Interstitial_Ads.AdCloseListener adCloseListener, CustomProgressDialog customProgressDialog, AppPreference preference, String oraganictype) {
        AppPreference.isFullScreenShow = false;
        if (customProgressDialog.isShowing()) {
            customProgressDialog.dismiss();
        }
        AppPreference.isFullScreenShow = false;
        Interstitial_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(source_class, adCloseListener);
        if (oraganictype.equalsIgnoreCase("noorganic")) {
            Constant.Open_Url_View(source_class);
        }
        Constant.IS_TIME_INTERVAL = false;
        new Handler().postDelayed(() -> Constant.IS_TIME_INTERVAL = true, Long.parseLong(String.valueOf(preference.get_Ad_Time_Interval())) * 1000);
    }

    public static void ShowAd_FullAdx(Activity source_class, Interstitial_Ads.AdCloseListener adCloseListener, CustomProgressDialog customProgressDialog, String oraganictype) {
        AppPreference preference = new AppPreference(source_class);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
            AdManagerInterstitialAd.load(source_class, new AppPreference(source_class).get_Admob_Interstitial_Id1(), adRequest, new AdManagerInterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                    adManagerInterstitialAd = interstitialAd;
                    adManagerInterstitialAd.show(source_class);
                    adManagerInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            adManagerInterstitialAd = null;
                            if (customProgressDialog.isShowing()) {
                                customProgressDialog.dismiss();
                            }
                            if (adCloseListener != null) {
                                adCloseListener.onAdClosed();
                            }
                            AppPreference.isFullScreenShow = false;
                            Constant.IS_TIME_INTERVAL = false;
                            new Handler().postDelayed(() -> Constant.IS_TIME_INTERVAL = true, Long.parseLong(String.valueOf(preference.get_Ad_Time_Interval())) * 1000);
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            super.onAdShowedFullScreenContent();
                            adManagerInterstitialAd = null;
                            AppPreference.isFullScreenShow = true;
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
                            AppPreference.isFullScreenShow = false;

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
                    adManagerInterstitialAd = null;
                    AppPreference.isFullScreenShow = false;
                    adFailedToLoadG(source_class, adCloseListener, customProgressDialog, preference, oraganictype);
                }
            });
        } else {
            if (adCloseListener != null) {
                adCloseListener.onAdClosed();
            }
        }
    }

}
