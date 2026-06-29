package com.jeramyadams.aiscreenreader;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class AIScreenReaderService extends AccessibilityService {
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        logToUI("\n[SYSTEM] Service Bound and Active!");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Extract text from the current screen event
        List<CharSequence> textList = event.getText();
        if (textList != null && !textList.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (CharSequence text : textList) {
                sb.append(text).append(" ");
            }
            logToUI("[CAPTURED] " + sb.toString().trim());
        }
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
