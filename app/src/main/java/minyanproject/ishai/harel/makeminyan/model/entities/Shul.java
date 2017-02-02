package minyanproject.ishai.harel.makeminyan.model.entities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IHAVIV on 04-Jan-17.
 */

public class Shul {
    String name;
    String location;
    int _id;
    String description;
    String username;

    public Shul(String name, String location, int _id, String description, String username) {
        this.name = name;
        this.location = location;
        this._id = _id;
        this.description = description;
        this.username = username;

    }

    public Shul(JSONObject jsonObject) throws JSONException {
        this._id = jsonObject.getInt("_id");
        this.name = jsonObject.getString("name");
        this.location = jsonObject.getString("location");
        this.description = jsonObject.getString("description");
        //this.username = jsonObject.getString("username");
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int get_id() {
        return _id;
    }

    public String getDescription() {
        return description;
    }


    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s\n"+
                "Address: %s\n" +
                "Description:\n" +
                "%s",_id,name,location,description);
    }
}
