package com.example.tonghop_tuan4.Constructor;

public class student extends User{
//    private String idClass;
    private int diemGK;
    private int diemCK;
    private int diemdanh;
    private String nhanxet;
//    public User user;

    public student( String id, int diemGK, int diemCK,int diemdanh, String nhanxet) {
        super(id);
        this.diemGK = diemGK;
        this.diemCK = diemCK;
        this.diemdanh = diemdanh;
        this.nhanxet = nhanxet;
//        this.user = user;
    }




    public int getDiemdanh() {
        return diemdanh;
    }

    public void setDiemdanh(int diemdanh) {
        this.diemdanh = diemdanh;
    }

//    public String getIdClass() {
//        return idClass;
//    }
//
//    public void setIdClass(String idClass) {
//        this.idClass = idClass;
//    }

    public int getDiemGK() {
        return diemGK;
    }

    public void setDiemGK(int diemGK) {
        this.diemGK = diemGK;
    }

    public int getDiemCK() {
        return diemCK;
    }

    public void setDiemCK(int diemCK) {
        this.diemCK = diemCK;
    }

    public String getNhanxet() {
        return nhanxet;
    }

    public void setNhanxet(String nhanxet) {
        this.nhanxet = nhanxet;
    }
}
