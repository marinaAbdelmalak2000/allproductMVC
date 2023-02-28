package com.example.productsretrofit.favourite.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productsretrofit.model.Product;
import com.example.productsretrofit.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouritAdapter  extends RecyclerView.Adapter<FavouritAdapter.ViewHolder>{

    private final Context context;
    private List<Product> values;
    private static final String TAG="RecyclerView";

    OnFavouriteClickLisener onFavouriteClickLisener;

    public FavouritAdapter(Context context, List<Product> values, OnFavouriteClickLisener _onFavouriteClickLisener) {
        this.context = context;
        this.values = values;
        this.onFavouriteClickLisener=_onFavouriteClickLisener;
    }

    @NonNull
    @Override
    public FavouritAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(recyclerView.getContext());
        View v=inflater.inflate(R.layout.row_layoutfavorite,recyclerView,false);
        FavouritAdapter.ViewHolder viewHolder=new FavouritAdapter.ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder: new row createeeeeeeeee");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText(values.get(position).getTitle());
        holder.txtDescription.setText(values.get(position).getDescription());
        Picasso.get().load(values.get(position).getThumbnail()).into(holder.imageView);
        holder.ratingBar.setRating((float) values.get(position).getRating());
        holder.txtPrice.setText(String.valueOf(values.get(position).getPrice()));
        holder.txtBrand.setText(values.get(position).getBrand());
        holder.btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavouriteClickLisener.onClick(values.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    public void setData(List<Product> values){
        this.values = values;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle;
        public TextView txtDescription,txtPrice,txtBrand;
        public ImageView imageView;
        public RatingBar ratingBar;

        public ConstraintLayout constraintLayout;
        public View layout;
        Button btnFav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            txtTitle=itemView.findViewById(R.id.textViewTitle);
            txtDescription=itemView.findViewById(R.id.textViewDescription);
            txtBrand=itemView.findViewById(R.id.textViewBrand);
            txtPrice=itemView.findViewById(R.id.textViewPrice);
            imageView=itemView.findViewById(R.id.imageView);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            btnFav=itemView.findViewById(R.id.buttonFav);
            // constraintLayout=itemView.findViewById(R.id.row);

        }
    }
}
