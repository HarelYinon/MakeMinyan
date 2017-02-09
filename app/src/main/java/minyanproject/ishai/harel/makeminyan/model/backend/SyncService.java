package minyanproject.ishai.harel.makeminyan.model.backend;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import minyanproject.ishai.harel.makeminyan.model.datasource.DataLists;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SyncService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_SYNC = "com.ishaihaviv.minyanfinder.backend.action.SYNC";
    private static final String ACTION_PUSH = "com.ishaihaviv.minyanfinder.backend.action.PUSH";
    public static final String SYNCED_BROADCAST = "com.ishaihaviv.minyanfinder.action.SYNCED_BROADCAST";
    private static final String UPTODATE = "Up to date";
    private static final String SUCCESS = "success";
    private static final String URL_PARAM = "url";
    private static final String SYNC_URL = "http://izhai.vlab.jct.ac.il/MinyanApp/sync.php";


    public SyncService() {
        super("SyncService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void scheduleActionSync(Context context) {
        Intent intent = new Intent(context, SyncService.class);
        intent.setAction(ACTION_SYNC);
        PendingIntent pintent = PendingIntent.getService(context,0,intent,0);
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC, System.currentTimeMillis(),15*60*1000,pintent);
        context.startService(intent);
    }

    public static void actionSync(Context context) {
        Intent intent = new Intent(context, SyncService.class);
        intent.setAction(ACTION_SYNC);
        context.startService(intent);
    }

    public static void actionPush(Context context, URI uri) {
        Intent intent = new Intent(context, SyncService.class);
        intent.setAction(ACTION_PUSH);
        intent.putExtra(URL_PARAM,uri.toString());
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SYNC.equals(action)) {
                handleActionSync();
            }else if(ACTION_PUSH.equals(action))
            {
                handleActionPush(intent.getStringExtra(URL_PARAM));
            }
        }
    }

    private void handleActionPush(String urlString){
        try{
            while(!getResponse(urlString).equals(SUCCESS));
        }catch(Exception e){
            Log.e("SyncService-handle",e.getMessage());
            //// TODO do something when i can't connect that tell the user that he can't get the most update imformation
        }
    }

    private String getResponse(String urlString) throws IOException {
        URL url = new URL(urlString);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        return reader.readLine();
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionSync() {
        if(trySync())
            sendBroadcast(new Intent(SYNCED_BROADCAST));
    }

    private boolean trySync()
    {
        try
        {
            int version = DataLists.getDataLists().version;
            Uri uri = Uri.parse(SYNC_URL)
                    .buildUpon()
                    .appendQueryParameter("version", String.valueOf(version))
                    .build();
            String response = getResponse(uri.toString());
            if(response == UPTODATE)
                return false;
            parseJson(response);
            return true;
        }catch(Exception e){
            Log.e("SyncService-handle",e.getMessage());
            //// TODO do something when i can't connect that tell the user that he can't get the most update imformation
        }
        return false;
    }

    private void parseJson(String json) throws IOException, JSONException {
        DataLists.getDataLists().update(new JSONObject(json));
    }
}
