package com.example.tonghop_tuan4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tonghop_tuan4.Constructor.KhoaHoc;
import com.example.tonghop_tuan4.Constructor.class_registered;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LopRegistered_Adapter extends RecyclerView.Adapter<LopRegistered_Adapter.LopRegisteredViewHolder>{

    private Context context;
    private List<class_registered> mListLopRegistered;
    private View view;

    public LopRegistered_Adapter(Context context, List<class_registered> mListLopRegistered) {
        this.context = context;
        this.mListLopRegistered = mListLopRegistered;

    }

//    public KhoaHoc_Adapter(List<KhoaHoc> mListKhoaHoc) {
//        this.mListKhoaHoc = mListKhoaHoc;
//    }

    @NonNull
    @Override
    public LopRegisteredViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoahoc_registered, parent, false);
        return new LopRegisteredViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LopRegistered_Adapter.LopRegisteredViewHolder holder, int position) {
        class_registered class_registered = mListLopRegistered.get(position);
        if(class_registered == null){
            return;
        }

        holder.tv_khoahocRegistered.setText("Mã khoá học: "+class_registered.getIdKhoaHocRegistered());
        holder.tv_lopRegistered.setText("Mã lớp học: "+class_registered.getIdClassRegistered());


        holder.tv_xemthemRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
////                Toast.makeText(context, ""+  khoaHoc.getId(), Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent(context, Lophoccuatoi.class);
                Bundle extras = new Bundle();
//                extras.putInt("idkhoahoc", khoaHoc.getId());
                extras.putString("idKhoaHoc", class_registered.getIdKhoaHocRegistered());
                extras.putString("idLopHoc", class_registered.getIdClassRegistered());
//                extras.putString("titlekhoahoc", khoaHoc.getTitle());
//                extras.putString("contentkhoahoc", khoaHoc.getContent());
                intent.putExtras(extras);

                context.startActivity(intent);
//



            }
        });
    }




    @Override
    public int getItemCount() {
        if(mListLopRegistered != null){
            return mListLopRegistered.size();
        }
        return 0;
    }

    public class LopRegisteredViewHolder extends  RecyclerView.ViewHolder{


        private TextView tv_khoahocRegistered, tv_lopRegistered, tv_xemthemRegistered ;


        public LopRegisteredViewHolder(@NonNull View itemView) {

            super(itemView);

            tv_khoahocRegistered = itemView.findViewById(R.id.tv_khoahocRegistered);
            tv_lopRegistered = itemView.findViewById(R.id.tv_lopRegistered);

            tv_xemthemRegistered = itemView.findViewById(R.id.tv_xemthemRegistered);
        }

    }
}