package minyanproject.ishai.harel.makeminyan.model.entities;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IHAVIV on 04-Jan-17.
 */

public class Shul {
    public String name;
    public String location;
    public int _id;
    public String description;
    public String username;
    public String email;
    public String website;
    public String telephone;

    public Shul(String name, String location, int _id, String description, String username, String email, String website, String telephone) {
        this.name = name;
        this.location = location;
        this._id = _id;
        this.description = description;
        this.username = username;
        this.email = email;
        this.website = website;
        this.telephone = telephone;
    }

    public Shul(JSONObject jsonObject) throws JSONException {
        this._id = jsonObject.getInt("_ID");
        this.name = jsonObject.getString("name");
        this.location = jsonObject.getString("location");
        this.description = jsonObject.getString("description");
        this.username = jsonObject.getString("username");
        this.email =        jsonObject.getString("email");
        this.website =      jsonObject.getString("website");
        this.telephone =    jsonObject.getString("telephone");
    }

    public Shul(Cursor Cursor){
        this._id =          Cursor.getInt(0);
        this.name =         Cursor.getString(1);
        this.location =     Cursor.getString(2);
        this.description =  Cursor.getString(3);
        this.username =     Cursor.getString(4);
        this.email =        Cursor.getString(5);
        this.website =      Cursor.getString(6);
        this.telephone =    Cursor.getString(7);
    }

    @Override
    public String toString() {
        return  "name=" + name +
                "&location=" + location + 
                "&_id=" + _id +
                "&description=" + description + 
                "&username=" + username + 
                "&email=" + email + 
                "&website=" + website + 
                "&telephone=" + telephone;
    }
}
