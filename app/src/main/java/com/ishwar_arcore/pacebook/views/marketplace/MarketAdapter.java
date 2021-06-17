package com.ishwar_arcore.pacebook.views.marketplace;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ishwar_arcore.pacebook.R;

import java.util.ArrayList;

public class MarketAdapter extends RecyclerView.Adapter<MarketViewHolder> {
    private ArrayList<MarketPlaceModel> cardList;

    public MarketAdapter(ArrayList<MarketPlaceModel> cardList, MarketplaceFragment marketplaceFragment) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.marketplace_itemlayout,parent,false);
        return new MarketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {
        MarketPlaceModel marketPlaceModel =cardList.get(position);
        holder.setData(marketPlaceModel);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
