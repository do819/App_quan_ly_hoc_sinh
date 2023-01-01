package com.example.tonghop_tuan4;

import static androidx.core.content.ContextCompat.startActivities;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tonghop_tuan4.Constructor.IconHome;

import java.util.List;

public class IconHomeAdapter extends RecyclerView.Adapter<IconHomeAdapter.MyViewHolder> {
    Context context;
    List<IconHome> array;


    public IconHomeAdapter(Context context, List<IconHome> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_icon_home,parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        IconHome iconHome = array.get(position);

        holder.tvInfo.setText(iconHome.getTvInfo());
        Glide.with(context).load(iconHome.getImaAvt()).into(holder.imaIcon);

        holder.imaIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://mindx.vn/");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);



            }
        });
//        holder.imaIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Recycle Click" + position, Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvInfo;
        ImageView imaIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInfo = itemView.findViewById(R.id.itemicon_info);
            imaIcon = itemView.findViewById(R.id.itemicon_ima);
        }
    }
}
