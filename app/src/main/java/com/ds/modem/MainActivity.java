package com.ds.modem;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView screen;
    StringBuilder buffer = new StringBuilder();

    int stage = 0; // 0=username, 1=password, 2=logged in
    String username = "";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screen = new TextView(this);
        screen.setTextSize(18);
        screen.setPadding(20, 40, 20, 20);

        screen.setText(
                "iDIRECT Modem Virtualization\n\n" +
                "Ready...\n\n" +
                "Username: "
        );

        setContentView(screen);

        // Capture typing
        screen.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == android.view.KeyEvent.ACTION_DOWN) {

                char c = (char) event.getUnicodeChar();

                if (keyCode == android.view.KeyEvent.KEYCODE_ENTER) {
                    handleInput();
                    return true;
                }

                if (Character.isLetterOrDigit(c)) {
                    buffer.append(c);

                    if (stage == 1) {
                        // hide password
                        screen.append("*");
                    } else {
                        screen.append(String.valueOf(c));
                    }
                }
            }
            return true;
        });
    }

    void handleInput() {

        if (stage == 0) {
            username = buffer.toString();
            buffer.setLength(0);

            screen.append("\nPassword: ");
            stage = 1;
        }

        else if (stage == 1) {
            password = buffer.toString();
            buffer.setLength(0);

            if (username.equals("admin") && password.equals("admin")) {

                screen.append("\n\nAccess Granted\n>\n");
                stage = 2;

            } else {
                screen.append("\n\nAccess Denied\n\nUsername: ");
                stage = 0;
            }
        }

        else if (stage == 2) {
            // simple command echo
            String cmd = buffer.toString();
            buffer.setLength(0);

            screen.append("\nExecuted: " + cmd + "\n>");
        }
    }
}
