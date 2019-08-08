package q.projectquinten;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

public class StudentIntentService extends IntentService {

    private static final String ACTION_FOO = "q.projectquinten.action.FOO";
    private static final String ACTION_BAZ = "q.projectquinten.action.BAZ";

    private static final String EXTRA_PARAM1 = "q.projectquinten.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "q.projectquinten.extra.PARAM2";

    public StudentIntentService() {
        super("StudentIntentService");
    }


    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, StudentIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }


    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, StudentIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    private void handleActionFoo(String param1, String param2) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleActionBaz(String param1, String param2) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
