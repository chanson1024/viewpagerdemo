package com.xqs.viewpagerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.xqs.viewpagerdemo.fragment.AdFragment;
import com.xqs.viewpagerdemo.fragment.ImageAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    List<ImageView> lists = new ArrayList<>();

    ImageAdapter imageAdapter;

    int currentIndex = 0;

    int[] boys = new int[]{R.mipmap.boy01, R.mipmap.boy02, R.mipmap.boy03};
    int[] girls = new int[]{R.mipmap.girl01, R.mipmap.girl02, R.mipmap.girl03};



    ImageHandler mHandler = new ImageHandler(new WeakReference<>(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = LayoutInflater.from(this);
        ImageView view1 = (ImageView) inflater.inflate(R.layout.item_image, null);
        ImageView view2 = (ImageView) inflater.inflate(R.layout.item_image, null);
        ImageView view3 = (ImageView) inflater.inflate(R.layout.item_image, null);

        view1.setImageResource(boys[0]);
        view2.setImageResource(boys[1]);
        view3.setImageResource(boys[2]);

        lists.add(view1);
        lists.add(view2);
        lists.add(view3);

        viewPager = (ViewPager) findViewById(R.id.vp);

        imageAdapter = new ImageAdapter(lists);

        viewPager.setAdapter(imageAdapter);

        mHandler.sendMessageDelayed(new Message(), 2000);

    }


    public static class ImageHandler extends Handler{
        private WeakReference<MainActivity> weakReference;

        protected ImageHandler(WeakReference<MainActivity> wk){
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = weakReference.get();
            if (activity==null){
                return ;
            }

            activity.currentIndex++;
            if(activity.currentIndex >= activity.lists.size()){
                activity.currentIndex=0;
            }
            activity.viewPager.setCurrentItem(activity.currentIndex);
            activity.mHandler.sendMessageDelayed(new Message(), 2000);
        }
    }

}
