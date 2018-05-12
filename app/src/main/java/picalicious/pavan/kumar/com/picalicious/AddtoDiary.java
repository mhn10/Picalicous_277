package picalicious.pavan.kumar.com.picalicious;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

public class AddtoDiary extends Activity {
private Bitmap photo;
private ImageView ivPhoto;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtodiary);
        Intent intent = getIntent();
        photo = (Bitmap) intent.getParcelableExtra("photopath");

        ivPhoto=(ImageView)findViewById(R.id.ivPhoto);
        ivPhoto.setImageBitmap(photo);
    }
}
