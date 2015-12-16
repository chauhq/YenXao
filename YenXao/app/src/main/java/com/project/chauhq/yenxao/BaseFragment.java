package com.project.chauhq.yenxao;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * @author ChauHQ
 */

@EFragment
public abstract class BaseFragment extends Fragment {
    protected DisplayImageOptions mOptions;
    protected DisplayImageOptions mOptionsContainCircle;
    protected ImageLoader mImageLoader;

    @AfterViews
    protected abstract void afterViews();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
        mOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .showImageForEmptyUri(R.drawable.img_yen_xao)
                .showImageOnFail(R.drawable.img_yen_xao)
                .cacheOnDisk(true)
                .considerExifParams(true)
                        //.displayer(new CircleBitmapDisplayer(Color.WHITE, 5)) // image circle
                .build();

        mOptionsContainCircle = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new CircleBitmapDisplayer(Color.WHITE, 5)) // image circle
                .build();
    }

    protected void replaceFragment(Fragment fragment, boolean addToStack) {
        ((BaseContainerFragment) getParentFragment()).replaceFragment(fragment,addToStack);
    }
}
