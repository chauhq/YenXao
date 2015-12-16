package com.project.chauhq.yenxao.ui.product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.project.chauhq.yenxao.R;
import com.project.chauhq.yenxao.model.Product;

import java.util.List;

/**
 * @author ChauHQ
 */
public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * this interface define for
     */
    public interface OnItemListener {
        void onItemClick(int position);
    }

    private Context mContext;
    private List<Product> mProducts;
    private DisplayImageOptions mOptions;
    private OnItemListener mListener;
    private ImageLoader mImageLoader;

    public ProductAdapter(Context context, List<Product> products) {
        mContext = context;
        mProducts = products;
        mImageLoader = ImageLoader.getInstance();
        mOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .showImageForEmptyUri(R.drawable.img_yen_xao)
                .showImageOnFail(R.drawable.img_yen_xao)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_list_product, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductViewHolder vh = (ProductViewHolder) holder;
        Product product = mProducts.get(position);
        Log.d("xxxx", product.getUrlImage());
        mImageLoader.displayImage(product.getUrlImage(), vh.mImgPhoto);
        vh.mTvType.setText(product.getType());
        String money = mContext.getString(R.string.product_text_nvd, product.getMoney());
        vh.mTvMoney.setText(money);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgPhoto;
        private TextView mTvType;
        private TextView mTvMoney;

        public ProductViewHolder(View itemView) {
            super(itemView);
            mImgPhoto = (ImageView) itemView.findViewById(R.id.imgPhoto);
            mTvType = (TextView) itemView.findViewById(R.id.tvType);
            mTvMoney = (TextView) itemView.findViewById(R.id.tvMoney);

            mImgPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(getLayoutPosition());
                }
            });
        }
    }

    public void setOnItemClick(OnItemListener listener) {
        mListener = listener;
    }
}
