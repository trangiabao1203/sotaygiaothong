package com.trangiabao.giaothong.tracuu.hotline.db;

import android.content.Context;
import android.database.Cursor;

import com.trangiabao.giaothong.ex.AbstractDB;
import com.trangiabao.giaothong.ex.MyMethod;
import com.trangiabao.giaothong.tracuu.hotline.model.HotLine;

import java.util.ArrayList;
import java.util.List;

public class HotLineDB extends AbstractDB {

    public HotLineDB(Context context) {
        super(context);
    }

    public List<HotLine> getByIdNhomHotline(String idNhomHotLine) {
        List<HotLine> data = new ArrayList<>();
        Cursor c = database.rawQuery("select * from HotLine where idNhomHotLine = ?", new String[]{idNhomHotLine});
        while (c.moveToNext()) {
            HotLine temp = new HotLine(
                    c.getInt(0),
                    c.getInt(1),
                    c.getString(2),
                    c.getString(3)
            );
            data.add(temp);
        }
        c.close();
        database.close();
        return data;
    }

    public List<HotLine> filter(String filter) {
        List<HotLine> data = new ArrayList<>();
        Cursor c = database.rawQuery("select * from HotLine", null);
        while (c.moveToNext()) {
            HotLine temp = new HotLine(
                    c.getInt(0),
                    c.getInt(1),
                    c.getString(2),
                    c.getString(3)
            );
            filter = MyMethod.unAccent(filter).toLowerCase().replace(".", "");
            String ten = MyMethod.unAccent(temp.getTen()).toLowerCase();
            String phone = MyMethod.unAccent(temp.getPhone()).toLowerCase().replace(".", "").replace(" ", "");
            if (ten.contains(filter) || phone.contains(filter))
                data.add(temp);
        }
        c.close();
        database.close();
        return data;
    }
}