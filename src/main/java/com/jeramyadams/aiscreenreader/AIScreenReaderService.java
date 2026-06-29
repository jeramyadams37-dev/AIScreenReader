package com.jeramyadams.aiscreenreader;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

public class AIScreenReaderService extends AccessibilityService {
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {}
    @Override
    public void onInterrupt() {}
}
