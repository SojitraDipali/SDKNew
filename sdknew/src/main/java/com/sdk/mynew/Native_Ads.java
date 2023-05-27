package com.sdk.mynew;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Native_Ads {
    public Context context;
    AppPreference preference;

    public Native_Ads(Context context) {
        this.context = context;
        preference = new AppPreference(this.context);
    }

//    public void addNativeAd(ViewGroup viewGroup, boolean isList) {
//        String type = isList ? new AppPreference(context).getNativeTypeList() : new AppPreference(context).getNativeTypeOther();
//        switch (type) {
//            case "banner":
//                Native_Banner_Ads(viewGroup);
//                break;
//
//            case "small":
//                Native_Banner_Ads1(viewGroup);
//                break;
//
//            case "medium":
//                Native_Ad1(viewGroup);
//                break;
//
//            case "large":
//                Native_Ad(viewGroup);
//                break;
//        }
//    }

//    public void Native_Ad(final ViewGroup viewGroup) {
//        if (preference.getNativeflag().equalsIgnoreCase("on")) {
//            new Native_Ads_Static(context).Native_Ads(viewGroup);
//            if (preference.get_Adstyle().equalsIgnoreCase("Normal")) {
//                Admob_Native_Ads(viewGroup);
//            } else if (preference.get_Adstyle().equalsIgnoreCase("ALT")) {
//                if (Constant.Alt_Cnt_Native == 2) {
//                    Admob_Native_Ads(viewGroup);
//                    Constant.Alt_Cnt_Native = 1;
//                } else {
//                    Facebook_Native_Ads(viewGroup);
//                    Constant.Alt_Cnt_Native++;
//                }
//            } else if (preference.get_Adstyle().equalsIgnoreCase("multiple")) {
//                Admob_FB_Multiple_Native_Ads(viewGroup);
//            } else if (preference.get_Adstyle().equalsIgnoreCase("fb")) {
//                Facebook_Native_Ads(viewGroup);
//            }
//        } else {
//            viewGroup.setVisibility(View.GONE);
//        }
//    }
//
//    public void Native_Ad1(final ViewGroup viewGroup) {
//        if (preference.getNativeflag().equalsIgnoreCase("on")) {
//            new Native_Ads_Static(context).Native_Ads1(viewGroup);
//            if (preference.get_Adstyle().equalsIgnoreCase("Normal")) {
//                Admob_Native_Ads1(viewGroup);
//            } else if (preference.get_Adstyle().equalsIgnoreCase("ALT")) {
//                if (Constant.Alt_Cnt_Native == 2) {
//                    Admob_Native_Ads1(viewGroup);
//                    Constant.Alt_Cnt_Native = 1;
//                } else {
//                    Facebook_Native_Ads1(viewGroup);
//                    Constant.Alt_Cnt_Native++;
//                }
//            } else if (preference.get_Adstyle().equalsIgnoreCase("multiple")) {
//                Admob_FB_Multiple_Native_Ads1(viewGroup);
//            } else if (preference.get_Adstyle().equalsIgnoreCase("fb")) {
//                Facebook_Native_Ads1(viewGroup);
//            }
//        } else {
//            viewGroup.setVisibility(View.GONE);
//        }
//    }
//
//    public void Native_Banner_Ads(final ViewGroup viewGroup) {
//        if (preference.getNativeflag().equalsIgnoreCase("on")) {
//            new Native_Ads_Static(context).Native_Banner_Ads(viewGroup);
//            Log.d(TAG, "Native_Banner_Ads: " + preference.get_Adstyle());
//            if (preference.get_Adstyle().equalsIgnoreCase("Normal")) {
//                Admob_Native_Banner_Ads(viewGroup);
//            } else if (preference.get_Adstyle().equalsIgnoreCase("ALT")) {
//                if (Constant.Alt_Cnt_Native_Banner == 2) {
//                    Admob_Native_Banner_Ads(viewGroup);
//                    Constant.Alt_Cnt_Native_Banner = 1;
//                } else {
//                    Facebook_Native_Banner_Ads(viewGroup);
//                    Constant.Alt_Cnt_Native_Banner++;
//                }
//            } else if (preference.get_Adstyle().equalsIgnoreCase("multiple")) {
//                Admob_FB_Multiple_Native_Banner_Ads(viewGroup);
//            } else if (preference.get_Adstyle().equalsIgnoreCase("fb")) {
//                Facebook_Native_Banner_Ads(viewGroup);
//            }
//        } else {
//            viewGroup.setVisibility(View.GONE);
//        }
//    }
//
//    public void Native_Banner_Ads1(final ViewGroup viewGroup) {
//        if (preference.getNativeflag().equalsIgnoreCase("on")) {
//            new Native_Ads_Static(context).Native_Banner_Ads1(viewGroup);
//            if (preference.get_Adstyle().equalsIgnoreCase("Normal")) {
//                Admob_Native_Banner_Ads1(viewGroup);
//            } else if (preference.get_Adstyle().equalsIgnoreCase("ALT")) {
//                if (Constant.Alt_Cnt_Native_Banner == 2) {
//                    Admob_Native_Banner_Ads1(viewGroup);
//                    Constant.Alt_Cnt_Native_Banner = 1;
//                } else {
//                    Facebook_Native_Banner_Ads1(viewGroup);
//                    Constant.Alt_Cnt_Native_Banner++;
//                }
//            } else if (preference.get_Adstyle().equalsIgnoreCase("multiple")) {
//                Admob_FB_Multiple_Native_Banner_Ads1(viewGroup);
//            } else if (preference.get_Adstyle().equalsIgnoreCase("fb")) {
//                Facebook_Native_Banner_Ads1(viewGroup);
//            }
//        } else {
//            viewGroup.setVisibility(View.GONE);
//        }
//    }

    public void Adaptive_Banner(final ViewGroup viewGroup) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            if (preference.getBannerflag().equalsIgnoreCase("on")) {
                new Native_Ads_Static(context).Adaptive_Banner(viewGroup);
                if (preference.getAdstyleBanner().equalsIgnoreCase("Normal")) {
                    Admob_Adaptive_Banner(viewGroup);
                } else if (preference.getAdstyleBanner().equalsIgnoreCase("ALT")) {
                    if (Constant.Alt_Cnt_Banner == 2) {
                        Admob_Adaptive_Banner(viewGroup);
                        Constant.Alt_Cnt_Banner = 1;
                    } else {
                        Facebook_Adaptive_Banner(viewGroup);
                        Constant.Alt_Cnt_Banner++;
                    }
                } else if (preference.getAdstyleBanner().equalsIgnoreCase("multiple")) {
                    Admob_FB_Multiple_Adaptive_Banner(viewGroup);
                } else if (preference.getAdstyleBanner().equalsIgnoreCase("fb")) {
                    Facebook_Adaptive_Banner(viewGroup);
                }
            }
        }
    }

//    private void Admob_Native_Ads(final ViewGroup viewGroup) {
//        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
//            try {
//                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
//                final TemplateView templateView = inflate.findViewById(R.id.my_template_large);
//                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
//                templateView.setVisibility(View.GONE);
//                AdLoader.Builder builder = new AdLoader.Builder(context, preference.get_Admob_Native_Id1());
//                builder.forNativeAd(nativeAd -> {
//                    NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
//                    templateView.setVisibility(View.VISIBLE);
//                    templateView.setStyles(build);
//                    templateView.setNativeAd(nativeAd);
//                    viewGroup.removeAllViews();
//                    viewGroup.addView(inflate);
//                });
//
//                builder.withAdListener(new AdListener() {
//                    @Override
//                    public void onAdClosed() {
//                        super.onAdClosed();
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        Log.e("TAG", "Admob Fail -> onAdFailedToLoad: Native" + loadAdError.getMessage());
//                        super.onAdFailedToLoad(loadAdError);
//                        templateView.setVisibility(View.GONE);
//                        Qureka_Predchamp_Native(viewGroup);
//                    }
//
//                    @Override
//                    public void onAdOpened() {
//                        super.onAdOpened();
//                    }
//
//                    @Override
//                    public void onAdLoaded() {
//                        super.onAdLoaded();
//                        Log.e("TAG", "Admob Load -> onNativeAdLoaded: Native");
//                    }
//                }).build().loadAd(new AdRequest.Builder().build());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void Admob_Native_Ads1(final ViewGroup viewGroup) {
//        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
//            try {
//                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp1, viewGroup, false);
//                final TemplateView templateView = inflate.findViewById(R.id.my_template_large);
//                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
//                templateView.setVisibility(View.GONE);
//                AdLoader.Builder builder = new AdLoader.Builder(context, preference.get_Admob_Native_Id1());
//                builder.forNativeAd(nativeAd -> {
//                    NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
//                    templateView.setVisibility(View.VISIBLE);
//                    templateView.setStyles(build);
//                    templateView.setNativeAd(nativeAd);
//                    viewGroup.removeAllViews();
//                    viewGroup.addView(inflate);
//                });
//
//                builder.withAdListener(new AdListener() {
//                    @Override
//                    public void onAdClosed() {
//                        super.onAdClosed();
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        Log.e("TAG", "Admob Fail -> onAdFailedToLoad: Native" + loadAdError.getMessage());
//                        super.onAdFailedToLoad(loadAdError);
//                        templateView.setVisibility(View.GONE);
//                        Qureka_Predchamp_Native(viewGroup);
//                    }
//
//                    @Override
//                    public void onAdOpened() {
//                        super.onAdOpened();
//                    }
//
//                    @Override
//                    public void onAdLoaded() {
//                        super.onAdLoaded();
//                        Log.e("TAG", "Admob Load -> onNativeAdLoaded: Native");
//                    }
//                }).build().loadAd(new AdRequest.Builder().build());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void Admob_FB_Multiple_Native_Ads(final ViewGroup viewGroup) {
//        final List<String> adUnitIds = Arrays.asList(new AppPreference(context).get_Admob_Native_Id1(),
//                new AppPreference(context).get_Admob_Native_Id2(),
//                new AppPreference(context).get_Admob_Native_Id3());
//        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
//            try {
//                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
//                final TemplateView templateView = inflate.findViewById(R.id.my_template_large);
//                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
//                if (Constant.NAtiveAdCounter > 2) {
//                    Constant.NAtiveAdCounter = 0;
//                }
//                templateView.setVisibility(View.GONE);
//                AdLoader.Builder builder = new AdLoader.Builder(context, adUnitIds.get(Constant.NAtiveAdCounter));
//                Log.e("NAtiveAdCounter out", String.valueOf(Constant.NAtiveAdCounter));
//
//                builder.forNativeAd(nativeAd -> {
//                    Constant.NAtiveAdCounter++;
//                    Log.e("NAtiveAdCounter", String.valueOf(Constant.NAtiveAdCounter));
//                    NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
//                    templateView.setVisibility(View.VISIBLE);
//                    templateView.setStyles(build);
//                    templateView.setNativeAd(nativeAd);
//                    viewGroup.removeAllViews();
//                    viewGroup.addView(inflate);
//                });
//
//                builder.withAdListener(new AdListener() {
//                    @Override
//                    public void onAdClosed() {
//                        super.onAdClosed();
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        Log.e("TAG", "Admob Fail -> onAdFailedToLoad: Native" + loadAdError.getMessage());
//                        super.onAdFailedToLoad(loadAdError);
//                        templateView.setVisibility(View.GONE);
//                        final NativeAd nativeAd = new NativeAd(context, preference.get_Facebook_Native());
//                        NativeAdListener nativeAdListener = new NativeAdListener() {
//                            @Override
//                            public void onMediaDownloaded(Ad ad) {
//                            }
//
//                            @Override
//                            public void onError(Ad ad, AdError adError) {
//                                Log.e("TAG", " Facebook Native_Ads  onAdFailedToLoad  " + adError.getErrorCode());
//                                templateView.setVisibility(View.GONE);
//                                Qureka_Predchamp_Native(viewGroup);
//                            }
//
//                            @Override
//                            public void onAdLoaded(Ad ad) {
//                                View adView = NativeAdView.render(context, nativeAd, NativeAdView.Type.HEIGHT_300);
//                                viewGroup.removeAllViews();
//                                viewGroup.addView(adView);
//                                viewGroup.setVisibility(View.VISIBLE);
//                            }
//
//                            @Override
//                            public void onAdClicked(Ad ad) {
//                            }
//
//                            @Override
//                            public void onLoggingImpression(Ad ad) {
//                            }
//                        };
//                        nativeAd.loadAd(
//                                nativeAd.buildLoadAdConfig()
//                                        .withAdListener(nativeAdListener)
//                                        .build());
//                    }
//
//                    @Override
//                    public void onAdOpened() {
//                        super.onAdOpened();
//                    }
//
//                    @Override
//                    public void onAdLoaded() {
//                        super.onAdLoaded();
//                        Log.e("TAG", "Admob Load -> onNativeAdLoaded: Native");
//                    }
//                }).build().loadAd(new AdRequest.Builder().build());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void Admob_FB_Multiple_Native_Ads1(final ViewGroup viewGroup) {
//        final List<String> adUnitIds = Arrays.asList(new AppPreference(context).get_Admob_Native_Id1(),
//                new AppPreference(context).get_Admob_Native_Id2(),
//                new AppPreference(context).get_Admob_Native_Id3());
//        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
//            try {
//                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp1, viewGroup, false);
//                final TemplateView templateView = inflate.findViewById(R.id.my_template_large);
//                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
//                if (Constant.NAtiveAdCounter > 2) {
//                    Constant.NAtiveAdCounter = 0;
//                }
//                templateView.setVisibility(View.GONE);
//                AdLoader.Builder builder = new AdLoader.Builder(context, adUnitIds.get(Constant.NAtiveAdCounter));
//                Log.e("NAtiveAdCounter out", String.valueOf(Constant.NAtiveAdCounter));
//
//                builder.forNativeAd(nativeAd -> {
//                    Constant.NAtiveAdCounter++;
//                    Log.e("NAtiveAdCounter", String.valueOf(Constant.NAtiveAdCounter));
//                    NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
//                    templateView.setVisibility(View.VISIBLE);
//                    templateView.setStyles(build);
//                    templateView.setNativeAd(nativeAd);
//                    viewGroup.removeAllViews();
//                    viewGroup.addView(inflate);
//                });
//
//                builder.withAdListener(new AdListener() {
//                    @Override
//                    public void onAdClosed() {
//                        super.onAdClosed();
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        Log.e("TAG", "Admob Fail -> onAdFailedToLoad: Native" + loadAdError.getMessage());
//                        super.onAdFailedToLoad(loadAdError);
//                        templateView.setVisibility(View.GONE);
//                        final NativeAd nativeAd = new NativeAd(context, preference.get_Facebook_Native());
//                        NativeAdListener nativeAdListener = new NativeAdListener() {
//                            @Override
//                            public void onMediaDownloaded(Ad ad) {
//                            }
//
//                            @Override
//                            public void onError(Ad ad, AdError adError) {
//                                Log.e("TAG", " Facebook Native_Ads  onAdFailedToLoad  " + adError.getErrorCode());
//                                templateView.setVisibility(View.GONE);
//                                Qureka_Predchamp_Native(viewGroup);
//                            }
//
//                            @Override
//                            public void onAdLoaded(Ad ad) {
//                                View adView = NativeAdView.render(context, nativeAd, NativeAdView.Type.HEIGHT_300);
//                                viewGroup.removeAllViews();
//                                viewGroup.addView(adView);
//                                viewGroup.setVisibility(View.VISIBLE);
//                            }
//
//                            @Override
//                            public void onAdClicked(Ad ad) {
//                            }
//
//                            @Override
//                            public void onLoggingImpression(Ad ad) {
//                            }
//                        };
//                        nativeAd.loadAd(
//                                nativeAd.buildLoadAdConfig()
//                                        .withAdListener(nativeAdListener)
//                                        .build());
//                    }
//
//                    @Override
//                    public void onAdOpened() {
//                        super.onAdOpened();
//                    }
//
//                    @Override
//                    public void onAdLoaded() {
//                        super.onAdLoaded();
//                        Log.e("TAG", "Admob Load -> onNativeAdLoaded: Native");
//                    }
//                }).build().loadAd(new AdRequest.Builder().build());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void Facebook_Native_Ads(final ViewGroup viewGroup) {
//        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
//            try {
//                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
//                final TemplateView templateView = inflate.findViewById(R.id.my_template_large);
//                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
//                templateView.setVisibility(View.GONE);
//                final NativeAd nativeAd = new NativeAd(context, preference.get_Facebook_Native());
//                NativeAdListener nativeAdListener = new NativeAdListener() {
//                    @Override
//                    public void onMediaDownloaded(Ad ad) {
//                    }
//
//                    @Override
//                    public void onError(Ad ad, AdError adError) {
//                        Log.e("TAG", " Facebook Native_Ads  onAdFailedToLoad  " + adError.getErrorCode());
//                        templateView.setVisibility(View.GONE);
//                        Qureka_Predchamp_Native(viewGroup);
//                    }
//
//                    @Override
//                    public void onAdLoaded(Ad ad) {
//                        View adView = NativeAdView.render(context, nativeAd, NativeAdView.Type.HEIGHT_300);
//                        viewGroup.removeAllViews();
//                        viewGroup.addView(adView);
//                        viewGroup.setVisibility(View.VISIBLE);
//                    }
//
//                    @Override
//                    public void onAdClicked(Ad ad) {
//                    }
//
//                    @Override
//                    public void onLoggingImpression(Ad ad) {
//                    }
//                };
//                nativeAd.loadAd(
//                        nativeAd.buildLoadAdConfig()
//                                .withAdListener(nativeAdListener)
//                                .build());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void Facebook_Native_Ads1(final ViewGroup viewGroup) {
//        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
//            try {
//                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp1, viewGroup, false);
//                final TemplateView templateView = inflate.findViewById(R.id.my_template_large);
//                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
//                templateView.setVisibility(View.GONE);
//                final NativeAd nativeAd = new NativeAd(context, preference.get_Facebook_Native());
//                NativeAdListener nativeAdListener = new NativeAdListener() {
//                    @Override
//                    public void onMediaDownloaded(Ad ad) {
//                    }
//
//                    @Override
//                    public void onError(Ad ad, AdError adError) {
//                        Log.e("TAG", " Facebook Native_Ads  onAdFailedToLoad  " + adError.getErrorCode());
//                        templateView.setVisibility(View.GONE);
//                        Qureka_Predchamp_Native(viewGroup);
//                    }
//
//                    @Override
//                    public void onAdLoaded(Ad ad) {
//                        View adView = NativeAdView.render(context, nativeAd, NativeAdView.Type.HEIGHT_300);
//                        viewGroup.removeAllViews();
//                        viewGroup.addView(adView);
//                        viewGroup.setVisibility(View.VISIBLE);
//                    }
//
//                    @Override
//                    public void onAdClicked(Ad ad) {
//                    }
//
//                    @Override
//                    public void onLoggingImpression(Ad ad) {
//                    }
//                };
//                nativeAd.loadAd(
//                        nativeAd.buildLoadAdConfig()
//                                .withAdListener(nativeAdListener)
//                                .build());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private void Admob_FB_Multiple_Adaptive_Banner(final ViewGroup viewGroup) {
        final List<String> adUnitIds = Arrays.asList(new AppPreference(context).get_Admob_Banner_Id1(),
                new AppPreference(context).get_Admob_Banner_Id2(),
                new AppPreference(context).get_Admob_Banner_Id3());
        try {
            if (Constant.BannerAdCounter > 2) {
                Constant.BannerAdCounter = 0;
            }
            Log.e("BannerAdCounter out", String.valueOf(Constant.BannerAdCounter));
            final AdView adView = new AdView(context);
            adView.setAdSize(getAdSize(context));
            adView.setAdUnitId(adUnitIds.get(Constant.BannerAdCounter));
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    Log.e("TAG", "Admob  Fail -> onAdFailedToLoad: Banner" + loadAdError.getMessage());
                    super.onAdFailedToLoad(loadAdError);
                    try {
                        com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, preference.get_Facebook_Banner(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        viewGroup.removeAllViews();
                        viewGroup.addView(adView);
                        adView.loadAd(adView.buildLoadAdConfig().withAdListener(new com.facebook.ads.AdListener() {
                            @Override
                            public void onError(Ad ad, AdError adError) {
                                Log.e("TAG", " Facebook Adaptive_Banner  onAdFailedToLoad  " + adError.getErrorCode());
                                Qureka_Predchamp_Adaptive(viewGroup);
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        }).build());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onAdOpened() {
                    super.onAdOpened();
                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    Log.e("TAG", "Admob Load -> onAdLoaded: Banner");
                    Constant.BannerAdCounter++;
                    Log.e("BannerAdCounter", String.valueOf(Constant.BannerAdCounter));
                    try {
                        viewGroup.removeAllViews();
                        viewGroup.addView(adView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Admob_Adaptive_Banner(final ViewGroup viewGroup) {
        try {
            final AdView adView = new AdView(context);
            adView.setAdSize(getAdSize(context));
            adView.setAdUnitId(preference.get_Admob_Banner_Id1());
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    Log.e("TAG", "Admob  Fail -> onAdFailedToLoad: Banner" + loadAdError.getMessage());
                    super.onAdFailedToLoad(loadAdError);
                    com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, preference.get_Facebook_Banner(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                    viewGroup.removeAllViews();
                    viewGroup.addView(adView);
                    adView.loadAd(adView.buildLoadAdConfig().withAdListener(new com.facebook.ads.AdListener() {
                        @Override
                        public void onError(Ad ad, AdError adError) {
                            Log.e("TAG", " Facebook Adaptive_Banner  onAdFailedToLoad  " + adError.getErrorCode());
                            Qureka_Predchamp_Adaptive(viewGroup);
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {

                        }

                        @Override
                        public void onAdClicked(Ad ad) {

                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {

                        }
                    }).build());
                }

                @Override
                public void onAdOpened() {
                    super.onAdOpened();
                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    Log.e("TAG", "Admob Load -> onAdLoaded: Banner");

                    try {
                        viewGroup.removeAllViews();
                        viewGroup.addView(adView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Facebook_Adaptive_Banner(final ViewGroup viewGroup) {
        try {
            com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, preference.get_Facebook_Banner(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
            viewGroup.removeAllViews();
            viewGroup.addView(adView);
            adView.loadAd(adView.buildLoadAdConfig().withAdListener(new com.facebook.ads.AdListener() {
                @Override
                public void onError(Ad ad, AdError adError) {
                    Log.e("TAG", " Facebook Adaptive_Banner  onAdFailedToLoad  " + adError.getErrorCode());
                    final AdView adView = new AdView(context);
                    adView.setAdSize(getAdSize(context));
                    adView.setAdUnitId(preference.get_Admob_Banner_Id1());
                    AdRequest adRequest = new AdRequest.Builder().build();
                    adView.loadAd(adRequest);
                    adView.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            super.onAdClosed();
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            Log.e("TAG", "Admob  Fail -> onAdFailedToLoad: Banner" + loadAdError.getMessage());
                            super.onAdFailedToLoad(loadAdError);
                            Qureka_Predchamp_Adaptive(viewGroup);
                        }

                        @Override
                        public void onAdOpened() {
                            super.onAdOpened();
                        }

                        @Override
                        public void onAdLoaded() {
                            super.onAdLoaded();
                            Log.e("TAG", "Admob Load -> onAdLoaded: Banner");

                            try {
                                viewGroup.removeAllViews();
                                viewGroup.addView(adView);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onAdClicked() {
                            super.onAdClicked();
                        }

                        @Override
                        public void onAdImpression() {
                            super.onAdImpression();
                        }
                    });
                }

                @Override
                public void onAdLoaded(Ad ad) {

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            }).build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void Qureka_Predchamp_Native(final ViewGroup BannerContainer) {
//        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
//            if (preference.get_Qureka_Flag().equalsIgnoreCase("qureka")) {
//                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_native_ads, null, false);
//                Random r = new Random();
//                int i1 = r.nextInt(4 + 1);
//                ((ImageView) view.findViewById(R.id.img_banner)).setImageResource(Constant.qureka_native[i1]);
//                ((TextView) view.findViewById(R.id.tv_appname)).setText(Constant.qureka_header[i1]);
//                ((TextView) view.findViewById(R.id.tv_desc)).setText(Constant.qureka_description[i1]);
//                Glide.with(context).load(Constant.qureka_icon[i1]).into((ImageView) view.findViewById(R.id.img_logo));
//                view.findViewById(R.id.btn_install).setOnClickListener(v -> Constant.Open_Qureka(context));
//                BannerContainer.removeAllViews();
//                BannerContainer.addView(view);
//            } else if (preference.get_Qureka_Flag().equalsIgnoreCase("predchamp")) {
//                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_native_ads, null, false);
//                Random r = new Random();
//                int i1 = r.nextInt(4 + 1);
//                Glide.with(context).load(Constant.predchamp_icon[i1]).into((ImageView) view.findViewById(R.id.img_logo));
//                ((TextView) view.findViewById(R.id.tv_appname)).setText(Constant.predchamp_header[i1]);
//                ((TextView) view.findViewById(R.id.tv_desc)).setText(Constant.predchamp_description[i1]);
//                ((ImageView) view.findViewById(R.id.img_banner)).setImageResource(Constant.predchamp_native[i1]);
//                view.findViewById(R.id.btn_install).setOnClickListener(v -> Constant.Open_Qureka(context));
//                BannerContainer.removeAllViews();
//                BannerContainer.addView(view);
//            } else {
//                new Native_Ads_Static(context).Native_Ads(BannerContainer);
//            }
//        }
//    }
//
//    private void Qureka_Predchamp_Native_Banner(final ViewGroup BannerContainer) {
//        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
//
//            if (preference.get_Qureka_Flag().equalsIgnoreCase("qureka")) {
//                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_native_banner_ads, null, false);
//                Random r = new Random();
//                int i1 = r.nextInt(4 + 1);
//                ((TextView) view.findViewById(R.id.tv_appname)).setText(Constant.qureka_header[i1]);
//                ((TextView) view.findViewById(R.id.tv_desc)).setText(Constant.qureka_description[i1]);
//                Glide.with(context).load(Constant.qureka_icon[i1]).into((ImageView) view.findViewById(R.id.img_logo));
//                view.findViewById(R.id.btn_install).setOnClickListener(v -> Constant.Open_Qureka(context));
//                BannerContainer.removeAllViews();
//                BannerContainer.addView(view);
//            } else if (preference.get_Qureka_Flag().equalsIgnoreCase("predchamp")) {
//                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_native_banner_ads, null, false);
//                Random r = new Random();
//                int i1 = r.nextInt(4 + 1);
//                ((TextView) view.findViewById(R.id.tv_appname)).setText(Constant.predchamp_header[i1]);
//                ((TextView) view.findViewById(R.id.tv_desc)).setText(Constant.predchamp_description[i1]);
//                Glide.with(context).load(Constant.predchamp_icon[i1]).into((ImageView) view.findViewById(R.id.img_logo));
//                view.findViewById(R.id.btn_install).setOnClickListener(v -> Constant.Open_Qureka(context));
//                BannerContainer.removeAllViews();
//                BannerContainer.addView(view);
//            } else {
//                new Native_Ads_Static(context).Native_Banner_Ads(BannerContainer);
//            }
//        }
//    }

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
                new Native_Ads_Static(context).Adaptive_Banner(BannerContainer);
            }
        }
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

//    private void Admob_Native_Banner_Ads(final ViewGroup viewGroup) {
//        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
//            try {
//                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
//                final TemplateView templateView = inflate.findViewById(R.id.my_template_small);
//                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
//                templateView.setVisibility(View.GONE);
//                AdLoader.Builder builder = new AdLoader.Builder(context, preference.get_Admob_Native_Id1());
//                builder.forNativeAd(nativeAd -> {
//                    Log.d(TAG, "1 Admob_Native_Banner_Ads: ");
//                    NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
//                    templateView.setVisibility(View.VISIBLE);
//                    templateView.setStyles(build);
//                    templateView.setNativeAd(nativeAd);
//                    viewGroup.removeAllViews();
//                    viewGroup.addView(inflate);
//                });
//
//                builder.withAdListener(new AdListener() {
//                    @Override
//                    public void onAdClosed() {
//                        super.onAdClosed();
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        super.onAdFailedToLoad(loadAdError);
//                        templateView.setVisibility(View.GONE);
//                        Qureka_Predchamp_Native_Banner(viewGroup);
//                    }
//
//                    @Override
//                    public void onAdOpened() {
//                        super.onAdOpened();
//                    }
//
//                    @Override
//                    public void onAdLoaded() {
//                        Log.d(TAG, "1 Admob_Native_Banner_Ads: onAdLoaded");
//
//                        super.onAdLoaded();
//                    }
//                }).build().loadAd(new AdRequest.Builder().build());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            Qureka_Predchamp_Native_Banner(viewGroup);
//        }
//    }
//
//    private void Admob_Native_Banner_Ads1(final ViewGroup viewGroup) {
//        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
//            try {
//                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp1, viewGroup, false);
//                final TemplateView templateView = inflate.findViewById(R.id.my_template_small);
//                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
//                templateView.setVisibility(View.GONE);
//                AdLoader.Builder builder = new AdLoader.Builder(context, preference.get_Admob_Native_Id1());
//                builder.forNativeAd(nativeAd -> {
//                    NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
//                    templateView.setVisibility(View.VISIBLE);
//                    templateView.setStyles(build);
//                    templateView.setNativeAd(nativeAd);
//                    viewGroup.removeAllViews();
//                    viewGroup.addView(inflate);
//                });
//
//                builder.withAdListener(new AdListener() {
//                    @Override
//                    public void onAdClosed() {
//                        super.onAdClosed();
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        super.onAdFailedToLoad(loadAdError);
//                        templateView.setVisibility(View.GONE);
//                        Qureka_Predchamp_Native_Banner(viewGroup);
//                    }
//
//                    @Override
//                    public void onAdOpened() {
//                        super.onAdOpened();
//                    }
//
//                    @Override
//                    public void onAdLoaded() {
//                        super.onAdLoaded();
//                    }
//                }).build().loadAd(new AdRequest.Builder().build());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            Qureka_Predchamp_Native_Banner(viewGroup);
//        }
//    }
//
//    private void Admob_FB_Multiple_Native_Banner_Ads(final ViewGroup viewGroup) {
//        final List<String> adUnitIds = Arrays.asList(new AppPreference(context).get_Admob_Native_Id1(),
//                new AppPreference(context).get_Admob_Native_Id2(),
//                new AppPreference(context).get_Admob_Native_Id3());
//        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
//            try {
//                if (Constant.NAtiveBannerAdCounter > 2) {
//                    Constant.NAtiveBannerAdCounter = 0;
//                }
//                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
//                final TemplateView templateView = inflate.findViewById(R.id.my_template_small);
//                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
//                templateView.setVisibility(View.GONE);
//                AdLoader.Builder builder = new AdLoader.Builder(context, adUnitIds.get(Constant.NAtiveBannerAdCounter));
//                Log.e("NAtivBanerAdCounter out", String.valueOf(Constant.NAtiveBannerAdCounter));
//                builder.forNativeAd(nativeAd -> {
//                    NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
//                    Constant.NAtiveBannerAdCounter++;
//                    Log.e("NAtivBanerAdCounter", String.valueOf(Constant.NAtiveBannerAdCounter));
//                    templateView.setVisibility(View.VISIBLE);
//                    templateView.setStyles(build);
//                    templateView.setNativeAd(nativeAd);
//                    viewGroup.removeAllViews();
//                    viewGroup.addView(inflate);
//                });
//
//                builder.withAdListener(new AdListener() {
//                    @Override
//                    public void onAdClosed() {
//                        super.onAdClosed();
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        super.onAdFailedToLoad(loadAdError);
//                        NativeBannerAd nativeBannerAd = new NativeBannerAd(context, preference.get_Facebook_Native());
//                        NativeAdListener nativeAdListener = new NativeAdListener() {
//
//                            @Override
//                            public void onMediaDownloaded(Ad ad) {
//                                // Native ad finished downloading all assets
//                                Log.e(TAG, "Native ad finished downloading all assets.");
//                            }
//
//                            @Override
//                            public void onError(Ad ad, AdError adError) {
//                                // Native ad failed to load
//                                Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
//                                templateView.setVisibility(View.GONE);
//                                Qureka_Predchamp_Native_Banner(viewGroup);
//                            }
//
//                            @Override
//                            public void onAdLoaded(Ad ad) {
//                                // Native ad is loaded and ready to be displayed
//                                Log.d(TAG, "Native ad is loaded and ready to be displayed!");
//                                try {
//                                    View adView = NativeBannerAdView.render(context, nativeBannerAd, NativeBannerAdView.Type.HEIGHT_100);
//                                    viewGroup.removeAllViews();
//                                    viewGroup.addView(adView);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            @Override
//                            public void onAdClicked(Ad ad) {
//                                // Native ad clicked
//                                Log.d(TAG, "Native ad clicked!");
//                            }
//
//                            @Override
//                            public void onLoggingImpression(Ad ad) {
//                                // Native ad impression
//                                Log.d(TAG, "Native ad impression logged!");
//                            }
//                        };
//                        // load the ad
//                        nativeBannerAd.loadAd(
//                                nativeBannerAd.buildLoadAdConfig()
//                                        .withAdListener(nativeAdListener)
//                                        .build());
//                    }
//
//                    @Override
//                    public void onAdOpened() {
//                        super.onAdOpened();
//                    }
//
//                    @Override
//                    public void onAdLoaded() {
//                        super.onAdLoaded();
//                    }
//                }).build().loadAd(new AdRequest.Builder().build());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            Qureka_Predchamp_Native_Banner(viewGroup);
//        }
//    }
//
//    private void Admob_FB_Multiple_Native_Banner_Ads1(final ViewGroup viewGroup) {
//        final List<String> adUnitIds = Arrays.asList(new AppPreference(context).get_Admob_Native_Id1(),
//                new AppPreference(context).get_Admob_Native_Id2(),
//                new AppPreference(context).get_Admob_Native_Id3());
//        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
//            try {
//                if (Constant.NAtiveBannerAdCounter > 2) {
//                    Constant.NAtiveBannerAdCounter = 0;
//                }
//                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp1, viewGroup, false);
//                final TemplateView templateView = inflate.findViewById(R.id.my_template_small);
//                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
//                templateView.setVisibility(View.GONE);
//                AdLoader.Builder builder = new AdLoader.Builder(context, adUnitIds.get(Constant.NAtiveBannerAdCounter));
//                Log.e("NAtivBanerAdCounter out", String.valueOf(Constant.NAtiveBannerAdCounter));
//                builder.forNativeAd(nativeAd -> {
//                    NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
//                    Constant.NAtiveBannerAdCounter++;
//                    Log.e("NAtivBanerAdCounter", String.valueOf(Constant.NAtiveBannerAdCounter));
//                    templateView.setVisibility(View.VISIBLE);
//                    templateView.setStyles(build);
//                    templateView.setNativeAd(nativeAd);
//                    viewGroup.removeAllViews();
//                    viewGroup.addView(inflate);
//                });
//
//                builder.withAdListener(new AdListener() {
//                    @Override
//                    public void onAdClosed() {
//                        super.onAdClosed();
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        super.onAdFailedToLoad(loadAdError);
//                        NativeBannerAd nativeBannerAd = new NativeBannerAd(context, preference.get_Facebook_Native());
//                        NativeAdListener nativeAdListener = new NativeAdListener() {
//
//                            @Override
//                            public void onMediaDownloaded(Ad ad) {
//                                // Native ad finished downloading all assets
//                                Log.e(TAG, "Native ad finished downloading all assets.");
//                            }
//
//                            @Override
//                            public void onError(Ad ad, AdError adError) {
//                                // Native ad failed to load
//                                Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
//                                templateView.setVisibility(View.GONE);
//                                Qureka_Predchamp_Native_Banner(viewGroup);
//                            }
//
//                            @Override
//                            public void onAdLoaded(Ad ad) {
//                                // Native ad is loaded and ready to be displayed
//                                Log.d(TAG, "Native ad is loaded and ready to be displayed!");
//                                try {
//                                    View adView = NativeBannerAdView.render(context, nativeBannerAd, NativeBannerAdView.Type.HEIGHT_100);
//                                    viewGroup.removeAllViews();
//                                    viewGroup.addView(adView);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            @Override
//                            public void onAdClicked(Ad ad) {
//                                // Native ad clicked
//                                Log.d(TAG, "Native ad clicked!");
//                            }
//
//                            @Override
//                            public void onLoggingImpression(Ad ad) {
//                                // Native ad impression
//                                Log.d(TAG, "Native ad impression logged!");
//                            }
//                        };
//                        // load the ad
//                        nativeBannerAd.loadAd(
//                                nativeBannerAd.buildLoadAdConfig()
//                                        .withAdListener(nativeAdListener)
//                                        .build());
//                    }
//
//                    @Override
//                    public void onAdOpened() {
//                        super.onAdOpened();
//                    }
//
//                    @Override
//                    public void onAdLoaded() {
//                        super.onAdLoaded();
//                    }
//                }).build().loadAd(new AdRequest.Builder().build());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            Qureka_Predchamp_Native_Banner(viewGroup);
//        }
//    }
//
//    private void Facebook_Native_Banner_Ads(final ViewGroup viewGroup) {
//        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
//            try {
//                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
//                final TemplateView templateView = inflate.findViewById(R.id.my_template_small);
//                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
//                templateView.setVisibility(View.GONE);
//
//                NativeBannerAd nativeBannerAd = new NativeBannerAd(context, preference.get_Facebook_Native());
//                NativeAdListener nativeAdListener = new NativeAdListener() {
//
//                    @Override
//                    public void onMediaDownloaded(Ad ad) {
//                        // Native ad finished downloading all assets
//                        Log.e(TAG, "Native ad finished downloading all assets.");
//                    }
//
//                    @Override
//                    public void onError(Ad ad, AdError adError) {
//                        // Native ad failed to load
//                        Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
//                        templateView.setVisibility(View.GONE);
//                        Qureka_Predchamp_Native_Banner(viewGroup);
//                    }
//
//                    @Override
//                    public void onAdLoaded(Ad ad) {
//                        // Native ad is loaded and ready to be displayed
//                        Log.d(TAG, "Native ad is loaded and ready to be displayed!");
//                        try {
//                            View adView = NativeBannerAdView.render(context, nativeBannerAd, NativeBannerAdView.Type.HEIGHT_100);
//                            viewGroup.removeAllViews();
//                            viewGroup.addView(adView);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onAdClicked(Ad ad) {
//                        // Native ad clicked
//                        Log.d(TAG, "Native ad clicked!");
//                    }
//
//                    @Override
//                    public void onLoggingImpression(Ad ad) {
//                        // Native ad impression
//                        Log.d(TAG, "Native ad impression logged!");
//                    }
//                };
//                // load the ad
//                nativeBannerAd.loadAd(
//                        nativeBannerAd.buildLoadAdConfig()
//                                .withAdListener(nativeAdListener)
//                                .build());
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void Facebook_Native_Banner_Ads1(final ViewGroup viewGroup) {
//        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
//            try {
//                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp1, viewGroup, false);
//                final TemplateView templateView = inflate.findViewById(R.id.my_template_small);
//                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
//                templateView.setVisibility(View.GONE);
//
//                NativeBannerAd nativeBannerAd = new NativeBannerAd(context, preference.get_Facebook_Native());
//                NativeAdListener nativeAdListener = new NativeAdListener() {
//
//                    @Override
//                    public void onMediaDownloaded(Ad ad) {
//                        // Native ad finished downloading all assets
//                        Log.e(TAG, "Native ad finished downloading all assets.");
//                    }
//
//                    @Override
//                    public void onError(Ad ad, AdError adError) {
//                        // Native ad failed to load
//                        Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
//                        Qureka_Predchamp_Native_Banner(viewGroup);
//                    }
//
//                    @Override
//                    public void onAdLoaded(Ad ad) {
//                        // Native ad is loaded and ready to be displayed
//                        Log.d(TAG, "Native ad is loaded and ready to be displayed!");
//                        try {
//                            View adView = NativeBannerAdView.render(context, nativeBannerAd, NativeBannerAdView.Type.HEIGHT_100);
//                            viewGroup.removeAllViews();
//                            viewGroup.addView(adView);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onAdClicked(Ad ad) {
//                        // Native ad clicked
//                        Log.d(TAG, "Native ad clicked!");
//                    }
//
//                    @Override
//                    public void onLoggingImpression(Ad ad) {
//                        // Native ad impression
//                        Log.d(TAG, "Native ad impression logged!");
//                    }
//                };
//                // load the ad
//                nativeBannerAd.loadAd(
//                        nativeBannerAd.buildLoadAdConfig()
//                                .withAdListener(nativeAdListener)
//                                .build());
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
