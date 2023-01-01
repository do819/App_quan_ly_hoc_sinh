package com.example.tonghop_tuan4.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tonghop_tuan4.Constructor.KhoaHoc;
import com.example.tonghop_tuan4.Constructor.class_registered;
import com.example.tonghop_tuan4.KhoaHoc_Adapter;
import com.example.tonghop_tuan4.LopRegistered_Adapter;
import com.example.tonghop_tuan4.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {
    private RecyclerView rcvlopRegistered;
    private LopRegistered_Adapter mLopRegistered_Adapter;
    private View rootview, itemView;
    private List<class_registered> mListLopRegistered;

    private ArrayList<String> mListLopRegistered1;
    ArrayAdapter<String> arrayAdapterLopRegistered1;

    private TextView xemthem;
    private String uid;
    private String idKhoaHocRegistered;
    private String idLopHocRegistered;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootview =  inflater.inflate(R.layout.fragment_products, container, false);
        itemView =  inflater.inflate(R.layout.item_khoahoc_registered, container, false);

        initUi();

        getListKhoaHocFromRealtimeDatabase();
//        initClickListener();

//        System.out.println(mListKhoaHoc);
        return rootview;

    }

    private void initUi() {
        rcvlopRegistered = (RecyclerView)rootview.findViewById(R.id.rcv_khoahoccuatoi);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        rcvlopRegistered.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration =new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL);
        rcvlopRegistered.addItemDecoration(dividerItemDecoration);

        mListLopRegistered = new ArrayList<>();
        mLopRegistered_Adapter = new LopRegistered_Adapter(this.getActivity(),mListLopRegistered);

        mListLopRegistered1 = new ArrayList<>();
//        arrayAdapterLopRegistered1 =new ArrayAdapter<>(ProductsFragment.this, );

        rcvlopRegistered.setAdapter(mLopRegistered_Adapter);

        xemthem = (TextView)itemView.findViewById(R.id.tv_xemthemRegistered);
//        System.out.println(" 1: "+xemthem);

    }
    private void getListKhoaHocFromRealtimeDatabase(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();


        DatabaseReference myRef = database.getReference("User").child(""+uid).child("class_registered");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (mListLopRegistered != null){
                    mListLopRegistered.clear();
                }
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    class_registered class_registered1 = dataSnapshot.getValue(class_registered.class);
//                    class_registered class_registered = new class_registered("","");
//                    idKhoaHocRegistered = dataSnapshot.getKey().toString().trim();
//                    idLopHocRegistered = dataSnapshot.getValue().toString().trim();
//                    class_registered.setIdKhoaHocRegistered(dataSnapshot.getKey().toString().trim());
//                    class_registered.setIdClassRegistered(dataSnapshot.getValue().toString().trim());
                    mListLopRegistered.add(class_registered1);


                }

                mLopRegistered_Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }




}