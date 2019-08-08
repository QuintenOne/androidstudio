package q.projectquinten;

import android.app.FragmentTransaction;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    static MainActivity mainActivity;
    DetailFragment detailFragment;

    public static Context getContext() {
        return mainActivity.getBaseContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
}
