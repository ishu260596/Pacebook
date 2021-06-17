package com.ishwar_arcore.pacebook.views.marketplace;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ishwar_arcore.pacebook.R;

public class MarketViewHolder extends RecyclerView.ViewHolder {

    private ImageView mIvPhoto;
    private TextView mTextPrice;

    public MarketViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mIvPhoto = itemView.findViewById(R.id.rvlipstic);
        mTextPrice = itemView.findViewById(R.id.tvprice);

    }

    public void setData(MarketPlaceModel marketPlaceModel) {
        mIvPhoto.setImageResource(marketPlaceModel.getImage());
        mTextPrice.setText(marketPlaceModel.getPrice());
    }
}
