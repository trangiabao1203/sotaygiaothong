package com.trangiabao.giaothong.database;

import android.content.Context;
import android.database.Cursor;

import com.trangiabao.giaothong.model.BienBao;

import java.util.ArrayList;
import java.util.List;

public class BienBaoDB extends AbstractDB {

    public BienBaoDB(Context context) {
        super(context);
    }

    public List<BienBao> getByNhom(int idNhomBienBao) {
        List<BienBao> data = new ArrayList<>();
        Cursor c = database.rawQuery("select * from BienBao where idNhomBienBao = ?", new String[]{String.valueOf(idNhomBienBao)});
        while (c.moveToNext()) {
            BienBao temp = new BienBao(
                    context,
                    c.getInt(0),
                    c.getInt(1),
                    c.getString(2),
                    c.getString(3),
                    c.getString(4),
                    c.getInt(5)
            );
            data.add(temp);
        }
        c.close();
        return data;
    }
}