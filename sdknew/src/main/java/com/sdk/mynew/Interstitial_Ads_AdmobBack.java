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
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Arrays;
import java.util.List;

public class Interstitial_Ads_AdmobBack {
    public static InterstitialAd mInterstitialAd_admob;
    public static AdManagerInterstitialAd adManagerInterstitialAd;

    public static void ShowAd_Full(Activity source_class, Interstitial_Ads.AdCloseListener adCloseListener) {
        AppPreference appPreference = new AppPreference(source_class);
        if (appPreference.getFullflag().equalsIgnoreCase("on")) {
            if (appPreference.getBackflag().equalsIgnoreCase("on")) {
                if (Constant.Back_Counter % Integer.parseInt(appPreference.getBackclick()) == 0) {
                    if (appPreference.getBackclickadstyle().equalsIgnoreCase("admob") || appPreference.getBackclickadstyle().equalsIgnoreCase("normal")) {
                        ShowAd_FullAdmob(source_class, adCloseListener);
                    } else if (appPreference.getBackclickadstyle().equalsIgnoreCase("adx")) {
                        ShowAd_FullAdx(source_class, adCloseListener);
                    } else if (appPreference.getBackclickadstyle().equalsIgnoreCase("fb")) {
                        ShowAd_Facebook(source_class, adCloseListener);
                    } else if (appPreference.getBackclickadstyle().equalsIgnoreCase("ALT")) {
                        if (Constant.Alt_Back_Cnt_Inter == 2) {
                            Constant.Alt_Back_Cnt_Inter = 1;
                            ShowAd_FullAdmob(source_class, adCloseListener);
                        } else {
                            Constant.Alt_Back_Cnt_Inter++;
                            ShowAd_Facebook(source_class, adCloseListener);
                        }
                    } else if (appPreference.getBackclickadstyle().equalsIgnoreCase("multiple")) {
                        ShowAd_Multiple(source_class, adCloseListener);
                    }
                } else {
                    if (adCloseListener != null) {
                        adCloseListener.onAdClosed();
                    }
                }
            } else {
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

    public static void ShowAd_Multiple(Activity source_class, Interstitial_Ads.AdCloseListener adCloseListener) {
        final List<String> adUnitIds = Arrays.asList(new AppPreference(source_class).get_Admob_Interstitial_Id1(),
                new AppPreference(source_class).get_Admob_Interstitial_Id2(),
                new AppPreference(source_class).get_Admob_Interstitial_Id3());

        AppPreference preference = new AppPreference(source_class);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            Constant.Back_Counter++;
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
                            if (!CheckInstallActivity.checkIsOrganic(source_class)) {
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
                    AppPreference.isFullScreenShow = false;
                    Interstitial_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(source_class, adCloseListener);
                    if (!CheckInstallActivity.checkIsOrganic(source_class)) {
                        Constant.Open_Url_View(source_class);
                    }
                }
            });
        } else {
            if (adCloseListener != null) {
                adCloseListener.onAdClosed();
            }
        }
    }

    public static void ShowAd_Facebook(Activity source_class, Interstitial_Ads.AdCloseListener adCloseListener) {
        AppPreference preference = new AppPreference(source_class);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            Constant.Back_Counter++;
            final CustomProgressDialog customProgressDialog = new CustomProgressDialog(source_class, "Showing Ad...");
            customProgressDialog.setCancelable(false);
            customProgressDialog.show();
            com.facebook.ads.InterstitialAd fb_interstitial = new com.facebook.ads.InterstitialAd(source_class, preference.get_Facebook_Interstitial());
            fb_interstitial.loadAd(
                    fb_interstitial.buildLoadAdConfig()
                            .withAdListener(new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {
                                    isFullScreenShow = true;
                                    if (!CheckInstallActivity.checkIsOrganic(source_class)) {
                                        Constant.Open_Url_View(source_class);
                                    }
                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
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
                                public void onError(Ad ad, AdError adError) {
                                    isFullScreenShow = false;
                                    Log.e("TAG", "onError: " + adError.getErrorCode());
                                    if (customProgressDialog.isShowing()) {
                                        customProgressDialog.dismiss();
                                    }
                                    AppPreference.isFullScreenShow = false;
                                    Interstitial_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(source_class, adCloseListener);
                                    if (!CheckInstallActivity.checkIsOrganic(source_class)) {
                                        Constant.Open_Url_View(source_class);
                                    }
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
                            if (!CheckInstallActivity.checkIsOrganic(source_class)) {
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
                    AppPreference.isFullScreenShow = false;
                    if (customProgressDialog.isShowing()) {
                        customProgressDialog.dismiss();
                    }
                    AppPreference.isFullScreenShow = false;
                    Interstitial_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(source_class, adCloseListener);
                    if (!CheckInstallActivity.checkIsOrganic(source_class)) {
                        Constant.Open_Url_View(source_class);
                    }
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
                            if (!CheckInstallActivity.checkIsOrganic(source_class)) {
                                Constant.Open_Url_View(source_class);
                            }
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
                    if (!CheckInstallActivity.checkIsOrganic(source_class)) {
                        Constant.Open_Url_View(source_class);
                    }
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
