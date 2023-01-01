package com.example.tonghop_tuan4.Constructor;

public class class_registered {
//    private Boolean isRegistered;
    private String idKhoaHocRegistered;
    private String idClassRegistered;


    public class_registered(){}
//    public class_registered(Boolean isRegistered) {
//        this.isRegistered = isRegistered;
//    }
//
//    public Boolean getRegistered() {
//        return isRegistered;
//    }
//
//    public void setRegistered(Boolean registered) {
//        isRegistered = registered;
//    }
//    public class_registered(String idKhoaHocRegistered) {
//        this.idKhoaHocRegistered = idKhoaHocRegistered;
//    }
//
//    public String getIdKhoaHocRegistered() {
//        return idKhoaHocRegistered;
//    }
//
//    public void setIdKhoaHocRegistered(String idKhoaHocRegistered) {
//        this.idKhoaHocRegistered = idKhoaHocRegistered;
//    }
    public class_registered(String idKhoaHoc, String idClassRegistered) {
        this.idKhoaHocRegistered = idKhoaHoc;
        this.idClassRegistered = idClassRegistered;
    }

    public String getIdKhoaHocRegistered() {
        return idKhoaHocRegistered;
    }

    public void setIdKhoaHocRegistered(String idKhoaHocRegistered) {
        this.idKhoaHocRegistered = idKhoaHocRegistered;
    }

    public String getIdClassRegistered() {
        return idClassRegistered;
    }

    public void setIdClassRegistered(String idClassRegistered) {
        this.idClassRegistered = idClassRegistered;
    }
}
