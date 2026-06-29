package com.jeramyadams.aiscreenreader;

import android.app.Application;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

public class ReaderApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        
        // The Death Note: Catches any fatal error and writes it to a text file
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            try {
                File dir = getExternalFilesDir(null);
                if (dir != null) {
                    File logFile = new File(dir, "crash_log.txt");
                    PrintWriter pw = new PrintWriter(new FileWriter(logFile, true));
                    pw.println("--- CRASH REPORT: " + new Date().toString() + " ---");
                    throwable.printStackTrace(pw);
                    pw.flush();
                    pw.close();
                }
            } catch (Exception e) {
                // Silent fail if we can't write the log
            }
            // Let the system kill the app after we secure the log
            System.exit(2);
        });
    }
}
