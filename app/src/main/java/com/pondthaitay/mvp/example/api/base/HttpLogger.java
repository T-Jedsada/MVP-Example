package com.pondthaitay.mvp.example.api.base;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import okhttp3.logging.HttpLoggingInterceptor;

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    private final static String TAG = HttpLogger.class.getSimpleName();

    @Override
    public void log(String message) {
        final String logName = "OkHttp";
        if (!message.startsWith("{")) {
            Log.d(logName, message);
            return;
        }
        try {
            String prettyPrintJson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(new JsonParser().parse(message));
            Log.e(HttpLogger.TAG, prettyPrintJson);
        } catch (JsonSyntaxException m) {
            Log.e(TAG, "html header parse failed");
            m.printStackTrace();
            Log.e(logName, message);
        }
    }
}