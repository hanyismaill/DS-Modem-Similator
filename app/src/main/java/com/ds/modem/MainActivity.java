package com.ds.modem;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView screen;
    EditText input;

    int stage = 0;
    String username = "admin";

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screen = new TextView(this);
        input = new EditText(this);

        screen.setTextSize(14);
        input.setSingleLine(true);
        input.setImeOptions(EditorInfo.IME_ACTION_DONE);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(screen);
        layout.addView(input);

        setContentView(layout);

        screen.setText("Username: ");

        input.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

                String text = input.getText().toString().trim();
                input.setText("");

                if (!text.isEmpty()) {
                    processInput(text);
                }

                return true;
            }
            return false;
        });
    }

    private void processInput(String text) {

        screen.append(text + "\n");

        if (stage == 0) {
            screen.append("Password: ");
            stage = 1;

        } else if (stage == 1) {
            screen.append("********\n\n");
            stage = 2;
            printHeader();

        } else if (stage == 2) {
            handleCommand(text);
        }
    }

    private void printHeader() {
        int session = 10000 + random.nextInt(90000);

        screen.append("[RMT:" + session + "] admin@telnet:127.0.0.1;4494\n");
        screen.append("> ");
    }

    private void handleCommand(String cmd) {

        cmd = cmd.toLowerCase();

        if (cmd.equals("rx power")) {
            screen.append("Rx Power: -66.830002\n\n");

        } else if (cmd.equals("rx snr")) {
            screen.append("Rx SNR: 11.930000\n\n");

        } else if (cmd.equals("rx freq")) {
            screen.append("Rx Frequency = 1100.0000 MHz\n\n");

        } else if (cmd.equals("tx freq")) {
            screen.append("Tx Frequency = 1800.0000 MHz\n\n");

        } else {
            screen.append("Unknown Command: '" + cmd + "'\n\n");
        }

        printHeader(); // IMPORTANT: repeat header every time
    }
}
