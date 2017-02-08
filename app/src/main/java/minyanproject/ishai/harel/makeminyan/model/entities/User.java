package minyanproject.ishai.harel.makeminyan.model.entities;

import java.io.InputStream;
import java.io.StringReader;

/**
 * Created by IHAVIV on 04-Jan-17.
 */

public class User {
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String toStringed) {
        String[] info = toStringed.split("\n");
        this.username = info[0];
        this.password = info[1];
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return username + "\n" +  password;
    }
}
