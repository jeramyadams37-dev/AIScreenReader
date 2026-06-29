package com.jeramyadams.aiscreenreader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ScrollView;
import android.widget.Button;
import android.widget.LinearLayout;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class MainActivity extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        Button refreshButton = new Button(this);
        refreshButton.setText("REFRESH LOG");
        refreshButton.setOnClickListener(v -> loadLogs());

        ScrollView scrollView = new ScrollView(this);
        textView = new TextView(this);
        textView.setTextSize(14);
        
        scrollView.addView(textView);
        layout.addView(refreshButton);
        layout.addView(scrollView);
        
        setContentView(layout);
        loadLogs();
    }

    private void loadLogs() {
        StringBuilder log = new StringBuilder("--- AIScreenReader Live Console ---\n\n");
        try {
            File logFile = new File(getFilesDir(), "screen_log.txt");
            if (logFile.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(logFile));
                String line;
                while ((line = br.readLine()) != null) {
                    log.append(line).append("\n");
                }
                br.close();
            } else {
                log.append("Waiting for screen text...\nEnsure the service is ON in Settings.");
            }
        } catch (Exception e) {
            log.append("Error reading logs.");
        }
        textView.setText(log.toString());
    }
}
