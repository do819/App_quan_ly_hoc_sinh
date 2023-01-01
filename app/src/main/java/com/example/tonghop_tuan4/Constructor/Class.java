package com.example.tonghop_tuan4.Constructor;

import java.util.Date;

public class Class {
    private String idKhoahoc;
    private String id;
    private String name;
    private String ngaylap;
    private String ngayketthuc;


    public Class(){

    }


    public Class(String idKhoahoc, String id, String name, String ngaylap, String ngayketthuc) {
        this.idKhoahoc = idKhoahoc;
        this.id = id;
        this.name = name;
        this.ngaylap = ngaylap;
        this.ngayketthuc = ngayketthuc;
    }

    public Class( String name, String ngaylap, String ngayketthuc) {
        this.name = name;
        this.ngaylap = ngaylap;
        this.ngayketthuc = ngayketthuc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getIdKhoahoc() {
        return idKhoahoc;
    }

    public void setIdKhoahoc(String idKhoahoc) {
        this.idKhoahoc = idKhoahoc;
    }

    public String getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(String ngaylap) {
        this.ngaylap = ngaylap;
    }

    public String getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(String ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }
}
