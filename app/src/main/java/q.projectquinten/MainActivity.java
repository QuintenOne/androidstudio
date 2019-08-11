package q.projectquinten;

import android.app.FragmentTransaction;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static MainActivity mainActivity;
    DetailFragment detailFragment;

    public static Context getContext() {
        return mainActivity.getBaseContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("QQQ", "Start");

        new DatabaseFiller(getContentResolver()).addValues();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;
        detailFragment = new DetailFragment();


        if (findViewById(R.id.fr_details) != null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fr_details, detailFragment);
            fragmentTransaction.commit();
        }

    }

    public void onClick(View view) {
        TextView textView = view.findViewById(R.id.txt_ID);
        int id = Integer.parseInt(textView.getText().toString());

        if (findViewById(R.id.fr_details) == null) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("id", id - 1);
            startActivity(intent);
        } else {
            detailFragment.setData(id - 1);

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fr_details, detailFragment);
            fragmentTransaction.commit();
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, StudentWidget.class));

        StudentWidget.updateStudentWidgets(this, appWidgetManager, appWidgetIds);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.fr_main) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return true;
    }

    public void onSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("id", 5);
        startActivity(intent);
    }

    public void onEspresso(View view) {

        ImageView espressoButton = findViewById(R.id.espresso);
        espressoButton.setVisibility(View.INVISIBLE);

        try {
            MyTask.execute();
            Log.i("QQQ", "OK!");
        } catch (Exception e) {
            Log.i("QQQ", "Error: " + e.getMessage());
        }
    }
}
