package com.jeramyadams.aiscreenreader;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;
import android.os.Handler;
import android.os.Looper;

public class AIScreenReaderService extends AccessibilityService {

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        // This will pop up on your screen the moment the service binds
        showToast("AIScreenReader: CONNECTED!");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // This will trigger a popup when the screen changes
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            showToast("AIScreenReader: Screen changed!");
        }
    }

    @Override
    public void onInterrupt() {
        showToast("AIScreenReader: Interrupted");
    }

    // Helper to ensure Toasts run on the main UI thread, preventing crashes
    private void showToast(String msg) {
        new Handler(Looper.getMainLooper()).post(() -> 
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show()
        );
    }
}
