package com.example.tonghop_tuan4.Constructor;

public class IconHome {
    int imaAvt;
    String tvInfo;

    public IconHome(int imaAvt, String tvInfo) {
        this.imaAvt = imaAvt;
        this.tvInfo = tvInfo;
    }

    public IconHome() {
    }

    public int getImaAvt() {
        return imaAvt;
    }

    public void setImaAvt(int imaAvt) {
        this.imaAvt = imaAvt;
    }

    public String getTvInfo() {
        return tvInfo;
    }

    public void setTvInfo(String tvInfo) {
        this.tvInfo = tvInfo;
    }
}
