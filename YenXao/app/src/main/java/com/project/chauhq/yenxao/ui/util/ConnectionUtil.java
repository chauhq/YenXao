package com.project.chauhq.yenxao.ui.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author ChauHQ
 */
public final class ConnectionUtil {
    private ConnectionUtil() {
    }
    /**
     * Get the network info
     */
    private static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }
    /**
     * Check if there is any connectivity
     */
    public static boolean isConnected(Context context) {
        NetworkInfo info = ConnectionUtil.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }
}
