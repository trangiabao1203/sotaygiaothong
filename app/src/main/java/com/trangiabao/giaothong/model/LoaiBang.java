package com.trangiabao.giaothong.model;

public class LoaiBang {

    private int id;
    private String tenBang;
    private String moTa;
    private int soCauHoi;
    private int soCauThi;
    private int soCauDatYeuCau;
    private int thoiGian;
    private String quyTac;

    public LoaiBang(int id, String tenBang, String moTa, int soCauHoi, int soCauThi, int soCauDatYeuCau, int thoiGian, String quyTac) {
        this.id = id;
        this.tenBang = tenBang;
        this.moTa = moTa;
        this.soCauHoi = soCauHoi;
        this.soCauThi = soCauThi;
        this.soCauDatYeuCau = soCauDatYeuCau;
        this.thoiGian = thoiGian;
        this.quyTac = quyTac;
    }

    public int getId() {
        return id;
    }

    public String getTenBang() {
        return tenBang;
    }

    public String getMoTa() {
        return moTa;
    }

    public int getSoCauHoi() {
        return soCauHoi;
    }

    public int getSoCauThi() {
        return soCauThi;
    }

    public int getSoCauDatYeuCau() {
        return soCauDatYeuCau;
    }

    public int getThoiGian() {
        return thoiGian;
    }

    public String getQuyTac() {
        return quyTac;
    }
}
