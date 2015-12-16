package com.project.chauhq.yenxao.ui.Home;

import android.widget.ImageView;

import com.project.chauhq.yenxao.BaseFragment;
import com.project.chauhq.yenxao.R;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * @author ChauHQ
 */
@EFragment(R.layout.fragment_photo)
public class PhotoFragment extends BaseFragment {

    @ViewById(R.id.imgPhoto)
    ImageView mImgPhoto;

    @Override
    protected void afterViews() {
        mImageLoader.displayImage("drawable://" +R.drawable.img_yen_xao , mImgPhoto, mOptions);
    }

}
