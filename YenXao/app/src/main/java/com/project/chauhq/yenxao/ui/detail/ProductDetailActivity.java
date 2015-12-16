package com.project.chauhq.yenxao.ui.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.project.chauhq.yenxao.BaseActivity;
import com.project.chauhq.yenxao.R;
import com.project.chauhq.yenxao.model.Product;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * @author ChauHQ
 */
@EActivity(R.layout.activity_product_detail)
public class ProductDetailActivity extends BaseActivity {

    @ViewById(R.id.pager)
    ViewPager mPager;

    @Extra
    ArrayList<Product> mProducts;

    @Extra
    int mPosition;

    private DetailAdapter mAdapter;

    @Override
    protected void afterViews() {
        mAdapter = new DetailAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(mPosition);
        initListener();

    }

    private void initListener() {
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //mPosition = position;
                Log.d("xxxxx", "xxx"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class DetailAdapter extends FragmentStatePagerAdapter {

        public DetailAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ProductDetailFragment_.builder().mProduct(mProducts.get(position)).build();
        }

        @Override
        public int getCount() {
            return mProducts.size();
        }
    }
}
