package cn.edu.nuc.texteditordemo_94240;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class SizeListener implements View.OnClickListener {
    private TextView tvText = null;

    public SizeListener(TextView tvText) {
        this.tvText = tvText;
    }

    @Override
    public void onClick(View v) {
        tvText.findViewById(R.id.tvText);
        float size = tvText.getTextSize();
        switch(v.getId()){
            case R.id.btnBigger:
                size += 1;
                break;
            case R.id.btnSmaller:
                size -= 1;
                break;
            default:
                break;
        }tvText.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }
}
