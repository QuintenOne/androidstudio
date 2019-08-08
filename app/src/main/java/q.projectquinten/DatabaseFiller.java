package q.projectquinten;

import android.content.ContentResolver;
import android.content.ContentValues;
import java.io.IOException;
import java.util.Random;

class DatabaseFiller {

    private ContentValues values;
    private ContentResolver contentResolver;

    DatabaseFiller(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    void addValues() {
        String[] names = {"Alice", "Bob", "Kwinten", "Jorn", "Nic", "Mike", "Lars", "Sofie", "Elon"};
        String[] lastnames = {"Zeta", "Yotta", "Crocs", "Boorns", "Wuit", "Peeters", "Straf", "Bergens", "Musk"};
        String[] ages = {"14", "16", "24", "27", "31", "39", "48", "65", "75"};
        String[] colors = {"Blue", "Green", "Red", "Yellow", "White", "Black", "Orange", "Purple", "Magenta"};
        String[] animals = {"Cat", "Dog", "Zebra", "Elephant", "Cow", "Chicken", "Tiger", "Lion", "Salamander"};
        values = new ContentValues();

        for (int i = 0; i < 2; i++) {
            values.put(StudentContract.Student.COLUMN_FIRST_NAME, names[(int) (new Random().nextFloat() * names.length)]);
            values.put(StudentContract.Student.COLUMN_LAST_NAME, lastnames[(int) (new Random().nextFloat() * lastnames.length)]);
            values.put(StudentContract.Student.COLUMN_COLOR, colors[(int) (new Random().nextFloat() * colors.length)]);
            values.put(StudentContract.Student.COLUMN_AGE, ages[(int) (new Random().nextFloat() * ages.length)]);
            values.put(StudentContract.Student.COLUMN_ANIMAL, animals[(int) (new Random().nextFloat() * animals.length)]);

            contentResolver.insert(StudentContract.Student.CONTENT_URI, values);
            values.clear();
        }
    }
}
