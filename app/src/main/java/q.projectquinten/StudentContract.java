package q.projectquinten;

import android.net.Uri;
import android.provider.BaseColumns;

class StudentContract {
    static final String AUTHORITY = "q.projectquinten.ContentProvider";
    static final String STUDENT_PATH = "student";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    private StudentContract() {
    }

    static class Student implements BaseColumns {
        static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(STUDENT_PATH).build();
        static final String TABLE_NAME = "student";
        static final String COLUMN_FIRST_NAME = "firstName";
        static final String COLUMN_LAST_NAME = "lastName";
        static final String COLUMN_COLOR = "color";
        static final String COLUMN_AGE = "age";
        static final String COLUMN_ANIMAL = "animal";
    }
}

