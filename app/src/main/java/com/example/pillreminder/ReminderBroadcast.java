package com.example.pillreminder;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class ReminderBroadcast extends BroadcastReceiver {




    @Override
    public void onReceive(Context context, Intent intent) {


        String text = intent.getExtras().getString("text");



            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notifyMe")
                    .setSmallIcon(R.drawable.ic_stat_local_hospital)
                    .setContentTitle("Reminder")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                    .setContentText(text)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(200,builder.build());
        }



    }



