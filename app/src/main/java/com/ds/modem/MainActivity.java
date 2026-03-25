package com.ds.modem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screen = new TextView(this);
        screen.setTextColor(0xFFFFFFFF);
        screen.setBackgroundColor(0xFF000000);
        screen.setTextSize(16);
        screen.setPadding(20, 40, 20, 20);

        screen.setText("iDIRECT Modem Virtualization\n\nReady...");

        setContentView(screen);
    }
}
