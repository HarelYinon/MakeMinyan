package minyanproject.ishai.harel.makeminyan.model.entities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IHAVIV on 04-Jan-17.
 */

public class Minyan {
    String nusach;
    String type;
    int shulID;
    int hour;
    int minute;
    int days;

    public final static int SUN = 1;
    public final static int MON = 2;
    public final static int TUE = 4;
    public final static int WED = 8;
    public final static int THU = 16;
    public final static int FRI = 32;
    public final static int SAT = 64;


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

    public String getNusach() {
        return nusach;
    }

    public String getType() {
        return type;
    }

    public int getShulID() {
        return shulID;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getDays() {
        return days;
    }
}
