package com.project.chauhq.yenxao.ui.Home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.chauhq.yenxao.BaseFragment;
import com.project.chauhq.yenxao.R;
import com.project.chauhq.yenxao.model.TitleValueEntity;
import com.project.chauhq.yenxao.ui.MainActivity;
import com.project.chauhq.yenxao.ui.comment.CommentFragment_;
import com.project.chauhq.yenxao.widget.CustomLinearLayoutManager;
import com.project.chauhq.yenxao.widget.SpiderWebChart;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChauHQ
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {
    public static final LatLng MY_LAT_LNG = new LatLng(16.062113, 108.209000);
    private static final int PAGE_NUMS = 3;
    @ViewById(R.id.spiderWebChart)
    SpiderWebChart mSpiderWebChart;

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @ViewById(R.id.ratingBar)
    RatingBar mRatingBar;

    @ViewById(R.id.pager)
    ViewPager mPager;

    @ViewById(R.id.imgNext)
    ImageView mImgNext;
    @ViewById(R.id.imgPrevious)
    ImageView mImgPrevious;

    private int mCurrentPager = 0;
    private GoogleMap mMap;
    private CommentAdapter mAdapter;
    private List<TitleValueEntity> mData = new ArrayList<>();
    private PhotoPagerAdapter mPhotoAdapter;
    private boolean mReload;

    @Override
    protected void afterViews() {
        setUpGoogleMap();
        setUpPhotoPager();
        initSpiderWebChart();
        setRateNum();
        setUpRecyclerView();
        initListener();

    }

    @Click(R.id.imgNext)
    void onNextClick() {
        mPager.setCurrentItem(++mCurrentPager);
        hideShowButtonDumpFragment();
    }

    @Click(R.id.imgPrevious)
    void onPreviousClick() {
        mPager.setCurrentItem(--mCurrentPager);
        hideShowButtonDumpFragment();
    }

    private void initSpiderWebChart() {
        mData.add(new TitleValueEntity("Quality", 10 / 2));
        mData.add(new TitleValueEntity("Price", 5 / 2));
        mData.add(new TitleValueEntity("Service", 8 / 2));
        mData.add(new TitleValueEntity("Reliability", 6 / 2));
        mData.add(new TitleValueEntity("Friendly", 4 / 2));

        List<List<TitleValueEntity>> data = new ArrayList<>();
        data.add(mData);

        mSpiderWebChart.setData(data);
        mSpiderWebChart.setLatitudeNum(5);
        mSpiderWebChart.setLongitudeNum(5);
        mSpiderWebChart.setDisplayLatitude(true);
    }

    private void setRateNum() {
        int rateNums = 0;
        for (TitleValueEntity entity : mData) {
            rateNums = (int) (rateNums + entity.getValue());
        }
        rateNums = rateNums / 5;
        mRatingBar.setRating(rateNums);
    }

    private void setUpRecyclerView() {
        mAdapter = new CommentAdapter(getActivity());
        mRecyclerView.setLayoutManager(new CustomLinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setUpGoogleMap() {

        mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        mMap.getUiSettings().setScrollGesturesEnabled(false);
        mMap.getUiSettings().setZoomGesturesEnabled(false);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.setInfoWindowAdapter(new InfoWIndownMapAdapter());

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(MY_LAT_LNG)      // Sets the center of the map to Mountain View
                .zoom(15)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions().position(MY_LAT_LNG)).showInfoWindow();
    }

    private void setUpPhotoPager() {
        mPhotoAdapter = new PhotoPagerAdapter(getChildFragmentManager());
        mPager.setAdapter(mPhotoAdapter);
        mPager.setCurrentItem(mCurrentPager);
        mPager.setOffscreenPageLimit(PAGE_NUMS);
    }

    private void initListener() {
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPager = position;
                hideShowButtonDumpFragment();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void hideShowButtonDumpFragment() {
        mImgNext.setVisibility(mCurrentPager == PAGE_NUMS - 1 ? View.GONE : View.VISIBLE);
        mImgPrevious.setVisibility(mCurrentPager == 0 ? View.GONE : View.VISIBLE);
    }

    private class InfoWIndownMapAdapter implements GoogleMap.InfoWindowAdapter {

        @Override
        public View getInfoWindow(Marker marker) {
            return LayoutInflater.from(getActivity()).inflate(R.layout.custom_info_window_map, null, false);
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }

    private class PhotoPagerAdapter extends FragmentStatePagerAdapter {

        public PhotoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PhotoFragment_.builder().build();
        }

        @Override
        public int getCount() {
            return PAGE_NUMS;
        }
    }

    @Click(R.id.btnMore)
    void onMoreClick() {
        ((MainActivity) getActivity()).getMPager().setCurrentItem(1);
    }

    @Click(R.id.edtComment)
    void onCommentClick() {
        replaceFragment(CommentFragment_.builder().build(), false);
    }

    @Override
    public void onPause() {
        super.onPause();
        mReload = true;
    }

    public void onDestroyView() {
        super.onDestroyView();
        Fragment f = getChildFragmentManager().findFragmentById(R.id.map);
        if (f != null) {
            getChildFragmentManager()
                    .beginTransaction().remove(f).commitAllowingStateLoss();
        }
    }
}
