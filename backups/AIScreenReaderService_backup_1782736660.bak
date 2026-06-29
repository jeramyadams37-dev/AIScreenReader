package com.jeramyadams.aiscreenreader;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.speech.tts.TextToSpeech;
import java.util.Locale;

public class AIScreenReaderService extends AccessibilityService implements TextToSpeech.OnInitListener {
    
    private TextToSpeech tts;
    private boolean isTtsReady = false;

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the text-to-speech engine
        tts = new TextToSpeech(this, this);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.US);
            isTtsReady = true;
            speak("Diagnostic audio system online.");
        }
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        // This fires when you toggle it ON in settings
        speak("Service bound successfully.");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Trigger speech when you change screens/apps
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            speak("Screen changed.");
        }
    }

    @Override
    public void onInterrupt() {
        // Required method, doing nothing for now
    }

    private void speak(String text) {
        if (isTtsReady && tts != null) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
