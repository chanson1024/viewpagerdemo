package com.xqs.viewpagerdemo;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by xiaoqingsong
 * Date: 29/07/2017
 * Time: 10:13 PM
 */

public class ImageAdapter extends PagerAdapter {

    private List<ImageView> imageViews;

    public ImageAdapter(List<ImageView> imageViews){
        this.imageViews = imageViews;
    }


    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView view = imageViews.get(position);

        container.addView(view);
        //add listeners here if necessary
        return view;
    }
}
