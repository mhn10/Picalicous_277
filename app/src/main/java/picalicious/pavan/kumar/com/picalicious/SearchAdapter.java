
package picalicious.pavan.kumar.com.picalicious;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Viewholder> {
    private List<SearchModel> searchModelList;

    public SearchAdapter(List<SearchModel> searchModelList) {
        this.searchModelList = searchModelList;

    }

    @NonNull
    @Override
    public SearchAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.Viewholder holder, int position) {
        SearchModel searchModel = searchModelList.get(position);
        holder.ivDisplay.setImageBitmap(searchModel.getImage());
    }

    @Override
    public int getItemCount() {
        return searchModelList.size();
    }

    //        @Override
//    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
//
//    }
//
//    @Override
//    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return searchModelList.size();
//    }
//
    public class Viewholder extends RecyclerView.ViewHolder {


        public ImageView ivDisplay;

        public Viewholder(View itemView) {
            super(itemView);

            ivDisplay = (ImageView) itemView.findViewById(R.id.ivDisplay);


        }
    }

}
