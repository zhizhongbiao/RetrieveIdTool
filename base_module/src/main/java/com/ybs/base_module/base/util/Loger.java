package com.ybs.base_module.base.util;

import android.util.Log;

public class Loger {
    private static final boolean DEBUG = true;
    private static final String TAG = "Zzb";
    private static final int THRESHOLD = 2*1024;

    private Loger() {

    }

    private static void handleLog(int level, String msg, String head) {
        StackTraceElement[] stackslist = Thread.currentThread().getStackTrace();
        StackTraceElement tracke = null;

        if (stackslist != null && stackslist.length > 4) {
            tracke = stackslist[4];
        }


        StringBuilder buffer = new StringBuilder(80);

        if (tracke != null) {
            buffer.append('[');
            buffer.append(tracke.getMethodName());
            String fName = tracke.getFileName();
            if (fName == null) {
                buffer.append("(Unknown Source)");
            } else {
                int lineNum = tracke.getLineNumber();

                buffer.append('(');
                buffer.append(fName);
                if (lineNum >= 0) {
                    buffer.append(':');
                    buffer.append(lineNum);
                }
                buffer.append(')');
            }
            buffer.append("]  ");
        }

        if (head != null) {
            buffer.append(head);
        }

        if (msg != null) {
            buffer.append(msg);
        }


        msg = buffer.toString();
        byte[] bytes = msg.getBytes();
        int len =  bytes.length;

        if (len <THRESHOLD) {
            printSubMsg(level, msg);
        } else {

            int count = 0;
            for (int i = 0; i < len; i=(i++)+THRESHOLD) {
                count=Math.min(len-i,THRESHOLD);
                printSubMsg(level,new String(bytes,i,count));
            }

        }




    }

    private static void printSubMsg(int level, String buffer) {
        switch (level) {
            case Log.VERBOSE:
                Log.v(TAG, buffer.toString());
                break;

            case Log.DEBUG:
                Log.d(TAG, buffer.toString());
                break;

            case Log.INFO:
                Log.i(TAG, buffer.toString());
                break;

            case Log.WARN:
                Log.w(TAG, buffer.toString());
                break;

            case Log.ERROR:
                Log.e(TAG, buffer.toString());
                break;

            default:
                break;
        }
    }

    public static void d(String msg) {
        if (DEBUG)
            handleLog(Log.DEBUG, msg, null);
    }

    public static void w(String msg) {
        if (DEBUG)
            handleLog(Log.WARN, msg, null);
    }

    public static void e(String msg) {
        if (DEBUG)
            handleLog(Log.ERROR, msg, null);
    }

    public static void i(String msg) {
        if (DEBUG)
            handleLog(Log.INFO, msg, null);
    }

    public static void v(String msg) {
        if (DEBUG)
            handleLog(Log.VERBOSE, msg, null);
    }

    public static void d(String head, String msg) {
        if (DEBUG)
            handleLog(Log.DEBUG, msg, head);
    }

    public static void i(String head, String msg) {
        if (DEBUG)
            handleLog(Log.INFO, msg, head);
    }

    public static void w(String head, String msg) {
        if (DEBUG)
            handleLog(Log.WARN, msg, head);
    }

    public static void e(String head, String msg) {
        if (DEBUG)
            handleLog(Log.ERROR, msg, head);
    }

    public static void v(String head, String msg) {
        if (DEBUG)
            handleLog(Log.VERBOSE, msg, head);
    }
}
