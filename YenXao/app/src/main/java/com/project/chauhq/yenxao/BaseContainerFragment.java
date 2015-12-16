package com.project.chauhq.yenxao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * @author ChauHQ
 */
public class BaseContainerFragment extends Fragment {

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if(!addToBackStack) {
            transaction.addToBackStack(fragment.getTag());
        }
        transaction.replace(R.id.containerFrameLayout, fragment);
        transaction.commitAllowingStateLoss();
        getChildFragmentManager().executePendingTransactions();
    }

    public boolean popFragment() {
        boolean isPop = false;
        FragmentManager fm = getChildFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            isPop = true;
            getChildFragmentManager().popBackStack();
        }
        return isPop;
    }

    public Fragment getCurrentFragment() {
        return getChildFragmentManager().findFragmentById(
                R.id.containerFrameLayout);
    }
}
