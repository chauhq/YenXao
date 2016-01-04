package com.project.chauhq.yenxao.ui.product;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.project.chauhq.yenxao.BaseFragment;
import com.project.chauhq.yenxao.R;
import com.project.chauhq.yenxao.api.RequestApi;
import com.project.chauhq.yenxao.model.Product;
import com.project.chauhq.yenxao.model.ProductResponse;
import com.project.chauhq.yenxao.ui.detail.ProductDetailActivity_;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * @author ChauHQ
 */

@EFragment(R.layout.fragment_product)
public class ProductFragment extends BaseFragment {

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    private List<Product> mProducts = new ArrayList<>();

    @Override
    protected void afterViews() {
        setUpRecyclerView();
        dummyData();
        initListener();
    }

    private void dummyData() {

        Call<ProductResponse.Product> call = RequestApi.requestHomeApi().getProductResponse();
        call.enqueue(new Callback<ProductResponse.Product>() {
            @Override
            public void onResponse(Response<ProductResponse.Product> response, Retrofit retrofit) {
                Log.d("xxxx","Ok");
            }
            @Override
            public void onFailure(Throwable t) {
                Log.d("xxxx","fail");
            }
        });
        Product p = new Product("http://ApiClient.androidhive.info/feed/img/time_best.jpg", "Yen Xao Da Thanh", 1000);
        Product p1 = new Product("http://ApiClient.androidhive.info/feed/img/cosmos.jpg", "Yen Xao Da Thanh", 3000);
        Product p2 = new Product("xxxx", "Yen Xao Da Thanh", 2000);
        Product p3 = new Product("xxxx", "Yen Xao Da Thanh", 5000);
        Product p4 = new Product("xxxx", "Yen Xao Da Thanh", 2000);
        mProducts.add(p);
        mProducts.add(p1);
        mProducts.add(p2);
        mProducts.add(p3);
        mProducts.add(p4);
        mAdapter.notifyDataSetChanged();

    }

    private void setUpRecyclerView() {
        mAdapter = new ProductAdapter(getActivity(), mProducts);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
        mAdapter.setOnItemClick(new ProductAdapter.OnItemListener() {
            @Override
            public void onItemClick(int position) {
                ProductDetailActivity_.intent(getActivity()).mPosition(position).start();
            }
        });
    }
}
