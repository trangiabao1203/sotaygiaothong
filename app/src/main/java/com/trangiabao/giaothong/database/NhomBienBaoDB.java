package com.trangiabao.giaothong.database;

import android.content.Context;
import android.database.Cursor;

import com.trangiabao.giaothong.model.NhomBienBao;

import java.util.ArrayList;
import java.util.List;

public class NhomBienBaoDB extends AbstractDB {

    public NhomBienBaoDB(Context context) {
        super(context);
    }

    public ArrayList<NhomBienBao> getAll() {
        ArrayList<NhomBienBao> data = new ArrayList<>();
        Cursor c = database.rawQuery("select * from NhomBienBao", null);
        while (c.moveToNext()) {
            NhomBienBao temp = new NhomBienBao(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2)
            );
            data.add(temp);
        }
        c.close();
        return data;
    }

    public List<String> getTenNhomBienBao(List<NhomBienBao> arr) {
        List<String> data = new ArrayList<>();
        for (NhomBienBao temp : arr) {
            data.add(temp.getTenNhomBienBao());
        }
        return data;
    }
}