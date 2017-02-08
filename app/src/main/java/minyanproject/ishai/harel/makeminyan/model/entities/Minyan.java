package minyanproject.ishai.harel.makeminyan.model.entities;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IHAVIV on 04-Jan-17.
 */

public class Minyan {
    public String nusach;
    public String type;
    public int shulID;
    public int hour;
    public int minute;
    public int days;

    final static int SUN = 1;
    final static int MON = 2;
    final static int TUE = 4;
    final static int WED = 8;
    final static int THU = 16;
    final static int FRI = 32;
    final static int SAT = 64;


    public Minyan(int attending, String nusach, String type, int shulID, int hour, int minute, int days) {
        this.nusach = nusach;
        this.type = type;
        this.shulID = shulID;
        this.hour = hour;
        this.minute = minute;
        this.days = days;
    }

    public Minyan(JSONObject jsonObject) throws JSONException {
        this.nusach = jsonObject.getString("nusach");
        this.type =   jsonObject.getString("type");
        this.shulID =    jsonObject.getInt("shulID");
        this.hour =   jsonObject.getInt("hour");
        this.minute = jsonObject.getInt("minute");
        //set days
        this.days |= "1".equals(jsonObject.getString("SUN")) ? SUN : 0;
        this.days |= "1".equals(jsonObject.getString("MON")) ? MON : 0;
        this.days |= "1".equals(jsonObject.getString("TUE")) ? TUE : 0;
        this.days |= "1".equals(jsonObject.getString("WED")) ? WED : 0;
        this.days |= "1".equals(jsonObject.getString("THU")) ? THU : 0;
        this.days |= "1".equals(jsonObject.getString("FRI")) ? FRI : 0;
        this.days |= "1".equals(jsonObject.getString("SAT")) ? SAT : 0;
    }

    public Minyan(Cursor cursor){
        this.nusach = cursor.getString(0);
        this.type =   cursor.getString(1);
        this.shulID =    cursor.getInt(2);
        this.hour =   cursor.getInt(3);
        this.minute = cursor.getInt(4);
        //set days
        this.days |= "1".equals(cursor.getString(5)) ? SUN : 0;
        this.days |= "1".equals(cursor.getString(6)) ? MON : 0;
        this.days |= "1".equals(cursor.getString(7)) ? TUE : 0;
        this.days |= "1".equals(cursor.getString(8)) ? WED : 0;
        this.days |= "1".equals(cursor.getString(9)) ? THU : 0;
        this.days |= "1".equals(cursor.getString(10)) ? FRI : 0;
        this.days |= "1".equals(cursor.getString(11)) ? SAT : 0;
    }
}
