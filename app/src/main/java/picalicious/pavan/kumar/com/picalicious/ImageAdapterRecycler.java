package picalicious.pavan.kumar.com.picalicious;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.File;
import java.util.ArrayList;


public class ImageAdapterRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> f = new ArrayList<String>();


    public ImageAdapterRecycler(ArrayList<String> f) {

        this.f = f;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.myphotos_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Bitmap myBitmap = BitmapFactory.decodeFile(f.get(position));
        ((ViewHolder) holder).thumbImage.setImageBitmap(myBitmap);

    }

    @Override
    public int getItemCount() {
        return f.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbImage;

        ViewHolder(final View itemView) {
            super(itemView);
            thumbImage = itemView.findViewById(R.id.thumbImage);


        }


    }

}

