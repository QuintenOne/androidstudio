package q.projectquinten;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {

    @SuppressLint("StaticFieldLeak")
    public static MainFragment mainFragment;

    RecyclerView recyclerView;
    StudentAdapter studentAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainFragment = this;

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        String[] projection = {"_ID", "lastName", "firstName", "color", "age", "animal"};
        Cursor cursor = getContext().getContentResolver().query(StudentContract.Student.CONTENT_URI, projection, null, null);

        studentAdapter = new StudentAdapter(cursor);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(studentAdapter);
        return view;
    }
}
