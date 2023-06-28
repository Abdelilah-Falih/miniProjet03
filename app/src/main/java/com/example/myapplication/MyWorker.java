package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.myapplication.Database.Quote;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MyWorker extends Worker {
    static ArrayList<Quote> quotes;
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        saveQuotes();
        //showNotification();
        return Result.success();
    }

    private void saveQuotes() {
        String fileName = "Quotes.txt";
        File downloadsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File savedFile = new File(downloadsDirectory, fileName);
        try {
            FileWriter fileWriter = new FileWriter(savedFile);
            for (Quote quote:quotes) {
                fileWriter.append(String.format("#%d :  %s\n\t-->  %s\n", quote.getId(),  quote.getQuote(),   quote.getAuthor()));
            }
            fileWriter.flush();
            fileWriter.close();
            showNotification();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showNotification() {
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("chanel_id", "chanel", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "chanel_id")
                .setContentTitle("this is notification")
                .setContentText("hello from falih")
                .setSmallIcon(R.drawable.ic_launcher_foreground);
        manager.notify(1, builder.build());

    }
}
