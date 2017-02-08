package minyanproject.ishai.harel.makeminyan.model.datasource;

import android.database.Cursor;

import minyanproject.ishai.harel.makeminyan.model.entities.Minyan;
import minyanproject.ishai.harel.makeminyan.model.entities.Shul;

import org.json.JSONException;

import java.util.AbstractList;

/**
 * Created by IHAVIV on 06-Feb-17.
 */

public class MinyanList extends AbstractList<Minyan> {
    Cursor shulsCursor;

    public MinyanList(Cursor cursor) {
        this.shulsCursor = cursor;
    }

    @Override
    public Minyan get(int location) {
        shulsCursor.move(location);
        return new Minyan(shulsCursor);
    }

    @Override
    public int size() {
        return shulsCursor.getCount();
    }
}
