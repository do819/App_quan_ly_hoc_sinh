package com.example.tonghop_tuan4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tonghop_tuan4.Constructor.Class;
import com.example.tonghop_tuan4.Constructor.KhoaHoc;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Lophoccuatoi extends AppCompatActivity {

    private TextView tv_nameclassregistered, tv_ngaybatdau, tv_ngayketthuc, tv_diemGK, tv_diemCK, tv_diemdanh, tv_nhanxet;
    String uid, idKhoaHoc, idLopHoc;
    private List<Class> mListLopHoc;
    String nameclassregistered, ngaybatdau, ngayketthuc, diemGK, diemCK, diemdanh, nhanxet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lophoccuatoi);

        initUi();
        initListener();
    }

    private void initListener() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Class").child(""+idKhoaHoc).child(""+idLopHoc);

//        nameclassregistered = myRef.child().toString();
//        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DataSnapshot snapshot = task.getResult();
//                    nameclassregistered = snapshot.getValue(String.class);
//                    tv_nameclassregistered.setText(nameclassregistered);
//                    Log.d("TAG", nameclassregistered);
//                } else {
//                    Log.d("TAG", task.getException().getMessage()); //Don't ignore potential errors!
//                }
//            }
//        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    Class class1 = snapshot.getValue(Class.class);
                nameclassregistered = snapshot.child("name").getValue().toString().trim();
                tv_nameclassregistered.setText(nameclassregistered);

                ngaybatdau = snapshot.child("ngaylap").getValue().toString().trim();
                tv_ngaybatdau.setText(ngaybatdau);

                ngayketthuc = snapshot.child("ngayketthuc").getValue().toString().trim();
                tv_ngayketthuc.setText(ngayketthuc);

                DatabaseReference myRef1 = myRef.child("student").child(""+uid);
                myRef1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        diemGK = snapshot.child("diemGK").getValue().toString().trim();
                        tv_diemGK.setText(diemGK);

                        diemCK = snapshot.child("diemCK").getValue().toString().trim();
                        tv_diemCK.setText(diemCK);

                        diemdanh = snapshot.child("diemdanh").getValue().toString().trim();
                        tv_diemdanh.setText(diemdanh);

                        nhanxet = snapshot.child("nhanxet").getValue().toString().trim();
                        tv_nhanxet.setText(nhanxet);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//                nameclassregistered = snapshot.child("name").getValue().toString().trim();
//                tv_nameclassregistered.setText(nameclassregistered);
//
//                nameclassregistered = snapshot.child("name").getValue().toString().trim();
//                tv_nameclassregistered.setText(nameclassregistered);
//
//                nameclassregistered = snapshot.child("name").getValue().toString().trim();
//                tv_nameclassregistered.setText(nameclassregistered);
//
//                nameclassregistered = snapshot.child("name").getValue().toString().trim();
//                tv_nameclassregistered.setText(nameclassregistered);
//                    Log.d("TAG", nameclassregistered);
//                nameclassregistered = snapshot.toString().trim();

//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

//                    mListLopHoc.add(class1);
//                    nameclassregistered = snapshot.toString();
//                    nameclassregistered = dataSnapshot.getValue().toString().trim();
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//
//        tv_namekhoahoc.setText(tvname);
//        tv_titlekhoahoc.setText(tvtitle);
//        tv_contentkhoahoc.setText(tvcontent);


//        Log.d("TAG", nameclassregistered);
//        tv_nameclassregistered.setText(nameclassregistered);
    }

    private void initUi() {

        tv_nameclassregistered = (TextView) findViewById(R.id.tv_nameclassregistered);
        tv_ngaybatdau = (TextView) findViewById(R.id.tv_ngaybatdau);
        tv_ngayketthuc = (TextView) findViewById(R.id.tv_ngayketthuc);
        tv_diemGK = (TextView) findViewById(R.id.tv_diemGK);
        tv_diemCK = (TextView) findViewById(R.id.tv_diemCK);
        tv_diemdanh = (TextView) findViewById(R.id.tv_diemdanh);
        tv_nhanxet = (TextView) findViewById(R.id.tv_nhanxet);




        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        idKhoaHoc = extras.getString("idKhoaHoc");
        idLopHoc = extras.getString("idLopHoc");

        mListLopHoc = new ArrayList<>();




    }






}
