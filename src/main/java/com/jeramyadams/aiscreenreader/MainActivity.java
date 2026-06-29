package com.jeramyadams.aiscreenreader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ScrollView;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ScrollView scrollView = new ScrollView(this);
        TextView textView = new TextView(this);
        textView.setPadding(40, 40, 40, 40);
        textView.setTextSize(14);
        
        StringBuilder log = new StringBuilder("--- AIScreenReader Console ---\n\n");
        try {
            File logFile = new File(getFilesDir(), "internal_crash.txt");
            if (logFile.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(logFile));
                String line;
                while ((line = br.readLine()) != null) {
                    log.append(line).append("\n");
                }
                br.close();
            } else {
                log.append("No crashes recorded yet. Try turning the Accessibility Service ON in settings.");
            }
        } catch (Exception e) {
            log.append("Error reading logs: ").append(e.getMessage());
        }
        
        textView.setText(log.toString());
        scrollView.addView(textView);
        setContentView(scrollView);
    }
}
