package q.projectquinten;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public class DetailActivity extends AppCompatActivity {
    DetailFragment detailFragment;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailFragment = new DetailFragment();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fr_details, detailFragment);
        fragmentTransaction.commit();
    }

    /*void updateStudentWidget(View view) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, StudentWidget.class));

        StudentWidget.updateStudentWidgets(this, appWidgetManager, appWidgetIds);
    }*/
}
