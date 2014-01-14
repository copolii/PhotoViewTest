package com.example.pvt;

import android.app.ActionBar;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewTest extends Activity implements PhotoViewAttacher.OnViewTapListener {

    private static final String LOGTAG = "PhotoViewTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view_test);

        final ViewPager pager = (ViewPager) findViewById (R.id.pager);
        pager.setAdapter (new ImagesAdapter ());
    }

    @Override
    public void onViewTap (final View view, final float v, final float v2) {
        Log.d (LOGTAG, "View Tapped");
        final ActionBar ab = getActionBar ();

        if (ab.isShowing ())
            ab.hide ();
        else
            ab.show ();
    }

    private static final Uri[] PICTURES = {
        Uri.parse ("file:///android_asset/hubble.jpg"),
        Uri.parse ("file:///android_asset/hubble1.jpg"),
        Uri.parse ("file:///android_asset/hubble2.jpg"),
        Uri.parse ("file:///android_asset/hubble3.jpg")
    };

    private class ImagesAdapter
      extends PagerAdapter {

        @Override
        public int getCount () {
            return PICTURES.length;
        }

        @Override
        public View instantiateItem (final ViewGroup container, final int position) {
            final PhotoView photoView = new PhotoView (container.getContext ());

            Picasso.with (PhotoViewTest.this)
                   .load (PICTURES[position])
                   .placeholder (R.drawable.ic_launcher)
                   .into (photoView);

            photoView.setOnViewTapListener (PhotoViewTest.this);

            container.addView (photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            photoView.setOnViewTapListener (PhotoViewTest.this);

            return photoView;
        }

        @Override
        public void destroyItem (final ViewGroup container, final int position, final Object object) {
            final PhotoView view = (PhotoView) object;
            view.setOnViewTapListener (null);
            container.removeView (view);
        }

        @Override
        public boolean isViewFromObject (final View view, final Object o) {
            return view == o;
        }
    }
}
