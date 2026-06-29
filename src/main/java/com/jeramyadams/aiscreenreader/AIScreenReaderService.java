package com.jeramyadams.aiscreenreader;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

public class AIScreenReaderService extends AccessibilityService {
    
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        announce("Service connected and ready.");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            CharSequence text = event.getText().toString();
            if (text != null && text.length() > 5 && !text.toString().contains("Recent apps")) {
                announce("New window: " + text);
            }
        }
    }

    private void announce(String text) {
        AccessibilityEvent event = AccessibilityEvent.obtain(AccessibilityEvent.TYPE_ANNOUNCEMENT);
        event.setClassName(getClass().getName());
        event.setPackageName(getPackageName());
        event.getText().add(text);
        // Corrected call using super
        super.sendAccessibilityEvent(event);
    }

    @Override
    public void onInterrupt() {}
}
