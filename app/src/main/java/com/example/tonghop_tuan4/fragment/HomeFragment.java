package com.example.tonghop_tuan4.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.tonghop_tuan4.Constructor.IconHome;
import com.example.tonghop_tuan4.Constructor.Photo;
import com.example.tonghop_tuan4.IconHomeAdapter;
import com.example.tonghop_tuan4.PhotoAdapter;
import com.example.tonghop_tuan4.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private View rootview;
    RecyclerView recyclerViewManHinhChinh;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_home, container, false);

        super.onCreate(savedInstanceState);

        rootview =  inflater.inflate(R.layout.fragment_home, container, false);



        viewPager = rootview.findViewById(R.id.viewpager);
        circleIndicator = rootview.findViewById(R.id.circle_indicator);
        recyclerViewManHinhChinh = rootview.findViewById(R.id.recyclerview);
        
        photoAdapter = new PhotoAdapter(this.getActivity(),getListPhoto());
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());




        List<IconHome> iconHomeList = initIcon();
        IconHomeAdapter iconHomeAdapter = new IconHomeAdapter(this.getActivity(), iconHomeList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),2);
        recyclerViewManHinhChinh.setAdapter(iconHomeAdapter);
        recyclerViewManHinhChinh.setLayoutManager(gridLayoutManager);

        return rootview;

    }

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.anh1));
        list.add(new Photo(R.drawable.anh2));
        list.add(new Photo(R.drawable.anh3));

        return list;
    }

    public List<IconHome> initIcon(){
        String[] info = {"Gương mặt tiêu biểu","Sự kiện","Blog","Về chúng tôi"};
        int[] avt = {R.drawable.icon_user,R.drawable.icon_event,R.drawable.icons_blog, R.drawable.icon_about};
        List<IconHome> iconHomeList = new ArrayList<>();
        for (int i = 0; i < avt.length; i++) {
            IconHome iconHome = new IconHome();
            iconHome.setTvInfo(info[i]);
            iconHome.setImaAvt(avt[i]);
            iconHomeList.add(iconHome);


        }
        return iconHomeList;
    }
}
