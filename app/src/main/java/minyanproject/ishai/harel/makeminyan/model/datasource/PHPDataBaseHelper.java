package minyanproject.ishai.harel.makeminyan.model.datasource;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import minyanproject.ishai.harel.makeminyan.model.backend.JSONArrayCursor;
import minyanproject.ishai.harel.makeminyan.model.entities.Shul;
import minyanproject.ishai.harel.makeminyan.model.entities.Minyan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.lang.Class;
import java.lang.reflect.Field;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.datatype.DatatypeConstants;

/**
 * Created by IHAVIV on 06-Feb-17.
 */

public class PHPDataBaseHelper {
    private static Uri serverUri = Uri.parse("http://izhai.vlab.jct.ac.il/MinyanApp/");
    private static String SUCCESS_STRING = "success";

    //user functions
    public boolean signin(String username,String password){
        try {
            String response = getResponse(serverUri.buildUpon()
                    .appendEncodedPath("signin.php")
                    .appendQueryParameter("username",username)
                    .appendQueryParameter("password",password)
                    .build()
                    .toString());
            if(response.equals(SUCCESS_STRING))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean signup(String username,String password,String email) {
        try {
            String response = getResponse(serverUri.buildUpon()
                    .appendPath("signup.php")
                    .appendQueryParameter("username",username)
                    .appendQueryParameter("password",password)
                    .build()
                    .toString());
            if(response.equals(SUCCESS_STRING))
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    //add functions
    private boolean addObject(Object o,Uri.Builder urlBuilder){
        try{
            for (Field field:o.getClass().getDeclaredFields()) {
                if(!field.getName().equals("_id"))//when adding id is automatically set
                    urlBuilder.appendQueryParameter(field.getName(), field.get(o).toString());
            }
            String response = getResponse(urlBuilder.build().toString());
            if(response.equals(SUCCESS_STRING))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean addShul(Shul shul){
        return addObject(shul,serverUri.buildUpon().appendEncodedPath("/shuls/create.php"));
    }
    public boolean addMinyan(Minyan minyan) {
        return addObject(minyan,serverUri.buildUpon().appendEncodedPath("/minyans/create.php"));
    }
    //update functions
    private boolean updateObject(Object o,Uri.Builder urlBuilder){
        try{
            for (Field field:o.getClass().getDeclaredFields()) {
                urlBuilder.appendQueryParameter(field.getName(), field.get(o).toString());
            }
            String response = getResponse(urlBuilder.build().toString());
            if(response.equals(SUCCESS_STRING))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateShul(Shul shul){
        return updateObject(shul,serverUri.buildUpon().appendEncodedPath("/shuls/update.php"));
    }
    public boolean updateMinyan(Minyan minyan) {
        return addObject(minyan,serverUri.buildUpon().appendEncodedPath("/minyans/update.php"));
    }
    //get functions
    public ShulList getShuls(ContentResolver contentResolver){
        Uri queryUri = serverUri.buildUpon()
                        .appendEncodedPath("/shuls/retrieve.php")
                        .build();
        JSONArrayCursor shulsCursor = (JSONArrayCursor) contentResolver.query(queryUri,null,null,null,null);
        return new ShulList(shulsCursor);
    }
    public MinyanList getMinyans(ContentResolver contentResolver){
        Uri queryUri = serverUri.buildUpon()
                .appendEncodedPath("/minyans/retrieve.php")
                .build();
        JSONArrayCursor shulsCursor = (JSONArrayCursor) contentResolver.query(queryUri,null,null,null,null);
        return new MinyanList(shulsCursor);
    }
    //delete functions
    public boolean deleteObject(int id){
        return false;
    }
    public boolean deleteShul(int id){
        return false;
    }

    private String getResponse(String urlString) throws IOException {
        URL url = new URL(urlString);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        return reader.readLine();
    }
}
