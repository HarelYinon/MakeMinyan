package minyanproject.ishai.harel.makeminyan.model.backend;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import minyanproject.ishai.harel.makeminyan.model.backend.JSONArrayCursor;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by IHAVIV on 08-Jan-17.
 */

public class WebContentProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "com.ishaihaviv.minyanfinder.WebContentProvider";
    public static final String shulsDBname = "shuls";
    public static final String minyansDBname = "minyans";
    public static final String URL = "content://" + PROVIDER_NAME + "/";
    public static final String PHP_URL = "http://izhai.vlab.jct.ac.il/MinyanApp/";
    /*
    public static final Uri SHULS_URI = Uri.parse("content://" + PROVIDER_NAME + "/" + shulsDBname);
    public static final Uri MINYANS_URI = Uri.parse("content://" + PROVIDER_NAME + "/" + minyansDBname);
    public static final Uri CREATE_SHUL_URL = Uri.parse("http://izhai.vlab.jct.ac.il/MinyanApp/shuls/create.php");
    public static final Uri CREATE_MINYAN_URL = Uri.parse("http://izhai.vlab.jct.ac.il/MinyanApp/minyans/create.php");
    public static final Uri DELETE_SHUL_URL = Uri.parse("http://izhai.vlab.jct.ac.il/MinyanApp/shuls/delete.php");
    public static final Uri DELETE_MINYAN_URL = Uri.parse("http://izhai.vlab.jct.ac.il/MinyanApp/minyans/delete.php");
    public static final Uri UPFATE_SHUL_URL = Uri.parse("http://izhai.vlab.jct.ac.il/MinyanApp/shuls/update.php");
    public static final Uri UPDATE_MINYAN_URL = Uri.parse("http://izhai.vlab.jct.ac.il/MinyanApp/minyans/update.php");
    public static final Uri RETRIEVE_SHUL_URL = Uri.parse("http://izhai.vlab.jct.ac.il/MinyanApp/shuls/retrieve.php");
    public static final Uri RETRIEVE_MINYAN_URL = Uri.parse("http://izhai.vlab.jct.ac.il/MinyanApp/minyans/retrieve.php");


    public static final  int SHULS_ALL = 0;
    public static final int SHULS_SINGLE = 1;
    public static final  int MINYANS_ALL = 3;
    public static final int MINYANS_SINGLE = 4;

    public static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "shuls",  SHULS_ALL);
        uriMatcher.addURI(PROVIDER_NAME, "shuls/#", SHULS_SINGLE);
        uriMatcher.addURI(PROVIDER_NAME, "minyans",  MINYANS_ALL);
        uriMatcher.addURI(PROVIDER_NAME, "minyans/#", MINYANS_SINGLE);
    }*/

    public static final UriMatcher uriMatcher;
    public static final int VALID = 0;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "/shuls*",  VALID);
        uriMatcher.addURI(PROVIDER_NAME, "/minyans*",  VALID);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d("web-query","entered");
        String url = PHP_URL + uri.toString().substring(URL.length());
        try
        {
            return new JSONArrayCursor(new JSONObject(getResponse(url)));
        }catch (Exception e)
        {
            Log.e("web-query",e.getMessage());
            return null;
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return "model/json";
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        try
        {
            getResponse(uri.toString());
        }catch (Exception e)
        {
            Log.e("web-query",e.getMessage());
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private String getResponse(String urlString) throws IOException {
        java.net.URL url = new URL(urlString);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        return reader.readLine();
    }
}
