package com.sdk.mynew;

import android.app.Activity;

public class Interstitial_Ads {

    public static AdCloseListener adCloseListener;

    public void Show_Ads(Activity source_class, AdCloseListener adCloseListener) {
        AppPreference preference = new AppPreference(source_class);

        if (!preference.get_Click_Flag().equalsIgnoreCase("on")) {
            if (Constant.IS_TIME_INTERVAL) {
                if (preference.get_Adstyle().equalsIgnoreCase("Normal")) {
                    Interstitial_Ads_Admob_Fb.ShowAd_Full(source_class, adCloseListener);
                } else if (preference.get_Adstyle().equalsIgnoreCase("ALT")) {
                    if (Constant.Alt_Cnt_Inter == 2) {
                        Constant.Alt_Cnt_Inter = 1;
                        Interstitial_Ads_Admob_Fb.ShowAd_Full(source_class, adCloseListener);
                    } else {
                        Constant.Alt_Cnt_Inter++;
                        Interstitial_Ads_Fb_Admob.ShowAd_FullFb(source_class, adCloseListener);
                    }
                } else if (preference.get_Adstyle().equalsIgnoreCase("fb")) {
                    Interstitial_Ads_Facebook.ShowAd_Facebook(source_class, adCloseListener);
                } else if (preference.get_Adstyle().equalsIgnoreCase("multiple")) {
                    Interstitial_Ads_Admob_Fb_Qureka_MultipleAds.ShowAd_Full(source_class, adCloseListener);
                }
            } else {
                if (adCloseListener != null) {
                    adCloseListener.onAdClosed();
                }
            }
        } else {
            if (Constant.Front_Counter % Integer.parseInt(preference.get_Click_Count()) == 0) {
                Constant.Adval = "0";
                if (preference.get_Adstyle().equalsIgnoreCase("Normal")) {
                    Interstitial_Ads_Admob_Fb.ShowAd_Full(source_class, adCloseListener);
                } else if (preference.get_Adstyle().equalsIgnoreCase("ALT")) {
                    if (Constant.Alt_Cnt_Inter == 2) {
                        Constant.Alt_Cnt_Inter = 1;
                        Interstitial_Ads_Admob_Fb.ShowAd_Full(source_class, adCloseListener);
                    } else {
                        Constant.Alt_Cnt_Inter++;
                        Interstitial_Ads_Fb_Admob.ShowAd_FullFb(source_class, adCloseListener);
                    }
                } else if (preference.get_Adstyle().equalsIgnoreCase("fb")) {
                    Interstitial_Ads_Facebook.ShowAd_Facebook(source_class, adCloseListener);
                } else if (preference.get_Adstyle().equalsIgnoreCase("multiple")) {
                    Interstitial_Ads_Admob_Fb_Qureka_MultipleAds.ShowAd_Full(source_class, adCloseListener);
                }
            } else {
                Constant.Front_Counter++;
                Constant.Adval = "1";
                if (adCloseListener != null) {
                    adCloseListener.onAdClosed();
                }
            }

        }
    }

    public interface AdCloseListener {
        void onAdClosed();
    }
}
