package cn.edu.nuc.texteditordemo_94240;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvText = null;
    private Button btnRed = null;
    private Button btnGreen = null;
    private Button btnBlue = null;

    private Button btnBigger = null;
    private Button btnSmaller = null;

    private Button btnBold = null;
    private Button btnItalic = null;
    private Button btnNormal = null;

    private Button btnClear = null;
    private EditText etText = null;

    private SizeListener sizeListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = findViewById(R.id.tvText);
        btnRed = findViewById(R.id.btnRed);
        btnGreen = findViewById(R.id.btnGreen);
        btnBlue = findViewById(R.id.btnBlue);
        btnBigger = findViewById(R.id.btnBigger);
        btnSmaller = findViewById(R.id.btnSmaller);
        btnBold = findViewById(R.id.btnBold);
        btnItalic = findViewById(R.id.btnItalic);
        btnNormal = findViewById(R.id.btnNormal);
        btnClear = findViewById(R.id.btnClear);
        etText = findViewById(R.id.etText);

        final float size = tvText.getTextSize();

        sizeListener = new SizeListener(tvText);

        ColorListener colorListener = new ColorListener();

        btnRed.setOnClickListener(colorListener);
        btnGreen.setOnClickListener(colorListener);
        btnBlue.setOnClickListener(colorListener);

        btnBigger.setOnClickListener(sizeListener);
        btnSmaller.setOnClickListener(sizeListener);

        btnBold.setOnClickListener(this);
        btnItalic.setOnClickListener(this);
        btnNormal.setOnClickListener(this);

        etText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvText.setText(etText.getText().toString());
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvText.setText("测试文字，测试文字");
                tvText.setTextColor(Color.BLACK);
                tvText.setTextSize(TypedValue.COMPLEX_UNIT_PX,size);
                tvText.setTypeface(Typeface.MONOSPACE,Typeface.NORMAL);
            }
        });

    }

    @Override
    public void onClick(View v){
        Typeface typeface = tvText.getTypeface();
        switch(v.getId()){
            case R.id.btnBold:
                if(typeface.isItalic()) {
                    tvText.setTypeface(Typeface.MONOSPACE,Typeface.BOLD_ITALIC);
                }else {
                    tvText.setTypeface(Typeface.MONOSPACE,Typeface.BOLD);
                }
                break;
            case R.id.btnItalic:
                if(typeface.isBold()){
                    tvText.setTypeface(Typeface.MONOSPACE,Typeface.BOLD_ITALIC);
                }else{
                    tvText.setTypeface(Typeface.MONOSPACE,Typeface.ITALIC);
                }
                break;
            case R.id.btnNormal:
                tvText.setTypeface(Typeface.MONOSPACE,Typeface.NORMAL);
                break;
            default:
                break;
        }
    }

    private class ColorListener implements View.OnClickListener{

        public ColorListener() {

        }

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnRed:
                    tvText.setTextColor(Color.RED);
                    break;
                case R.id.btnGreen:
                    tvText.setTextColor(Color.GREEN);
                    break;
                case R.id.btnBlue:
                    tvText.setTextColor(Color.BLUE);
                    break;
                default:
                    break;
            }
        }
    }

}
