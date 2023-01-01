package com.example.tonghop_tuan4.fragment;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tonghop_tuan4.Constructor.KhoaHoc;
import com.example.tonghop_tuan4.KhoaHoc_Adapter;
import com.example.tonghop_tuan4.KhoaHoc_Detail;
import com.example.tonghop_tuan4.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class LearningFragment extends Fragment {

    private RecyclerView rcvKhoaHoc;
    private KhoaHoc_Adapter mKhoaHoc_Adapter;
    private View rootview, itemView;
    private List<KhoaHoc> mListKhoaHoc;
    private TextView xemthem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootview =  inflater.inflate(R.layout.fragment_learning, container, false);
        itemView =  inflater.inflate(R.layout.item_khoahoc, container, false);

        initUi();

        getListKhoaHocFromRealtimeDatabase();
//        initClickListener();

//        System.out.println(mListKhoaHoc);
        return rootview;
    }

    private void initUi(){

        rcvKhoaHoc = (RecyclerView)rootview.findViewById(R.id.rcv_khoahoc);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        rcvKhoaHoc.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration =new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL);
        rcvKhoaHoc.addItemDecoration(dividerItemDecoration);

        mListKhoaHoc = new ArrayList<>();
        mKhoaHoc_Adapter = new KhoaHoc_Adapter(this.getActivity(),mListKhoaHoc);

        rcvKhoaHoc.setAdapter(mKhoaHoc_Adapter);

        xemthem = (TextView)itemView.findViewById(R.id.tv_xemthemkhoahoc);
//        System.out.println(" 1: "+xemthem);


    }


//    private void initClickListener() {
//        xemthem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "Ping", Toast.LENGTH_SHORT).show();
//                onClickKhoaHocDetail();
//            }
//        });
//    }
//
//    private void onClickKhoaHocDetail() {
//        Intent intent = new Intent(this.getActivity(), KhoaHoc_Detail.class);
//        startActivity(intent);
//    }

    private void getListKhoaHocFromRealtimeDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("KhoaHoc");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (mListKhoaHoc != null){
                    mListKhoaHoc.clear();
                }
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    KhoaHoc khoaHoc = dataSnapshot.getValue(KhoaHoc.class);
                    mListKhoaHoc.add(khoaHoc);


                }

                mKhoaHoc_Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
