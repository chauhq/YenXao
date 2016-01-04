package com.project.chauhq.yenxao.ui.map;

import com.project.chauhq.yenxao.BaseContainerFragment;
import com.project.chauhq.yenxao.R;
import com.project.chauhq.yenxao.ui.util.ConnectionUtil;
import com.project.chauhq.yenxao.ui.util.GooglePlayService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * @author ChauHQ
 */

@EFragment(R.layout.container_frame_layout)
public class ContainerMapFragment extends BaseContainerFragment {

    @AfterViews
    void afterViews() {
        if (!ConnectionUtil.isConnected(getActivity())) {
            return;
        }
        if (!GooglePlayService.isGooglePlayServicesAvailable(getActivity())) {
            return;
        }
       // replaceFragment(MapFragment_.builder().build(), false);
    }

}
