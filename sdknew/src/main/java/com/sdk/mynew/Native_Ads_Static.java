package com.sdk.mynew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdk.mynew.R;
import com.facebook.shimmer.ShimmerFrameLayout;

public class Native_Ads_Static {
    Context context;

    public Native_Ads_Static(Context context) {
        this.context = context;
    }

    public void Native_Ads(ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_static_native_ads, null);
        viewGroup.removeAllViews();
        ShimmerFrameLayout shimmerContainer = view.findViewById(R.id.shimmer_view_container);
        shimmerContainer.startShimmer();
        viewGroup.addView(view);
    }

    public void Native_Ads1(ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_static_native_ads1, null);
        viewGroup.removeAllViews();
        ShimmerFrameLayout shimmerContainer = view.findViewById(R.id.shimmer_view_container);
        shimmerContainer.startShimmer();
        viewGroup.addView(view);
    }

    public void Native_Banner_Ads(ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_static_native_banner_ads, null);
        viewGroup.removeAllViews();
        ShimmerFrameLayout shimmerContainer = view.findViewById(R.id.shimmer_view_container);
        shimmerContainer.startShimmer();
        viewGroup.addView(view);
    }

    public void Native_Banner_Ads1(ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_static_native_banner_ads1, null);
        viewGroup.removeAllViews();
        ShimmerFrameLayout shimmerContainer = view.findViewById(R.id.shimmer_view_container);
        shimmerContainer.startShimmer();
        viewGroup.addView(view);
    }

    public void Adaptive_Banner(ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_static_adaptive_ads, null);
        viewGroup.removeAllViews();
        ShimmerFrameLayout shimmerContainer = view.findViewById(R.id.shimmer_view_container);
        shimmerContainer.startShimmer();
        viewGroup.addView(view);
    }
}
