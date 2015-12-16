package com.project.chauhq.yenxao.model;

import android.support.v4.app.Fragment;

import lombok.Data;

/**
 * @author ChauHQ
 */
@Data
public class ItemTab {
    private Fragment fragment;
    private String title;

    public ItemTab(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }
}
