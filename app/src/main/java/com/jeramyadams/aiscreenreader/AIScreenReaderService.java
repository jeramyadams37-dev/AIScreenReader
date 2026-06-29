package com.jeramyadams.aiscreenreader;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

public class AIScreenReaderService extends AccessibilityService {
    
    @Override
    protected void onServiceConnected() {
        try {
            super.onServiceConnected();
            // Literally doing nothing. Just trying to survive the connection.
        } catch (Throwable t) {
            // Swallow any errors to prevent the "keeps stopping" dialog
        }
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        try {
            // Doing nothing with the events yet
        } catch (Throwable t) {
        }
    }

    @Override
    public void onInterrupt() {}
}
