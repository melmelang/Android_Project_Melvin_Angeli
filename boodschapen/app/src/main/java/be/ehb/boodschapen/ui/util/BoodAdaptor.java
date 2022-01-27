package be.ehb.boodschapen.ui.util;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.ehb.boodschapen.R;
import be.ehb.boodschapen.model.Bood;

public class BoodAdaptor extends RecyclerView.Adapter<BoodAdaptor.BoodHolder> {

    class BoodHolder extends RecyclerView.ViewHolder{
        final TextView TvTitle, TvDescription, TvCreateDate, TvBoodschappenDate;
        final CardView card;

        public BoodHolder(@NonNull View itemView) {
            super(itemView);
            TvTitle = itemView.findViewById(R.id.tv_Title);
            TvDescription = itemView.findViewById(R.id.tv_Description);
            TvCreateDate = itemView.findViewById(R.id.tv_CreateDate);
            TvBoodschappenDate = itemView.findViewById(R.id.tv_MakeBooschappenDate);
            card = itemView.findViewById(R.id.card_winkard);
            card.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Bood boodToGive = data.get(position);

                Bundle dataToGive = new Bundle();
                dataToGive.putSerializable("boodschapItem", boodToGive);

                Navigation.findNavController(itemView).navigate(R.id.action_homeFragment_to_detailFragment, dataToGive);
            });

        }
    }

    List<Bood> data;

    public BoodAdaptor(List<Bood> data){
        this.data = data;
    }

    public void reloadBood(List<Bood> newBood){
        this.data = newBood;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        View r = LayoutInflater.from(mContext).inflate(R.layout.winkard, parent, false);

        return new BoodHolder(r);
    }

    @Override
    public void onBindViewHolder(@NonNull BoodHolder holder, int position) {
        Bood thisBood = data.get(position);

        holder.TvTitle.setText(thisBood.getTitle());
        holder.TvCreateDate.setText(thisBood.getCreatingDate().toString());
        holder.TvBoodschappenDate.setText(thisBood.getMakeBoodschappenDate().toString());
        holder.TvDescription.setText(thisBood.getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
