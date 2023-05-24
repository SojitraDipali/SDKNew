package com.sdk.mynew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

        private final AppPreference preference;
        private final Context context;
        List<Integer> datalist;

        public SliderAdapter(Context context, List<Integer> datalist) {
            this.context = context;
            this.datalist = datalist;
            preference = new AppPreference(this.context);
        }

        @Override
        public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
            return new SliderAdapterVH(inflate);
        }

        @Override
        public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
            Glide.with(context).load(datalist.get(position)).into(viewHolder.img_qureka);
            viewHolder.itemView.setOnClickListener(v -> Constant.Open_Qureka(context));
        }

        @Override
        public int getCount() {
            return datalist.size();
        }

        static class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

            View itemView;
            ImageView img_qureka;

            public SliderAdapterVH(View itemView) {
                super(itemView);
                img_qureka = itemView.findViewById(R.id.img_qureka);
                this.itemView = itemView;
            }
        }

    }
