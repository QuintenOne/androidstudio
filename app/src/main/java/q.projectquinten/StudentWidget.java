package q.projectquinten;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;

public class StudentWidget extends AppWidgetProvider {

    static Cursor cursor;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.student_widget);

        if (DetailFragment.id != -1) {
            String[] projection = {"_ID", "firstName", "lastName"};
            cursor = MainActivity.getContext().getContentResolver().query(StudentContract.Student.CONTENT_URI, projection, null, null);
            if (cursor != null) {
                cursor.moveToPosition(DetailFragment.id);
            }
            if (cursor != null) {
                views.setTextViewText(R.id.appwidget_text, cursor.getString(1) + " " + cursor.getString(2));
            }
            //views.setTextViewText(R.id.appwidget_text, "# " + cursor.getString(0));
        }

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.appwidget, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateStudentWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}





