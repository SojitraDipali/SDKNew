package com.sdk.mynew;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class Reward_Ad {

    public RewardedAd ADrewardedad;

    public void Premium_Dialog(Activity activity) {
        Dialog dialog = new Dialog(activity, R.style.transparent_dialog);
        dialog.setContentView(R.layout.layout_reward_dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.findViewById(R.id.iv_close).setOnClickListener(v -> dialog.dismiss());
        dialog.findViewById(R.id.cv_no).setOnClickListener(v -> dialog.dismiss());
        dialog.findViewById(R.id.cv_yes).setOnClickListener(v -> {
            (dialog.findViewById(R.id.pb_loading)).setVisibility(View.VISIBLE);
            Show_Reward_Ads(activity, dialog);
        });
        dialog.show();

    }

    private void Show_Reward_Ads(Activity source_class, Dialog dialog) {
        if (new AppPreference(source_class).get_Ad_Status().equalsIgnoreCase("on")) {
            if (new AppPreference(source_class).get_Adstyle().equalsIgnoreCase("Normal")) {
                ShowAdReward_Admob_Adx(source_class, dialog);
            }
        }
    }

    public void ShowAdReward_Admob_Adx(Activity source_class, Dialog dialog) {
        AppPreference preference = new AppPreference(source_class);
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(source_class, preference.get_Admob_Rewarded_Id(), adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                Log.d("TAG", loadAdError.getMessage());
                RewardedAd.load(source_class, preference.get_Admob_Rewarded_Id(), adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d("TAG", loadAdError.getMessage());
                        ADrewardedad = null;
                        dialog.dismiss();
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        dialog.dismiss();
                        ADrewardedad = rewardedAd;
                        ADrewardedad.show(source_class, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                //Your Code Goes Here
                            }
                        });
                        ADrewardedad.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdShowedFullScreenContent() {
                                dialog.dismiss();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                Log.d("TAG", "Ad failed to show.");
                                dialog.dismiss();

                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Log.d("TAG", "Ad was dismissed.");
                                ADrewardedad = null;

                            }
                        });
                    }
                });
            }

            @Override
            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                ADrewardedad = rewardedAd;
                ADrewardedad.show(source_class, new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        //Your Code Goes Here
                    }
                });
                ADrewardedad.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdShowedFullScreenContent() {
                        dialog.dismiss();

                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        Log.d("TAG", "Ad failed to show.");
                        ADrewardedad = null;
                        dialog.dismiss();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        Log.d("TAG", "Ad was dismissed.");
                        ADrewardedad = null;

                    }
                });
            }
        });
    }

}
