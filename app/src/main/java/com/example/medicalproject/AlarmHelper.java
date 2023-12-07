package com.example.medicalproject;


import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmHelper extends BroadcastReceiver {
    // Declara a variável do tipo AlarmManager
    private AlarmManager alarmManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    // Método para criar o alarme
    public void createAlarm(Context context, long startTime, long repeatInterval, int alarmId, Intent intent) {
        // Cria o PendingIntent para executar a ação do alarme
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Define o horário do alarme
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, startTime, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, startTime, pendingIntent);
        }

    }

    // Método para cancelar o alarme
    public void cancelAlarm(Context context, int alarmId) {
        // Cria o PendingIntent para cancelar o alarme
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(context, alarmId, new Intent(), PendingIntent.FLAG_IMMUTABLE);

        // Cancela o alarme
        alarmManager.cancel(pendingIntent);
    }


}