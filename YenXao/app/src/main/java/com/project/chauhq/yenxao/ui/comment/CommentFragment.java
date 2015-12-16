package com.project.chauhq.yenxao.ui.comment;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;

import com.project.chauhq.yenxao.BaseFragment;
import com.project.chauhq.yenxao.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.regex.Pattern;

/**
 * @author ChauHQ
 */

@EFragment(R.layout.fragment_comment)
public class CommentFragment extends BaseFragment {

    @ViewById(R.id.tvUserName)
    TextView mTvUserName;

    private String name;

    @Override
    protected void afterViews() {
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(getActivity()).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                name = account.name;

            }
        }
        name = name.replace("@gmail.com","");
        mTvUserName.setText(name);
    }

    @Click(R.id.btnPost)
    void onPostCLick() {
        Toast.makeText(getActivity(), "Post commnet", Toast.LENGTH_LONG).show();
        getActivity().finish();
    }
}
