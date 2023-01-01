package com.example.tonghop_tuan4.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.tonghop_tuan4.BuildConfig;
import com.example.tonghop_tuan4.MainActivity;
import com.example.tonghop_tuan4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MyProfileFragment extends Fragment {

    public static final int MY_REQUEST_CODE = 10;
    private View mView;
    private ImageView imgAvatar;
    private EditText edtFullname, edtEmail;
    private Button btnUpdateProfile;
    private Button btnUpdateEmail;

    private Uri mUri;

    private MainActivity mMainActivity;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my_profile, container, false);

        initUi();
        mMainActivity =(MainActivity)getActivity();
        progressDialog = new ProgressDialog(getActivity());
        setUserInformation();

        initListener();
        return mView;
    }

    public void setmUri(Uri mUri) {
        this.mUri = mUri;
    }

    private void initListener() {
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClickUpdateProfile();

            }
        });

        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdateEmail();
            }
        });
    }




    private void onClickRequestPermission() {

        if(mMainActivity == null){
            return;
        }

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            mMainActivity.openGallery();
            return;
        }

        if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            mMainActivity.openGallery();
        }else{
            String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            getActivity().requestPermissions(permissions, MY_REQUEST_CODE);
        }
    }

    private void setUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            return;
        }

        edtFullname.setText(user.getDisplayName());
        edtEmail.setText(user.getEmail());
        Glide.with(getActivity()).load(user.getPhotoUrl()).error(R.drawable.ic_avatar_default).into(imgAvatar);

    }

    private void initUi(){
        imgAvatar = mView.findViewById(R.id.img_avatar);
        edtFullname = mView.findViewById(R.id.edt_fullname);
        edtEmail = mView.findViewById(R.id.edt_email);
        btnUpdateProfile = mView.findViewById(R.id.btn_update_profile);
        btnUpdateEmail = mView.findViewById(R.id.btn_update_email);

    }

    public void setBitMapImageView(Bitmap bitMapImageView){
        imgAvatar.setImageBitmap(bitMapImageView);
    }

    private void onClickUpdateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            return;
        }
        progressDialog.show();

        String strFullName = edtFullname.getText().toString().trim();


        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(strFullName)
                .setPhotoUri(mUri)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "User profile updated.", Toast.LENGTH_SHORT).show();
                            mMainActivity.showUserInformation();
                        }
                    }
                });

    }
    private void onClickUpdateEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String strNewEmail = edtEmail.getText().toString().trim();
        progressDialog.show();

        user.updateEmail(strNewEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "User email address updated.", Toast.LENGTH_SHORT).show();
                            mMainActivity.showUserInformation();
                        }
                    }
                });
    }



}
