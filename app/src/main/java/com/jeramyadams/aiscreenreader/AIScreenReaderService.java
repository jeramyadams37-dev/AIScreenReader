package com.jeramyadams.aiscreenreader;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.util.Log;

public class AIScreenReaderService extends AccessibilityService {
    private static final String TAG = "AIScreenReader";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode == null) return;

        // Recursively traverse and capture all visible text nodes
        analyzeNode(rootNode);
        rootNode.recycle();
    }

    private void analyzeNode(AccessibilityNodeInfo node) {
        if (node == null) return;

        if (node.getText() != null) {
            String text = node.getText().toString();
            // Output text directly to logcat for visual inspection
            Log.d(TAG, "Captured Screen Text: " + text);
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            analyzeNode(node.getChild(i));
        }
    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "Service Interrupted");
    }
}
