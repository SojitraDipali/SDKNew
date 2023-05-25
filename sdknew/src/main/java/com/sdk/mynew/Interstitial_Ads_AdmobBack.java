package com.sdk.mynew;

import android.app.Activity;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class Interstitial_Ads_AdmobBack {
    public static InterstitialAd mInterstitialAd_admob;
    public static AdManagerInterstitialAd adManagerInterstitialAd;

    public static void ShowAd_Full(Activity source_class, Interstitial_Ads.AdCloseListener adCloseListener) {
        AppPreference appPreference = new AppPreference(source_class);
        if (appPreference.getFullflag().equalsIgnoreCase("on")) {
            if (Constant.Back_Counter % Integer.parseInt(appPreference.getBackclick()) == 0) {
                if (appPreference.getBackflag().equals("on")) {
                    AppPreference preference = new AppPreference(source_class);
                    Constant.Back_Counter++;
                    new Interstitial_Ads().callad(preference, source_class, adCloseListener);

//                    if (appPreference.get_Ad_Flag().equals("admob")) {
//                        ShowAd_FullAdmob(source_class, adCloseListener);
//                    } else {
//                        ShowAd_FullAdx(source_class, adCloseListener);
//                    }
                } else {
                    if (adCloseListener != null) {
                        adCloseListener.onAdClosed();
                    }
                }
            } else {
                Constant.Back_Counter++;
                if (adCloseListener != null) {
                    adCloseListener.onAdClosed();
                }
            }
        } else {
            if (adCloseListener != null) {
                adCloseListener.onAdClosed();
            }
        }
    }

    public static void ShowAd_FullAdmob(Activity source_class, Interstitial_Ads.AdCloseListener adCloseListener) {
        AppPreference preference = new AppPreference(source_class);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            Constant.Back_Counter++;
            final CustomProgressDialog customProgressDialog = new CustomProgressDialog(source_class, "Showing Ad...");
            customProgressDialog.setCancelable(false);
            customProgressDialog.show();
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
                    AppPreference.isFullScreenShow = false;
                    if (customProgressDialog.isShowing()) {
                        customProgressDialog.dismiss();
                    }
                    AppPreference.isFullScreenShow = false;
                    Interstitial_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(source_class, adCloseListener);
                    Constant.IS_TIME_INTERVAL = false;
                    new Handler().postDelayed(() -> Constant.IS_TIME_INTERVAL = true, Long.parseLong(String.valueOf(preference.get_Ad_Time_Interval())) * 1000);
                }
            });
        } else {
            if (adCloseListener != null) {
                adCloseListener.onAdClosed();
            }
        }
    }

    public static void ShowAd_FullAdx(Activity source_class, Interstitial_Ads.AdCloseListener adCloseListener) {
        AppPreference preference = new AppPreference(source_class);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            Constant.Back_Counter++;
            final CustomProgressDialog customProgressDialog = new CustomProgressDialog(source_class, "Showing Ad...");
            customProgressDialog.setCancelable(false);
            customProgressDialog.show();
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

                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();

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
                        public void onAdImpression() {
                            super.onAdImpression();
                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    adManagerInterstitialAd = null;
                    if (customProgressDialog.isShowing()) {
                        customProgressDialog.dismiss();
                    }
                    AppPreference.isFullScreenShow = false;
                    Interstitial_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(source_class, adCloseListener);
                    Constant.IS_TIME_INTERVAL = false;
                    new Handler().postDelayed(() -> Constant.IS_TIME_INTERVAL = true, Long.parseLong(String.valueOf(preference.get_Ad_Time_Interval())) * 1000);
                }
            });
        } else {
            if (adCloseListener != null) {
                adCloseListener.onAdClosed();
            }
        }
    }
}
