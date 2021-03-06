package picalicious.pavan.kumar.com.picalicious;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FirebaseSend extends AppCompatActivity {
    public String pid;
    public Photos photo;
    private static final int MAX_DIMENSION = 1200;


    private static final String TAG = FirebaseSend.class.getSimpleName();
    public String[] labelsArray;
    public String message2 = "";
    public Uri uri;
    private ImageView mMainImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_send);

        //db initialize
        PhotosDatabase db = PhotosDatabase.getDatabase(getApplicationContext());



        // get data and initilize it in proper form for Room storage
        Intent intent = getIntent();
        String message = intent.getStringExtra(SmartSearchActivity.FIREBASE_STRING);
        String datapath = intent.getStringExtra(SmartSearchActivity.FIREBASE_DATAPATH);

        Log.i("firebase_message", message);
        Log.i("firebase_datapath", datapath);
        try {
            photo = new Photos(datapath, message);
            //db
            // photo.setFilepath(datapath);
            // photo.setLabels(message);
            db.photosDAO().insert(photo);
            Toast.makeText(this, R.string.image_upload, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "Image picking failed because " + e.getMessage());
            Toast.makeText(this, R.string.same_image, Toast.LENGTH_LONG).show();
            Intent transfer = new Intent(this, HomeScreenActivity.class);
            startActivity(transfer);
        }


        uri = Uri.parse(datapath);
/*        //remove string
        message = message.replace("This picture contains :","");
        labelsArray = message.trim().split("\\s*,\\s*");
        pid = labelsArray[5];
        //store picture id as key to firebase
        pid = pid.substring(0,7);

        List<String> labelslist = new ArrayList<String>(Arrays.asList( labelsArray ));
        labelslist.remove(5);*/

       //String s = "dog";

       // List<String> db_labels = new ArrayList<String>(db.photosDAO().getPhotoswithLabels(s));
        /*
        //List<String> toplabels = new ArrayList<String>(labelslist.subList(0,5));

        for (int i=0;i<5;i++){

            message2=message2+toplabels.get(i)+"\n";
        }
        */
        //Log.i("firebase_textview", labelslist.toString());
        //Log.i("pid", pid);

        //message2= TextUtils.join(", ", db_labels);
       // TextView textView = findViewById(R.id.firebase_string);
       // textView.setText(db_labels.toString());

        mMainImage = findViewById(R.id.imageView_firebase);

        if (uri != null) {
            try {
                Bitmap bitmap =
                        scaleBitmapDown(
                                MediaStore.Images.Media.getBitmap(getContentResolver(), uri),
                                MAX_DIMENSION);


                mMainImage.setImageBitmap(bitmap);

            }
            catch (IOException e) {
                Log.d(TAG, "Image picking failed because " + e.getMessage());
                Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
            }

        } else {
            Log.d(TAG, "Image picker gave us a null image.");
            Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
        }
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
