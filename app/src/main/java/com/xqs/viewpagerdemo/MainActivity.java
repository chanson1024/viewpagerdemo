package com.xqs.viewpagerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ViewPager viewPager;
    public Button mSwitch;

    List<ImageView> lists = new ArrayList<>();

    ImageAdapter imageAdapter;

    int currentIndex = 0;

    int gender = 1;

    public ImageView view1;
    public ImageView view2;
    public ImageView view3;

    int[] boys = new int[]{R.mipmap.boy01, R.mipmap.boy02, R.mipmap.boy03};
    int[] girls = new int[]{R.mipmap.girl01, R.mipmap.girl02, R.mipmap.girl03};

    ImageHandler mHandler = new ImageHandler(new WeakReference<>(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = LayoutInflater.from(this);
        view1 = (ImageView) inflater.inflate(R.layout.item_image, null);
        view2 = (ImageView) inflater.inflate(R.layout.item_image, null);
        view3 = (ImageView) inflater.inflate(R.layout.item_image, null);

        lists.addAll(initImageData(gender));

        viewPager = (ViewPager) findViewById(R.id.vp);
        mSwitch = (Button) findViewById(R.id.btn_switch);

        imageAdapter = new ImageAdapter(lists);

        viewPager.setAdapter(imageAdapter);

        mHandler.sendMessageDelayed(new Message(), 2000);

        mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("gender", "gender-->" + gender);
                if (gender == 1) {
                    switchImage(1);
                    gender = 2;
                } else {
                    switchImage(2);
                    gender = 1;
                }
            }
        });

    }

    public void switchImage(int gender) {
        lists.clear();
        lists.addAll(initImageData(gender));
        imageAdapter.notifyDataSetChanged();
    }

    public List<ImageView> initImageData(int gender) {
        if (gender == 1) {
            view1.setImageResource(boys[0]);
            view2.setImageResource(boys[1]);
            view3.setImageResource(boys[2]);
        } else {
            view1.setImageResource(girls[0]);
            view2.setImageResource(girls[1]);
            view3.setImageResource(girls[2]);
        }
        List<ImageView> lists = new ArrayList<>();

        lists.add(view1);
        lists.add(view2);
        lists.add(view3);
        return lists;
    }


    public static class ImageHandler extends Handler {
        private WeakReference<MainActivity> weakReference;

        protected ImageHandler(WeakReference<MainActivity> wk) {
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = weakReference.get();
            if (activity == null) {
                return;
            }

            activity.currentIndex++;
            if (activity.currentIndex >= activity.lists.size()) {
                activity.currentIndex = 0;
            }
            activity.viewPager.setCurrentItem(activity.currentIndex);
            activity.mHandler.sendMessageDelayed(new Message(), 3000);
        }
    }

}
