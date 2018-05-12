package picalicious.pavan.kumar.com.picalicious;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class HomeScreenActivity extends Activity {
    private LinearLayout llCamera, llMyPhotos,llSmartSearch,llMyDiary;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_CODE = 200;
    private Bitmap photo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        llCamera = (LinearLayout) findViewById(R.id.llCamera);
        llMyPhotos = (LinearLayout) findViewById(R.id.llMyPhotos);
        llSmartSearch=(LinearLayout)findViewById(R.id.llSmartSearch);
        llMyDiary=(LinearLayout)findViewById(R.id.llMyDiary);
        Button button = (Button) findViewById(R.id.button);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);
        llCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, AddPhotoActivity.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
        llMyPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, MyPhotosActivity.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        llMyDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, MyDiaryActivity.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        llSmartSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, SmartSearchActivity.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getApplicationContext().checkSelfPermission(Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                MY_CAMERA_PERMISSION_CODE);
                    } else {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                }
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }else if(requestCode==STORAGE_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage permission granted", Toast.LENGTH_LONG).show();
                saveToSD(photo);
            } else {
                Toast.makeText(this, "Storage permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
           photo= (Bitmap) data.getExtras().get("data");
           // ivCamera.setImageBitmap(photo);
            // saveToInternalStorage(photo);
            invalidateOptionsMenu();
           // saveToSD(photo);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            STORAGE_CODE);
                } else {
                    saveToSD(photo);
                }
            }


            Intent i =new Intent(HomeScreenActivity.this, AddPhotoActivity.class);
            i.putExtra("photopath",photo);
            startActivity(i);
            //startActivity(new Intent(HomeScreenActivity.this, AddPhotoActivity.class));

        }

    }


    public void saveToSD(Bitmap imageToSave) {
        File storagePath = new File(Environment.getExternalStorageDirectory() + "/Picaliciouss/");
        storagePath.mkdirs();

        File myImage = new File(storagePath, Long.toString(System.currentTimeMillis()) + ".jpg");

        try {
            FileOutputStream out = new FileOutputStream(myImage);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 80, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
