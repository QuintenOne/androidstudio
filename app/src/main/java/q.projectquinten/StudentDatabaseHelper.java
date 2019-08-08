package q.projectquinten;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 1;

    StudentDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_LECTOR_DATABASE = "CREATE TABLE IF NOT EXISTS " +
                StudentContract.Student.TABLE_NAME + " (" +
                StudentContract.Student._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                StudentContract.Student.COLUMN_FIRST_NAME + " VARCHAR(20), " +
                StudentContract.Student.COLUMN_LAST_NAME + " VARCHAR(20), " +
                StudentContract.Student.COLUMN_COLOR + " VARCHAR(20), " +
                StudentContract.Student.COLUMN_AGE + " VARCHAR(20), " +
                StudentContract.Student.COLUMN_ANIMAL + " VARCHAR(20) " +
                ")";

        db.execSQL(CREATE_LECTOR_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + StudentContract.Student.TABLE_NAME);
        onCreate(db);
    }
}

