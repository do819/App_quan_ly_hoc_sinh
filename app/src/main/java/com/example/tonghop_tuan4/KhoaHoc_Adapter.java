package com.example.tonghop_tuan4;





import static androidx.core.content.ContextCompat.startActivity;

import com.example.tonghop_tuan4.fragment.LearningFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tonghop_tuan4.Constructor.KhoaHoc;
import com.example.tonghop_tuan4.fragment.LearningFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KhoaHoc_Adapter extends RecyclerView.Adapter<KhoaHoc_Adapter.KhoaHocViewHolder>{

    private Context context;
    private List<KhoaHoc> mListKhoaHoc;
    private View view;

    public KhoaHoc_Adapter(Context context, List<KhoaHoc> mListKhoaHoc) {
        this.context = context;
        this.mListKhoaHoc = mListKhoaHoc;

    }

//    public KhoaHoc_Adapter(List<KhoaHoc> mListKhoaHoc) {
//        this.mListKhoaHoc = mListKhoaHoc;
//    }

    @NonNull
    @Override
    public KhoaHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoahoc, parent, false);
        return new KhoaHocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoaHocViewHolder holder, int position) {
        KhoaHoc khoaHoc = mListKhoaHoc.get(position);
        if(khoaHoc == null){
            return;
        }
        Picasso.get().load(khoaHoc.getImage()).into(holder.imageView);

        holder.tv_namekhoahoc.setText(khoaHoc.getName());
        holder.tv_timekhoahoc.setText(khoaHoc.getTime());
        holder.tv_descripkhoahoc.setText(khoaHoc.getDescription());

        holder.tv_xemthemkhoahoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, ""+  khoaHoc.getId(), Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent(context, KhoaHoc_Detail.class);
                Bundle extras = new Bundle();
                extras.putInt("idkhoahoc", khoaHoc.getId());
                extras.putString("bannerkhoahoc", khoaHoc.getBanner());
                extras.putString("namekhoahoc", khoaHoc.getName());
                extras.putString("titlekhoahoc", khoaHoc.getTitle());
                extras.putString("contentkhoahoc", khoaHoc.getContent());
                intent.putExtras(extras);


                context.startActivity(intent);




            }
        });
    }




    @Override
    public int getItemCount() {
        if(mListKhoaHoc != null){
            return mListKhoaHoc.size();
        }
        return 0;
    }

    public class KhoaHocViewHolder extends  RecyclerView.ViewHolder{


        private ImageView imageView;
        private TextView tv_namekhoahoc, tv_timekhoahoc, tv_descripkhoahoc, tv_xemthemkhoahoc ;


        public KhoaHocViewHolder(@NonNull View itemView) {

            super(itemView);

            imageView = itemView.findViewById(R.id.imageView_khoahoc);
            tv_namekhoahoc = itemView.findViewById(R.id.tv_namekhoahoc);
            tv_descripkhoahoc = itemView.findViewById(R.id.tv_descripkhoahoc);
            tv_timekhoahoc = itemView.findViewById(R.id.tv_timekhoahoc);
            tv_xemthemkhoahoc = itemView.findViewById(R.id.tv_xemthemkhoahoc);
        }

    }
}
