package com.trangiabao.giaothong.tracuu.luat.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.trangiabao.giaothong.R;

import java.util.List;

public class Chuong extends AbstractItem<Chuong, Chuong.ViewHolder> {

    private int id;
    private String tenChuong;
    private String muc;

    public Chuong(int id, String tenChuong, String muc) {
        this.id = id;
        this.tenChuong = tenChuong;
        this.muc = muc;
    }

    public int getId() {
        return id;
    }

    public String getTenChuong() {
        return tenChuong;
    }

    public String getMuc() {
        return muc;
    }

    @Override
    public int getType() {
        return R.id.item_layout;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_6;
    }

    @Override
    public void bindView(ViewHolder viewHolder, List payloads) {
        super.bindView(viewHolder, payloads);

        viewHolder.txtSubject.setText(getTenChuong());

        if (getMuc().equals("")) {
            viewHolder.txtContent.setVisibility(View.GONE);
        } else {
            viewHolder.txtContent.setVisibility(View.VISIBLE);
            viewHolder.txtContent.setText(getMuc());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtSubject;
        private TextView txtContent;

        public ViewHolder(View view) {
            super(view);
            this.txtSubject = (TextView) view.findViewById(R.id.txtSubject);
            this.txtContent = (TextView) view.findViewById(R.id.txtContent);
        }
    }
}
