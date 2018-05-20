package picalicious.pavan.kumar.com.picalicious;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public class MyPhotosActivity extends Activity implements OnItemClickListener {

    ArrayList<String> f = new ArrayList<String>();// list of file paths
    File[] listFile;
   // private ImageAdapter imageAdapter;
    private ImageAdapterRecycler imageAdapterRecycler;
    private ImageView ivBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myphotos);
        ivBack=(ImageView)findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.exit, R.anim.exit);

            }
        });

        getFromSdcard();
        RecyclerView imagegrid = (RecyclerView) findViewById(R.id.PhoneImageGrid);
        imagegrid.setHasFixedSize(true);
        int numberOfColumns = 2;
        imagegrid.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        imageAdapterRecycler = new ImageAdapterRecycler(f);
        imagegrid.setAdapter(imageAdapterRecycler);
       // imageAdapterRecycler.setOnItemClickListener(this);


    }

    public void getFromSdcard()
    {
        File file= new File(android.os.Environment.getExternalStorageDirectory(),"Picaliciouss");

        if (file.isDirectory())
        {
            listFile = file.listFiles();


            for (int i = 0; i < listFile.length; i++)
            {

                f.add(listFile[i].getAbsolutePath());
                Log.d("jjdjd","msmsdnmds :"+f);

            }
        }
    }

    @Override
    public void onItemClick(String message, int position) {
       imageAdapterRecycler.getItemId(position);
    }

    public class ImageAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public ImageAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return f.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(
                        R.layout.myphotos_item, null);
                holder.imageview = (ImageView) convertView.findViewById(R.id.thumbImage);

                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }


            Bitmap myBitmap = BitmapFactory.decodeFile(f.get(position));
            holder.imageview.setImageBitmap(myBitmap);
            return convertView;
        }
    }
    class ViewHolder {
        ImageView imageview;


    }
}
