package com.example.mavenirsdkdemo.logger;

import android.os.Build;
import android.text.SpannableStringBuilder;
import android.util.Log;

import com.example.mavenirsdkdemo.config.AppConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {
    private static final String logFileName = "MavSDkDemoLog";
    private static final String TAG = LogUtils.class.getSimpleName();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void v(String tag, String msg) {
        Log.v(tag, msg);
        writeToLogFile("V", tag, msg);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
        writeToLogFile("D", tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
        writeToLogFile("E", tag, msg);
    }

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
        writeToLogFile("I", tag, msg);
    }

    private static void writeToLogFile(String logType, String tag, String msg) {
        File logFolder = AppConfig.getInstance().getLogFolder();
        if (logFolder == null) {
            LogUtils.d(TAG, "logFolder is null");
            return;
        }
        try {
            File logFile = new File(logFolder, logFileName);
            if (!logFile.exists()) {
                createNewLogFile(logFile);
            }
            FileWriter fileWriter = new FileWriter(logFile, true);
            fileWriter.append(formatLogs(logType, tag, msg));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            LogUtils.e(TAG, "error writing log file " + e);
            e.printStackTrace();
        }


    }

    private static void createNewLogFile(File file) throws IOException {
        LogUtils.d(TAG, "creating Log File " + file.createNewFile());
        LogUtils.d(TAG, "starting Logs at " + dateFormat.format(new Date()));
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.append(dumpDeviceInfo());
        fileWriter.flush();
        fileWriter.close();
    }

    private static String formatLogs(String logType, String tag, String msg) {
        Date date = new Date();
        return (dateFormat.format(date) + " " + logType + ": " + tag + " : " + msg + "\n");
    }

    private static String dumpDeviceInfo() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String title = "-----------------Device Info Starts-------------------\n";
        String deviceOS = "Device OS = " + System.getProperty("os.version") + ", " + Build.VERSION.RELEASE + "\n"; // OS version
        String device = "Device = " + Build.DEVICE + "\n";               // Device
        String deviceModel = "Device Model = " + Build.MODEL + "\n";                      // Model
        String deviceProduct = "Device Product = " + Build.PRODUCT + "\n";         // Product
        String deviceBrand = "Device Brand + Manufacturer = " + Build.BRAND + ", " + Build.MANUFACTURER + "\n";         // Brand
        String end = "-----------------Device Info Ends-------------------\n";
        return spannableStringBuilder.append(title)
                .append(deviceBrand)
                .append(device)
                .append(deviceProduct)
                .append(deviceModel)
                .append(deviceOS)
                .append(end).toString();

    }

    public static void dumpCrashReport(Thread t, Throwable e){
        File logFolder = AppConfig.getInstance().getLogFolder();
        if (logFolder == null) {
            LogUtils.d(TAG, "logFolder is null");
            return;
        }
        try {
            File logFile = new File(logFolder, logFileName);
            if (!logFile.exists()) {
                createNewLogFile(logFile);
            }
            FileWriter fileWriter = new FileWriter(logFile, true);
            fileWriter.append(getCrashReport(t, e));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ex) {
            LogUtils.e(TAG, "error dumping crash log " + ex);
            e.printStackTrace();
        }
    }

    private static String getCrashReport(Thread t, Throwable e){
        final Writer stringBuffSync = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringBuffSync);
        e.printStackTrace(printWriter);
        String stacktrace = stringBuffSync.toString();
        printWriter.close();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String title = "-----------------App Crashed started-------------------\n";
        String threadDesc = "ThreadId == " + t.getId() + " ThreadName == : "+ t.getName() + "\n";
        String end = "-----------------App Crashed ends-------------------\n";
        return spannableStringBuilder.append(title)
                .append(dumpDeviceInfo())
                .append(threadDesc)
                .append(stacktrace)
                .append(end).toString();
    }


}
