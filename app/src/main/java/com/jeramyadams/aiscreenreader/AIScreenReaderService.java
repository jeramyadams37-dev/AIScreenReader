package com.jeramyadams.aiscreenreader;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.speech.tts.TextToSpeech;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

public class AIScreenReaderService extends AccessibilityService implements TextToSpeech.OnInitListener {
    
    private TextToSpeech tts;
    private boolean isTtsReady = false;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            tts = new TextToSpeech(this, this);
        } catch (Exception e) {}
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.US);
            isTtsReady = true;
            speak("Audio system initialized.");
        }
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        logToUI("\n[SYSTEM] Service Bound and Active!");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        List<CharSequence> textList = event.getText();
        if (textList != null && !textList.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (CharSequence text : textList) {
                sb.append(text).append(" ");
            }
            String capturedText = sb.toString().trim();
            logToUI("[CAPTURED] " + capturedText);
            
            // Smarter not harder: Ignore useless system spam
            if (!capturedText.equals("Recent apps") && !capturedText.equals("AIScreenReader") && !capturedText.equals("App info")) {
                speak(capturedText);
            }
        }
    }

    private void speak(String text) {
        if (isTtsReady && tts != null) {
            // QUEUE_ADD means it will read everything in order without cutting itself off
            tts.speak(text, TextToSpeech.QUEUE_ADD, null, null);
        }
    }

    @Override
    public void onInterrupt() {}

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    private void logToUI(String message) {
        try {
            File logFile = new File(getFilesDir(), "screen_log.txt");
            PrintWriter pw = new PrintWriter(new FileWriter(logFile, true));
            pw.println(message);
            pw.close();
        } catch (Exception e) {}
    }
}
