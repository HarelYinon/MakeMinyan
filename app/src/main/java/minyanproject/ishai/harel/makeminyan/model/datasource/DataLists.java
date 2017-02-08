package minyanproject.ishai.harel.makeminyan.model.datasource;

import minyanproject.ishai.harel.makeminyan.model.entities.Shul;
import minyanproject.ishai.harel.makeminyan.model.entities.Minyan;
import minyanproject.ishai.harel.makeminyan.model.entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by IHAVIV on 04-Jan-17.
 */

public class DataLists {
    public ArrayList<Shul> shuls;
    public ArrayList<Minyan> minyans;
    public User user;
    public int version;
    private static DataLists singelton = new DataLists();

    private DataLists(){
        shuls = new ArrayList<Shul>();
        minyans = new ArrayList<Minyan>();
    }
    public static DataLists getDataLists(){return singelton;}

    public void update(JSONObject jsonObject) throws JSONException {

        //update shuls
        JSONArray jsonArray = jsonObject.getJSONArray("shuls");
        // looping through All Shuls
        shuls.clear();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonShul = jsonArray.getJSONObject(i);
            shuls.add(new Shul(jsonShul));
        }

        //update minyans
        jsonArray = jsonObject.getJSONArray("minyans");
        // looping through All Shuls
        minyans.clear();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonShul = jsonArray.getJSONObject(i);
            minyans.add(new Minyan(jsonShul));
        }

        //update version
        version = jsonObject.getInt("version");
    }
}
