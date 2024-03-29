package com.trangiabao.giaothong.sathach.cauhoi.db;

import android.content.Context;
import android.database.Cursor;

import com.trangiabao.giaothong.ex.AbstractDB;
import com.trangiabao.giaothong.sathach.cauhoi.model.CauHoi;

import java.util.ArrayList;
import java.util.List;

public class CauHoiDB extends AbstractDB {

    public CauHoiDB(Context context) {
        super(context);
    }

    public List<CauHoi> getCauHoi(String query) {
        List<CauHoi> data = new ArrayList<>();
        Cursor c = database.rawQuery(query, null);
        while (c.moveToNext()) {
            CauHoi temp = new CauHoi(
                    context,
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2)
            );
            data.add(temp);
        }
        c.close();
        database.close();
        return data;
    }
}
