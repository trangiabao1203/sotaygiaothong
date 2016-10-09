package com.trangiabao.giaothong.sathach;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.trangiabao.giaothong.R;
import com.trangiabao.giaothong.sathach.cauhoi.TuyChonCauHoiActivity;
import com.trangiabao.giaothong.sathach.cauhoi.TuyChonBaiThiActivity;
import com.trangiabao.giaothong.sathach.meo.MeoActivity;
import com.trangiabao.giaothong.sathach.sahinh.SaHinhActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SatHachFragment extends Fragment {

    private FastItemAdapter<SatHachAdapter> adapter;
    private RecyclerView rvSatHach;
    private Context context;

    private static final String[] PATH = {
            "image/icon/ic_cau_hoi.png",
            "image/icon/ic_lam_bai_thi.png",
            "image/icon/ic_sa_hinh.png",
            "image/icon/ic_meo.png"
    };

    public SatHachFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sat_hach, container, false);
        context = getActivity();

        rvSatHach = (RecyclerView) view.findViewById(R.id.rvSatHach);
        rvSatHach.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSatHach.setHasFixedSize(true);

        adapter = new FastItemAdapter<>();
        adapter.setHasStableIds(true);
        adapter.withSelectable(true);

        rvSatHach.setAdapter(adapter);
        adapter.add(createList());

        addEvents();
        return view;
    }

    private List<SatHachAdapter> createList() {
        List<SatHachAdapter> data = new ArrayList<>();
        data.add(new SatHachAdapter("Ngân hàng câu hỏi", getDrawable(PATH[0])));
        data.add(new SatHachAdapter("Làm bài thi", getDrawable(PATH[1])));
        data.add(new SatHachAdapter("Bài thi sa hình", getDrawable(PATH[2])));
        data.add(new SatHachAdapter("Mẹo ghi nhớ", getDrawable(PATH[3])));
        return data;
    }

    private Drawable getDrawable(String path) {
        Drawable drawable = null;
        try {
            InputStream is = context.getAssets().open(path);
            drawable = Drawable.createFromStream(is, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawable;
    }

    private void addEvents() {
        adapter.withOnClickListener(new FastAdapter.OnClickListener<SatHachAdapter>() {
            @Override
            public boolean onClick(View v, IAdapter<SatHachAdapter> adapter, SatHachAdapter item, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), TuyChonCauHoiActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), TuyChonBaiThiActivity.class));
                        break;
                    case 2:
                        ConnectivityManager connectivityManager =
                                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                        Intent youtubeIntent = context.getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                        if (connectivityManager.getActiveNetworkInfo() == null) {
                            Toast.makeText(context, "Chức năng yêu cầu kết nối Internet", Toast.LENGTH_SHORT).show();
                        } else if (youtubeIntent == null) {
                            new MaterialDialog.Builder(context)
                                    .title("Không tìm thấy ứng dụng Youtube")
                                    .content("Chức năng yêu cầu thiết bị phải có ứng dụng Youtube. Bạn có muốn cài đặt ngay bây giờ không ?")
                                    .positiveText("Có")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            try {
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.youtube")));
                                            } catch (android.content.ActivityNotFoundException anfe) {
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.youtube")));
                                            }
                                        }
                                    })
                                    .negativeText("Để sau")
                                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();
                        } else {
                            startActivity(new Intent(getActivity(), SaHinhActivity.class));
                        }
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), MeoActivity.class));
                        break;
                }
                return true;
            }
        });
    }
}
