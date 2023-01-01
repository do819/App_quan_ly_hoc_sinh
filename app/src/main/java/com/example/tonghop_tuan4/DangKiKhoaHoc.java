package com.example.tonghop_tuan4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tonghop_tuan4.Constructor.Class;
import com.example.tonghop_tuan4.Constructor.KhoaHoc;
import com.example.tonghop_tuan4.Constructor.User;
import com.example.tonghop_tuan4.Constructor.class_registered;
import com.example.tonghop_tuan4.Constructor.student;
import com.example.tonghop_tuan4.fragment.ProductsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DangKiKhoaHoc extends AppCompatActivity {

    private EditText edt_name_register;
    private EditText edt_email_register;
    private Button btnRegister;
    private Spinner spinner_khoahoc;
    private Spinner spinner_lophoc;

    private List<KhoaHoc> mListKhoaHoc;
    private KhoaHoc_Adapter mKhoaHoc_Adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference spinnerRefKhoaHoc;
    DatabaseReference spinnerRefLopHoc;

    ArrayList<String> spinnerListKhoaHoc;
    ArrayList<String> spinnerListLopHoc;

    ArrayAdapter<String> arrayAdapterKhoaHoc;
    ArrayAdapter<String> arrayAdapterLopHoc;

    FirebaseUser user;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//    user = FirebaseAuth.getInstance().getCurrentUser();
//    String userId = user.getUid();

    private String idKhoaHoc;
    private String idLopHoc;
    private String uid;
//    int idkhoahoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki_khoa_hoc);

        initUi();
        showDataKhoaHoc();
        showDataLopHoc();

        initListener();
    }

    private void initListener() {

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idLopHoc = spinner_lophoc.getSelectedItem().toString();
//                Toast.makeText(DangKiKhoaHoc.this, "" +idLopHoc, Toast.LENGTH_SHORT ).show();
                dangkikhoahoc();
            }
        });
    }




    private void initUi() {
        edt_name_register = findViewById(R.id.edt_name_register);
        edt_email_register = findViewById(R.id.edt_email_register);
        btnRegister = findViewById(R.id.btn_dangkikhoahoc);
        spinner_khoahoc = findViewById(R.id.spinner_khoahoc);
        spinner_lophoc = findViewById(R.id.spinner_lophoc);

        spinnerRefKhoaHoc = database.getReference("KhoaHoc");
        spinnerRefLopHoc = database.getReference("Class");

        spinnerListKhoaHoc = new ArrayList<>();
        spinnerListLopHoc = new ArrayList<>();
        mListKhoaHoc = new ArrayList<>();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        if (extras != null) {
            idKhoaHoc = extras.getString("idKhoaHoc");
            //The key argument here must match that used in the other activity
        }



        mKhoaHoc_Adapter = new KhoaHoc_Adapter(DangKiKhoaHoc.this,mListKhoaHoc);

        arrayAdapterKhoaHoc = new ArrayAdapter<String>(DangKiKhoaHoc.this, android.R.layout.simple_spinner_dropdown_item, spinnerListKhoaHoc);
        arrayAdapterLopHoc = new ArrayAdapter<String>(DangKiKhoaHoc.this, android.R.layout.simple_spinner_dropdown_item, spinnerListLopHoc);



        spinner_khoahoc.setAdapter(arrayAdapterKhoaHoc);
        spinner_lophoc.setAdapter(arrayAdapterLopHoc);




    }

    private void showDataLopHoc() {
        spinnerRefLopHoc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (spinnerListLopHoc != null){
                    spinnerListLopHoc.clear();
                }
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if ((dataSnapshot.getKey().toString().trim()).equals((idKhoaHoc.trim())  )){
//                        Class aClass = dataSnapshot.getValue(Class.class);
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            spinnerListLopHoc.add(dataSnapshot1.getKey().toString());
                        }
                        arrayAdapterLopHoc.notifyDataSetChanged();
                    }



//                    arrayAdapterLopHoc.notifyDataSetChanged();
                }
//                arrayAdapterLopHoc.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DangKiKhoaHoc.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDataKhoaHoc() {
        spinnerRefKhoaHoc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (spinnerListKhoaHoc != null){
                    spinnerListKhoaHoc.clear();
                }

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if ((dataSnapshot.getKey().toString().trim()).equals((idKhoaHoc.trim())  )){
                        KhoaHoc khoaHoc = dataSnapshot.getValue(KhoaHoc.class);

                        spinnerListKhoaHoc.add(khoaHoc.getName().toString());
                    }


                    arrayAdapterKhoaHoc.notifyDataSetChanged();

                }
                arrayAdapterKhoaHoc.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DangKiKhoaHoc.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dangkikhoahoc() {

        initStudent();
        initClassRegistered();
        sendID();
    }


    private void initStudent() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
        }
        DatabaseReference studentListRef = mDatabase.getRef().child("Class").child(""+idKhoaHoc.trim()).child(""+idLopHoc.trim()).child("student");

        student student1 = new student(uid, 0, 0, 0, "" );

        studentListRef.child(uid).setValue(student1);
    }

    private void sendID() {
        Intent intent = new Intent(DangKiKhoaHoc.this, ProductsFragment.class);
        Bundle extras = new Bundle();

        extras.putString("idKhoaHoc", idKhoaHoc);
        extras.putString("idLopHoc", idLopHoc);
        intent.putExtras(extras);
//        startActivity(intent);

    }


    private void initClassRegistered() {
        DatabaseReference userListRef = mDatabase.getRef().child("User");

        DatabaseReference classListRef = mDatabase.getRef().child("User").child(""+uid).child("class_registered").child(""+idKhoaHoc);
        DatabaseReference classReListRef = mDatabase.getRef().child("User").child(""+uid).child("class_registered");



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
        }

//        class_registered class_registered1 = new class_registered();
//
        class_registered class_registered = new class_registered(""+idKhoaHoc,"" +idLopHoc);
//        class_registered class_registered1 = new class_registered(null);

//        classReListRef.child(""+idKhoaHoc).setValue(idLopHoc);
        classReListRef.child(""+idKhoaHoc).setValue(class_registered);
//        userListRef.child(uid).child("class_registered").setValue(class_registered);
//
//
//        userListRef.child(uid).child("class_registered").child(""+idKhoaHoc).setValue(idLopHoc);
//        userListRef.child(uid).child("class_registered").setValue(class_registered);
////        userListRef
//
//
//
//        HashMap<String, Object> params = new HashMap<>();
//        params.put(""+idKhoaHoc, idLopHoc);
//        classListRef.updateChildren(""+idKhoaHoc,idLopHoc);
//        classReListRef.updateChildren(params);

//        userListRef.child(uid).child("class_registered").child(""+idKhoaHoc).
    }

}