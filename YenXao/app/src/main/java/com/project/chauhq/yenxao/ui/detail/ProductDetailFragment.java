package com.project.chauhq.yenxao.ui.detail;

import android.widget.ImageView;

import com.project.chauhq.yenxao.BaseFragment;
import com.project.chauhq.yenxao.R;
import com.project.chauhq.yenxao.model.Product;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * @author ChauHQ
 */
@EFragment(R.layout.fragment_product_detail)
public class ProductDetailFragment extends BaseFragment {

    @FragmentArg
    Product mProduct;

    @ViewById(R.id.imgPhoto)
    ImageView mImgPhoto;

    @Override
    protected void afterViews() {
        mImageLoader.displayImage(mProduct.getUrlImage(), mImgPhoto, mOptions);
    }
}
