package com.project.chauhq.yenxao.ui.util;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;

/**
 * @author ChauHQ
 */
public class GPS {

    public static boolean isGPSAvailability(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Activity.LOCATION_SERVICE);
        return locationManager.isProviderEnabled (LocationManager.GPS_PROVIDER);
    }
}
