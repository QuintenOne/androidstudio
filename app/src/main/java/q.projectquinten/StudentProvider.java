package q.projectquinten;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

public class StudentProvider extends ContentProvider {

    private static final int ALL_STUDENTS = 1;
    private static final int STUDENT_ID = 2;
    private static UriMatcher uriMatcher; //Match een URI en geeft de bijhorende ID
    private StudentDatabaseHelper helper;

    @Override
    public boolean onCreate() {
        helper = new StudentDatabaseHelper(getContext());
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(StudentContract.AUTHORITY, StudentContract.STUDENT_PATH, ALL_STUDENTS);
        uriMatcher.addURI(StudentContract.AUTHORITY, StudentContract.STUDENT_PATH + "/#", STUDENT_ID);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase database = helper.getReadableDatabase();

        int match = uriMatcher.match(uri);
        if (match == ALL_STUDENTS)
            return database.query(StudentContract.Student.TABLE_NAME, projection, null, null, null, null, null);
        if (match == STUDENT_ID)
            return database.query(StudentContract.Student.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        throw new UnsupportedOperationException("Unknown Uri: " + uri);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri returnUri;

        if (uriMatcher.match(uri) == ALL_STUDENTS) {
            long id = helper.getWritableDatabase().insert(StudentContract.Student.TABLE_NAME, null, values);
            if (id > 0)
                returnUri = ContentUris.withAppendedId(StudentContract.Student.CONTENT_URI, id);
            else throw new SQLException("Failed to insert row into " + uri);
        } else throw new UnsupportedOperationException("Unknown Uri: " + uri);


        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deletedRows;

        if (uriMatcher.match(uri) == STUDENT_ID) {
            deletedRows = helper.getWritableDatabase().delete(StudentContract.Student.TABLE_NAME, selection, selectionArgs);
        } else throw new UnsupportedOperationException("Unknown Uri " + uri);

        return deletedRows;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}

