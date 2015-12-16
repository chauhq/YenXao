package com.project.chauhq.yenxao.ui.info;

import com.project.chauhq.yenxao.BaseContainerFragment;
import com.project.chauhq.yenxao.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * @author ChauHQ
 */

@EFragment(R.layout.container_frame_layout)
public class ContainerInfoFragment extends BaseContainerFragment {

    @AfterViews
    void afterViews() {
        replaceFragment(InfoFragment_.builder().build(), false);
    }

}
