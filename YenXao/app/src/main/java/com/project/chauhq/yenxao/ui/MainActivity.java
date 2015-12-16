package com.project.chauhq.yenxao.ui;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.project.chauhq.yenxao.BaseActivity;
import com.project.chauhq.yenxao.BaseContainerFragment;
import com.project.chauhq.yenxao.R;
import com.project.chauhq.yenxao.model.ItemTab;
import com.project.chauhq.yenxao.ui.Home.ContainerHomeFragment_;
import com.project.chauhq.yenxao.ui.info.ContainerInfoFragment_;
import com.project.chauhq.yenxao.ui.map.ContainerMapFragment_;
import com.project.chauhq.yenxao.ui.map.MapFragment_;
import com.project.chauhq.yenxao.ui.product.ContainerProductFragment_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import lombok.Getter;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private static final int MAX_TAB_ITEM_LENGHT = 4;
    @Getter
    @ViewById(R.id.pager)
    ViewPager mPager;
    @ViewById(R.id.tabs)
    PagerSlidingTabStrip mTabs;

    private ItemTab[] mItemTabs = new ItemTab[MAX_TAB_ITEM_LENGHT];
    private boolean mDoubleBackToExitPressedOnce;
    private MainPagerAdapter mAdapter;

    @Override
    protected void afterViews() {
        setItemTab();
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mTabs.setViewPager(mPager);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        initListener();

    }

    private void setItemTab() {
        mItemTabs[0] = new ItemTab(ContainerHomeFragment_.builder().build(), getString(R.string.tab_home_title));
        mItemTabs[1] = new ItemTab(ContainerProductFragment_.builder().build(), getString(R.string.tab_product_title));
        mItemTabs[2] = new ItemTab(ContainerInfoFragment_.builder().build(), getString(R.string.tab_info_title));
        mItemTabs[3] = new ItemTab(ContainerMapFragment_.builder().build(), getString(R.string.tab_info_location));
    }

    private void initListener() {
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                Fragment fragment = getCurrentBaseFragment().getCurrentFragment();
                if(fragment instanceof MapFragment_) {
                    ((MapFragment_) fragment).showGPSDialog();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class MainPagerAdapter extends FragmentStatePagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mItemTabs[position].getFragment();
        }

        @Override
        public int getCount() {
            return mItemTabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mItemTabs[position].getTitle();
        }
    }

    @Override
    public void onBackPressed() {
        onBack();
    }

    private void onBack() {
        boolean isPopFragment = false;
        BaseContainerFragment f = getCurrentBaseFragment();
        if (f != null) {
            isPopFragment = f.popFragment();
        }

        if (!isPopFragment) {
            if (mDoubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.mDoubleBackToExitPressedOnce = true;
            Toast.makeText(this, "double back", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDoubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    private BaseContainerFragment getCurrentBaseFragment() {
        return (BaseContainerFragment) mAdapter
                .instantiateItem(mPager, mPager.getCurrentItem());
    }
}
