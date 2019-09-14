package com.malaurydtr.rsaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ClipboardManager _clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        final EditText InputText = findViewById(R.id.inputText);
        final EditText NText = findViewById(R.id.NText);
        final EditText DText = findViewById(R.id.DText);
        final EditText EText = findViewById(R.id.EText);
        final TextView outText = findViewById(R.id.Output);

        final Switch SwDecode = findViewById(R.id.EncodeSw);

        final Button tButton = findViewById(R.id.transformButton);

        tButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                //Find the currently focused view, so we can grab the correct window token from it.
                View view = getCurrentFocus();

                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                if(SwDecode.isChecked()){
                    outText.setText(DecodeJNI(InputText.getText().toString(),Integer.parseInt(NText.getText().toString()),Integer.parseInt(DText.getText().toString())));
                }else{
                    outText.setText(EncodeJNI(InputText.getText().toString(),Integer.parseInt(NText.getText().toString()),Integer.parseInt(EText.getText().toString())));
                }

            }
        });

        final Button copyButton = findViewById(R.id.CopyButton);
        copyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                _clipboard.setPrimaryClip(ClipData.newPlainText(null,outText.getText().toString()));
            }
        });

        final Button createButton = findViewById(R.id.CreateKeysButton);
        createButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                outText.setText(CreateKeys());
            }
        });
    }


    public native String EncodeJNI(String in,long n,long e);
    public native String DecodeJNI(String in,long n, long d);
    public native String CreateKeys();
}
