package com.jeramyadams.aiscreenreader;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.os.Handler;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

public class AIScreenReaderService extends AccessibilityService {
    private Handler handler = new Handler();
    private Runnable heartbeat = new Runnable() {
        @Override
        public void run() {
            logToUI("[HEARTBEAT] Still alive: " + new Date().toString());
            handler.postDelayed(this, 5000); // Logs every 5 seconds
        }
    };

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        logToUI("[SYSTEM] Service Connected!");
        handler.post(heartbeat);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        logToUI("[EVENT] Type: " + event.getEventType() + " Source: " + event.getPackageName());
    }

    @Override
    public void onInterrupt() {}

    private void logToUI(String message) {
        try {
            File logFile = new File(getFilesDir(), "screen_log.txt");
            PrintWriter pw = new PrintWriter(new FileWriter(logFile, true));
            pw.println(message);
            pw.close();
        } catch (Exception e) {}
    }
}
