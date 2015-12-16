package com.project.chauhq.yenxao;

import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * @author ChauHQ
 */
@EActivity
public abstract class BaseActivity extends AppCompatActivity {

    @AfterViews
    protected abstract void afterViews();
}
