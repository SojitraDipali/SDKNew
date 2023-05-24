package com.sdk.mynew;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.NativeAdViewAttributes;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.Random;


public class Native_Ads_Preload_1 {
    public static Context context;
    public static AppPreference preference;
    public static Native_Ads_Preload_1 mInstance;

    NativeAd nativeBannerAd1;


    public Native_Ads_Preload_1(Context activity) {
        context = activity;
        preference = new AppPreference(activity);

    }

    public static Native_Ads_Preload_1 getInstance(Context mContext) {
        context = mContext;
        preference = new AppPreference(mContext);
        if (mInstance == null) {
            mInstance = new Native_Ads_Preload_1(mContext);
        }
        return mInstance;
    }

    private static AdSize getAdSize(Context context) {
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
    }

    public void addNativeAd(FrameLayout viewGroup, boolean isList) {
        new Native_Ads_Static(context).Native_Ads(viewGroup);
        String type = isList ? new AppPreference(context).getNativeTypeList() : new AppPreference(context).getNativeTypeOther();
        switch (type) {
            case "banner":
                if (preference.getNativeflag().equalsIgnoreCase("on")) {
                    Native_Banner_Ads(viewGroup);
                } else {
                    viewGroup.setVisibility(View.GONE);
                }
                break;
            case "small":
                if (preference.getNativeflag().equalsIgnoreCase("on")) {
                    Native_Small_Ads(viewGroup);
                } else {
                    viewGroup.setVisibility(View.GONE);
                }
                break;
            case "medium":
                if (preference.getNativeflag().equalsIgnoreCase("on")) {
                    Native_Medium_Size(viewGroup);
                } else {
                    viewGroup.setVisibility(View.GONE);
                }
                break;
            case "large":
                if (preference.getNativeflag().equalsIgnoreCase("on")) {
                    Native_Large_Size(viewGroup);
                } else {
                    viewGroup.setVisibility(View.GONE);
                }
                break;
        }
    }

    private void inflateAd(Context context, com.facebook.ads.NativeAd nativeAd, FrameLayout viewGroup, int height) {

        AppPreference preference = new AppPreference(context);
        int textColor = Color.parseColor("#" + preference.getTextColor());
        int backColor = Color.parseColor("#" + preference.getBackColor());
        int btnColor = Color.parseColor("#" +  preference.getAdbtcolor());

        NativeAdViewAttributes viewAttributes = new NativeAdViewAttributes(context)
                .setBackgroundColor(backColor)
                .setTitleTextColor(textColor)
                .setDescriptionTextColor(textColor)
                .setButtonColor(btnColor)
                .setButtonTextColor(Color.WHITE);

        View adView = NativeAdView.render(context, nativeAd, viewAttributes);
        viewGroup.removeAllViews();
        viewGroup.addView(adView, new ViewGroup.LayoutParams(MATCH_PARENT, height));
    }


    public void Native_Banner_Ads(final FrameLayout viewGroup) {
        Object nativeAd = Native_Ads_Load.getNextNativeAd();
        if (nativeAd != null) {
            if (nativeAd instanceof NativeAd) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
                final TemplateView templateView = inflate.findViewById(R.id.my_template_small);
                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                templateView.setVisibility(View.GONE);
                NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
                templateView.setVisibility(View.VISIBLE);
                templateView.setStyles(build);
                templateView.setNativeAd((NativeAd) nativeAd);
                viewGroup.setVisibility(View.VISIBLE);
                viewGroup.removeAllViews();
                viewGroup.addView(inflate);
            }  else if (nativeAd instanceof com.facebook.ads.NativeAd){
                inflateAd(context, (com.facebook.ads.NativeAd) nativeAd, viewGroup, 300);
            }
        } else {
            Qureka_Predchamp_Native_Banner(viewGroup);
        }
    }

    public void Native_Small_Ads(final FrameLayout viewGroup) {
        Object nativeAd = Native_Ads_Load.getNextNativeAd();
        if (nativeAd != null) {
            if (nativeAd instanceof NativeAd) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp1, viewGroup, false);
                final TemplateView templateView = inflate.findViewById(R.id.my_template_small);
                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                templateView.setVisibility(View.GONE);
                NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
                templateView.setVisibility(View.VISIBLE);
                templateView.setStyles(build);
                templateView.setNativeAd((NativeAd) nativeAd);
                viewGroup.setVisibility(View.VISIBLE);
                viewGroup.removeAllViews();
                viewGroup.addView(inflate);
            }else if (nativeAd instanceof com.facebook.ads.NativeAd){
                inflateAd(context, (com.facebook.ads.NativeAd) nativeAd, viewGroup, 300);
            }
        } else {
            Qureka_Predchamp_Native_Banner(viewGroup);
        }
    }

    public void Native_Medium_Size(final FrameLayout viewGroup) {
        Object nativeAd = Native_Ads_Load.getNextNativeAd();
        if (nativeAd != null) {
            if (nativeAd instanceof NativeAd) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp1, viewGroup, false);
                final TemplateView templateView = inflate.findViewById(R.id.my_template_large);
                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                templateView.setVisibility(View.GONE);
                NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
                templateView.setVisibility(View.VISIBLE);
                templateView.setStyles(build);
                templateView.setNativeAd((NativeAd) nativeAd);
                viewGroup.removeAllViews();
                viewGroup.addView(inflate);
            } else if (nativeAd instanceof com.facebook.ads.NativeAd){
                inflateAd(context, (com.facebook.ads.NativeAd) nativeAd, viewGroup, 400);
            }
        }
        else {
            Qureka_Predchamp_Native(viewGroup);
        }
    }

    public void Native_Large_Size(final FrameLayout viewGroup) {
        Object nativeAd = Native_Ads_Load.getNextNativeAd();
        if (nativeAd != null) {
            if (nativeAd instanceof NativeAd) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
                final TemplateView templateView = inflate.findViewById(R.id.my_template_large);
                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                templateView.setVisibility(View.GONE);
                NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
                templateView.setVisibility(View.VISIBLE);
                templateView.setStyles(build);
                templateView.setNativeAd((NativeAd) nativeAd);
                viewGroup.removeAllViews();
                viewGroup.addView(inflate);
            } else if (nativeAd instanceof com.facebook.ads.NativeAd){
                inflateAd(context, (com.facebook.ads.NativeAd) nativeAd, viewGroup, 600);
            }
        } else {
            Qureka_Predchamp_Native(viewGroup);
        }
    }

    private void Qureka_Predchamp_Native(final ViewGroup BannerContainer) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            if (preference.get_Qureka_Flag().equalsIgnoreCase("qureka")) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_native_ads, null, false);
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                ((ImageView) view.findViewById(R.id.img_banner)).setImageResource(Constant.qureka_native[i1]);
                ((TextView) view.findViewById(R.id.tv_appname)).setText(Constant.qureka_header[i1]);
                ((TextView) view.findViewById(R.id.tv_desc)).setText(Constant.qureka_description[i1]);
                Glide.with(context).load(Constant.qureka_icon[i1]).into((ImageView) view.findViewById(R.id.img_logo));
                view.findViewById(R.id.btn_install).setOnClickListener(v -> Constant.Open_Qureka(context));
                BannerContainer.removeAllViews();
                BannerContainer.addView(view);
            } else if (preference.get_Qureka_Flag().equalsIgnoreCase("predchamp")) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_native_ads, null, false);
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                Glide.with(context).load(Constant.predchamp_icon[i1]).into((ImageView) view.findViewById(R.id.img_logo));
                ((TextView) view.findViewById(R.id.tv_appname)).setText(Constant.predchamp_header[i1]);
                ((TextView) view.findViewById(R.id.tv_desc)).setText(Constant.predchamp_description[i1]);
                ((ImageView) view.findViewById(R.id.img_banner)).setImageResource(Constant.predchamp_native[i1]);
                view.findViewById(R.id.btn_install).setOnClickListener(v -> Constant.Open_Qureka(context));
                BannerContainer.removeAllViews();
                BannerContainer.addView(view);
            } else {
                BannerContainer.setVisibility(View.GONE);
            }
        }
    }

    private void Qureka_Predchamp_Native_Banner(final ViewGroup BannerContainer) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {

            if (preference.get_Qureka_Flag().equalsIgnoreCase("qureka")) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_native_banner_ads, null, false);
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                ((TextView) view.findViewById(R.id.tv_appname)).setText(Constant.qureka_header[i1]);
                ((TextView) view.findViewById(R.id.tv_desc)).setText(Constant.qureka_description[i1]);
                Glide.with(context).load(Constant.qureka_icon[i1]).into((ImageView) view.findViewById(R.id.img_logo));
                view.findViewById(R.id.btn_install).setOnClickListener(v -> Constant.Open_Qureka(context));
                BannerContainer.setVisibility(View.VISIBLE);
                BannerContainer.removeAllViews();
                BannerContainer.addView(view);
            } else if (preference.get_Qureka_Flag().equalsIgnoreCase("predchamp")) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_native_banner_ads, null, false);
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                ((TextView) view.findViewById(R.id.tv_appname)).setText(Constant.predchamp_header[i1]);
                ((TextView) view.findViewById(R.id.tv_desc)).setText(Constant.predchamp_description[i1]);
                Glide.with(context).load(Constant.predchamp_icon[i1]).into((ImageView) view.findViewById(R.id.img_logo));
                view.findViewById(R.id.btn_install).setOnClickListener(v -> Constant.Open_Qureka(context));
                BannerContainer.setVisibility(View.VISIBLE);

                BannerContainer.removeAllViews();
                BannerContainer.addView(view);
            } else {
                BannerContainer.setVisibility(View.GONE);
            }
        }
    }

    private void Qureka_Predchamp_Adaptive(final ViewGroup BannerContainer) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            if (preference.get_Qureka_Flag().equalsIgnoreCase("qureka")) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_adaptive_ads, null, false);
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                ((ImageView) view.findViewById(R.id.img_banner)).setImageResource(Constant.qureka_banner[i1]);
                view.setOnClickListener(v -> Constant.Open_Qureka(context));
                BannerContainer.removeAllViews();
                BannerContainer.addView(view);
            } else if (preference.get_Qureka_Flag().equalsIgnoreCase("predchamp")) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_adaptive_ads, null, false);
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                ((ImageView) view.findViewById(R.id.img_banner)).setImageResource(Constant.predchamp_banner[i1]);
                view.setOnClickListener(v -> Constant.Open_Qureka(context));
                BannerContainer.removeAllViews();
                BannerContainer.addView(view);
            } else {
                BannerContainer.setVisibility(View.GONE);
            }
        }
    }
}
