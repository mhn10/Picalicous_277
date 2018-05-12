package picalicious.pavan.kumar.com.picalicious;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Fade;
import android.view.View;
import android.widget.LinearLayout;

public class AddPhotoActivity extends Activity {
    private LinearLayout llMyPhotos,llMyDiary;

    Bitmap photo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_photo);
        Intent intent = getIntent();
         photo = (Bitmap) intent.getParcelableExtra("photopath");


       // setupWindowAnimations();
        llMyDiary=(LinearLayout)findViewById(R.id.llMyDiary);
        llMyDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddPhotoActivity.this,AddtoDiary.class);
                i.putExtra("photopath",photo);

                startActivity(i);
//                finish();
//                overridePendingTransition(R.anim.exit, R.anim.exit);
            }
        });
    }

    private void setupWindowAnimations() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.setDuration(1000);
            getWindow().setEnterTransition(fade);
        }
    }
}
