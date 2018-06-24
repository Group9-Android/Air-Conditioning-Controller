package com.example.smallkun.aswitch;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidget1 extends AppWidgetProvider {

    final String B1="action_button1";
    final String B2="action_button2";
    final String B3="action_button3";
    final String B4="action_button4";


    @Override
    public void  onReceive(Context context, Intent intent){
        super.onReceive(context,intent);

        if (intent==null){
            return;
        }

        String action = intent.getAction();
        if (action.equals(B1)){
            Toast.makeText(context, "You click button 1", Toast.LENGTH_SHORT).show();
        }else if (action.equals(B2)){
            Toast.makeText(context, "You click button 2", Toast.LENGTH_SHORT).show();
        }else if (action.equals(B3)){
            Toast.makeText(context, "You click button 3", Toast.LENGTH_SHORT).show();
        }else if(action.equals(B4)){
            Toast.makeText(context, "You click button 4", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Intent intent1 = new Intent(B1);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, 0, intent1, 0);
        Intent intent2 = new Intent(B2);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(context, 0, intent2, 0);
        Intent intent3 = new Intent(B3);
        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(context, 0, intent3, 0);
        Intent intent4 = new Intent(B4);
        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, 0, intent4, 0);

        // 小部件在Launcher桌面的布局
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.app_widget1);

        // 事件
        remoteViews.setOnClickPendingIntent(R.id.b1, pendingIntent1);
        remoteViews.setOnClickPendingIntent(R.id.b2, pendingIntent2);
        remoteViews.setOnClickPendingIntent(R.id.b3, pendingIntent3);
        remoteViews.setOnClickPendingIntent(R.id.b4, pendingIntent4);

        // 更新AppWidget
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);

        // There may be multiple widgets active, so update all of them
        /*for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }*/

    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        // Enter relevant functionality for when the last widget is disabled
    }
}

