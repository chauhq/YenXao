package com.project.chauhq.yenxao.ui.product;

import com.project.chauhq.yenxao.BaseContainerFragment;
import com.project.chauhq.yenxao.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * @author ChauHQ
 */
@EFragment(R.layout.container_frame_layout)
public class ContainerProductFragment extends BaseContainerFragment {

    @AfterViews
    void afterViews() {
        replaceFragment(ProductFragment_.builder().build(), false);
    }
}
