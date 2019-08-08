package q.projectquinten;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Cursor cursor;

    StudentAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.setText();
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView firstName, lastName, ID;

        StudentViewHolder(View itemView) {
            super(itemView);

            ID = itemView.findViewById(R.id.txt_ID);
            lastName = itemView.findViewById(R.id.txt_lastName);
            firstName = itemView.findViewById(R.id.txt_firstName);
        }

        void setText() {
            this.ID.setText(cursor.getString(0));
            this.lastName.setText(cursor.getString(1));
            this.firstName.setText(cursor.getString(2));
        }
    }
}

