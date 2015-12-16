package com.project.chauhq.yenxao.BusProvider;

import com.squareup.otto.Bus;

/**
 * @author ChauHQ
 */
public final class BusProvider {
    private static final Bus BUS = new Bus();

    private BusProvider() {

    }

    public static Bus getInstance() {
        return BUS;
    }
}
