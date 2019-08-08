package q.projectquinten;

import android.app.Fragment;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    public static int id = -1;
    public static String student;
    ViewGroup container;
    Cursor cursor;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] projection = {"firstName", "lastName", "color", "age", "animal"};
        cursor = MainActivity.getContext().getContentResolver().query(StudentContract.Student.CONTENT_URI, projection, null, null);
        view = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null)
            setData(bundle.getInt("id"));

        return view;
    }

    public void setData(int id) {
        DetailFragment.id = id;

        if (id != -1) {
            cursor.moveToPosition(id);
            ((TextView) view.findViewById(R.id.txtTitle)).setText(String.format("%s %s", cursor.getString(0), cursor.getString(1)));
            ((TextView) view.findViewById(R.id.txtColor)).setText(cursor.getString(2));
            ((TextView) view.findViewById(R.id.txtAge)).setText(cursor.getString(3));
            ((TextView) view.findViewById(R.id.txtAnimal)).setText(cursor.getString(4));
        }

        /*
        //UNUSED
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(MainActivity.getContext());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(MainActivity.getContext(), StudentWidget.class));
        StudentWidget.updateStudentWidgets(MainActivity.getContext(), appWidgetManager, appWidgetIds);
        */
    }
}
