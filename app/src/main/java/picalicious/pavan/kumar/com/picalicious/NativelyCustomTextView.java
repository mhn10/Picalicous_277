package picalicious.pavan.kumar.com.picalicious;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;


/**
 * Created by Seeinside on 18/09/17.
 */

public class NativelyCustomTextView extends android.support.v7.widget.AppCompatTextView {

    public NativelyCustomTextView(Context context) {
        super(context);
        init();

    }

    public NativelyCustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NativelyCustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(StringUtils.getToUpper(text+""), type);
        init();

    }

    @Override
    public void setTypeface(Typeface tf, int style) {
//
        super.setTypeface(tf, style);

    }


    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Pacifico.ttf");
        setTypeface(tf ,0);

    }
}