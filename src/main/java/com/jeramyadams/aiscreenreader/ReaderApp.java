package com.jeramyadams.aiscreenreader;

import android.app.Application;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ReaderApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            try {
                File logFile = new File(getFilesDir(), "internal_crash.txt");
                PrintWriter pw = new PrintWriter(new FileWriter(logFile, true));
                throwable.printStackTrace(pw);
                pw.close();
            } catch (Exception e) {}
            System.exit(2);
        });
    }
}
