package com.trangiabao.giaothong.tracuu.xuphat;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.trangiabao.giaothong.R;
import com.trangiabao.giaothong.tracuu.xuphat.db.LoaiViPhamDB;
import com.trangiabao.giaothong.tracuu.xuphat.model.MucXuPhat;

import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

@SuppressLint("ValidFragment")
public class XuPhatFragment extends Fragment {

    private MaterialSpinner spinnerXuPhat;
    private List<List<MucXuPhat>> lstMucXuPhat;
    private FastItemAdapter<MucXuPhat> adapter;
    private RecyclerView rvXuPhat;

    public XuPhatFragment(List<List<MucXuPhat>> lstMucXuPhat) {
        this.lstMucXuPhat = lstMucXuPhat;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xu_phat, container, false);

        spinnerXuPhat = (MaterialSpinner) view.findViewById(R.id.spinnerXuPhat);
        spinnerXuPhat.setItems(new LoaiViPhamDB(getActivity()).getAllAsString());

        rvXuPhat = (RecyclerView) view.findViewById(R.id.rvXuPhat);
        rvXuPhat.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvXuPhat.setHasFixedSize(true);

        adapter = new FastItemAdapter<>();
        adapter.setHasStableIds(true);
        adapter.withSelectable(true);

        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(adapter);
        animationAdapter.setDuration(1500);
        animationAdapter.setInterpolator(new OvershootInterpolator());
        animationAdapter.setFirstOnly(false);

        rvXuPhat.setAdapter(animationAdapter);
        adapter.withSavedInstanceState(savedInstanceState);

        addEvents();
        return view;
    }

    private void addEvents() {
        selectSpinner(0);
        spinnerXuPhat.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                selectSpinner(position);
            }
        });
    }

    private void selectSpinner(int flag) {
        adapter.clear();
        adapter.add(this.lstMucXuPhat.get(flag));
        if (this.lstMucXuPhat.get(flag).size() == 0) {
            Toast.makeText(getActivity(), "Wait for updating", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState = adapter.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
}