package minyanproject.ishai.harel.makeminyan.model.datasource;

import android.database.Cursor;

import java.util.AbstractList;
import minyanproject.ishai.harel.makeminyan.model.entities.Shul;

/**
 * Created by IHAVIV on 06-Feb-17.
 */

public class ShulList extends AbstractList<Shul> {
    Cursor shulsCursor;

    public ShulList(Cursor cursor) {
        this.shulsCursor = cursor;
    }

    @Override
    public Shul get(int location) {

        shulsCursor.move(location);
        return new Shul(shulsCursor);
    }

    @Override
    public int size() {
        return shulsCursor.getCount();
    }
}
