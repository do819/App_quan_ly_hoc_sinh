package com.example.tonghop_tuan4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tonghop_tuan4.Constructor.KhoaHoc;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.Console;
import java.time.temporal.Temporal;
import java.util.List;

public class KhoaHoc_Detail extends AppCompatActivity {

    private List<KhoaHoc> mListKhoaHoc;
    private ImageView imageView_bannerkhoahoc;
    private TextView tv_namekhoahoc,tv_titlekhoahoc, tv_contentkhoahoc;
    private Button btn_dangkikhoahoc;
    int idkhoahoc;
    String tvname, tvtitle,tvcontent,tvbanner;
    String idKhoaHoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoa_hoc_detail);

        initUi();
        initListener();


//        Toast.makeText(this, ""  + idkhoahoc, Toast.LENGTH_SHORT).show();


    }

    private void initListener() {
        Picasso.get().load(tvbanner).into(imageView_bannerkhoahoc);
        tv_namekhoahoc.setText(tvname);
        tv_titlekhoahoc.setText(tvtitle);
        tv_contentkhoahoc.setText(tvcontent);

        btn_dangkikhoahoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KhoaHoc_Detail.this, DangKiKhoaHoc.class);
                intent.putExtra("idKhoaHoc", idKhoaHoc);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initUi() {
        imageView_bannerkhoahoc = (ImageView) findViewById(R.id.imageView_bannerkhoahoc);
        tv_namekhoahoc = (TextView) findViewById(R.id.tv_namekhoahocdt);
        tv_titlekhoahoc = (TextView) findViewById(R.id.tv_titlekhoahoc);
        tv_contentkhoahoc = (TextView) findViewById(R.id.tv_contentkhoahoc);

        btn_dangkikhoahoc = findViewById(R.id.btn_dangkikhoahoc);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        idkhoahoc = extras.getInt("idkhoahoc");
        tvname = extras.getString("namekhoahoc");
        tvtitle = extras.getString("titlekhoahoc");
        tvcontent = extras.getString("contentkhoahoc");
        tvbanner = extras.getString("bannerkhoahoc");

        idKhoaHoc = ("0"+idkhoahoc);
//        tvname = intent.getStringExtra("namekhoahoc");
//        tvtitle = intent.getStringExtra("titlekhoahoc");
//        tvcontent = intent.getStringExtra("contentkhoahoc");
//        tvbanner = intent.getStringExtra("bannerkhoahoc");






//        Toast.makeText(this, ""  + tvtitle , Toast.LENGTH_SHORT).show();




    }






}