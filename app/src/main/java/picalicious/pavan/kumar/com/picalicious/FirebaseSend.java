package picalicious.pavan.kumar.com.picalicious;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FirebaseSend extends AppCompatActivity {
    public String pid;
    public String[] labelsArray;
    public String message2 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_send);
        Intent intent = getIntent();
        String message = intent.getStringExtra(SmartSearchActivity.FIREBASE_STRING);

        message = message.replace("This picture contains :","");
        labelsArray = message.trim().split("\\s*,\\s*");
        pid = labelsArray[5];

        pid = pid.substring(0,7);

        List<String> labelslist = new ArrayList<String>(Arrays.asList( labelsArray ));
        labelslist.remove(5);


        /*
        //List<String> toplabels = new ArrayList<String>(labelslist.subList(0,5));

        for (int i=0;i<5;i++){

            message2=message2+toplabels.get(i)+"\n";
        }
        */
        //Log.i("firebase_textview", labelslist.toString());
        Log.i("pid", pid);

        message2= TextUtils.join(", ", labelslist);
        TextView textView = findViewById(R.id.firebase_string);
        textView.setText(labelslist.toString());
    }
}
