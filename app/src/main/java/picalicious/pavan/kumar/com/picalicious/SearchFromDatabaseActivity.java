package picalicious.pavan.kumar.com.picalicious;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SearchFromDatabaseActivity extends Activity {
    private EditText etSearch;
    private RecyclerView recyclerview;
    private Button btSubmit;
    SearchAdapter searchAdapter;
    private TextView tvpic;
    public Uri uri;

    private List<SearchModel> searchModelList = new ArrayList<>();
    List<String> db_labels;
    private static final int MAX_DIMENSION = 1200;
    private static final int MY_STORAGE_PERMISSION_CODE = 100;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfromdb);
        PhotosDatabase db = PhotosDatabase.getDatabase(getApplicationContext());

        etSearch=(EditText)findViewById(R.id.etSearch);
        btSubmit=(Button)findViewById(R.id.btSubmit);
        tvpic=(TextView)findViewById(R.id.tvpic);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText= etSearch.getText().toString().trim();
                searchModelList.clear();
                Log.d("searchText","searchText"+searchText);
                db_labels = new ArrayList<String>(db.photosDAO().getPhotoswithLabels(searchText));
                 Log.d("db_labels","db_labels"+db_labels);
                if (db_labels.isEmpty()) {

                    tvpic.setText("Oops!Looks like you forgot to add it to your database");

                } else {
                    viewstodisplay();
                }
            }
        });

    }

    private void viewstodisplay() {
        tvpic.setText("");
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setNestedScrollingEnabled(false);

        searchAdapter = new SearchAdapter(searchModelList);
        recyclerview.setAdapter(searchAdapter);

        addData();



    }

    private void addData() {


        for(int i=0;i<db_labels.size();i++){
            SearchModel searchModel =new SearchModel();

            uri = Uri.parse(db_labels.get(i));
            Log.d("uri","uri:::::::::"+uri);

            if (uri != null) {
                try {
                    Bitmap bitmap =
                            scaleBitmapDown(
                                    MediaStore.Images.Media.getBitmap(getContentResolver(), uri),
                                    MAX_DIMENSION);


                    //searchModel.setImage(R.drawable.diary);.setImageBitmap(bitmap);
                    Log.d("bitmap","bitmap::::"+bitmap);
                    searchModel.setImage(bitmap);
                }
                catch (IOException e) {
                    Log.d("Image picking failed because ", "Image picking failed because " + e.getMessage());
                    Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
                }

            } else {
                Log.d("Image picker gave us a null image.", "Image picker gave us a null image.");
                Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
            }

            searchModelList.add(searchModel);
        }

        searchAdapter.notifyDataSetChanged();
    }


    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

}
