package com.project.chauhq.yenxao.ui.util;

import android.app.Activity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * @author ChauHQ
 */
public final class GooglePlayService {
    private GooglePlayService() {
    }
    /**
     * This function will check Google Play Service already install in device or not yet.
     *
     * @param activity It's improve parameter.
     * @return function will return true/false have google service in device
     */
    public static boolean isGooglePlayServicesAvailable(Activity activity) {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, activity, 0).show();
            return false;
        }
    }
}
