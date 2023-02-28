package com.example.productsretrofit.allProduct.view;

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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private final Context context;
    private List<Product> values;


    private static final String TAG="RecyclerView";

    com.example.productsretrofit.allProduct.view.OnProductClickLesener OnProductClickLesener;

    public MyAdapter(Context context, List<Product> values, com.example.productsretrofit.allProduct.view.OnProductClickLesener _onClickLisener) {
        this.context = context;
        this.values = values;
        this.OnProductClickLesener= _onClickLisener;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(recyclerView.getContext());
        View v=inflater.inflate(R.layout.row_layout,recyclerView,false);
        ViewHolder viewHolder=new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder: new row createeeeeeeeee");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.txtTitle.setText(values.get(position).getTitle());
        holder.txtDescription.setText(values.get(position).getDescription());
        Picasso.get().load(values.get(position).getThumbnail()).into(holder.imageView);
        holder.ratingBar.setRating((float) values.get(position).getRating());
        holder.txtPrice.setText(String.valueOf(values.get(position).getPrice()));
        holder.txtBrand.setText(values.get(position).getBrand());
        holder.btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnProductClickLesener.onClick(values.get(position));
              //  holder.btnFav.setEnabled(false);
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
