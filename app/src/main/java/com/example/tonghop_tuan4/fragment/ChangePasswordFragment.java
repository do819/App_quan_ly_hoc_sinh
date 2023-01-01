package com.example.tonghop_tuan4.fragment;

import static android.widget.Toast.LENGTH_SHORT;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tonghop_tuan4.Login_page;
import com.example.tonghop_tuan4.MainActivity;
import com.example.tonghop_tuan4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordFragment extends Fragment {

    private View mView;

    private EditText edtOldPass, edtNewPass, edtConfirmNewPass;
    private EditText edtEmail_Dialog, edtPass_Dialog;
    private Button btnUpdatePassword;

    private ProgressDialog progressDialog;

    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_change_password, container, false);

        initUi();

        progressDialog = new ProgressDialog(getActivity());

        initListener();
        return mView;


    }

    private void initUi() {
        edtOldPass = mView.findViewById(R.id.edt_password_old);
        edtNewPass = mView.findViewById(R.id.edt_password_new);
        edtConfirmNewPass = mView.findViewById(R.id.edt_password_new_confirm);

        btnUpdatePassword = mView.findViewById(R.id.btn_update_password_new);


    }

    private void initListener() {
        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdatePassword();
            }
        });
    }

    private void onClickUpdatePassword() {

        String strNewPassword = edtNewPass.getText().toString().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String newPassword = "SOME-SECURE-PASSWORD";
        progressDialog.show();
        user.updatePassword(strNewPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "User password updated.");
                            Toast.makeText(getActivity(), "User password updated.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //Toast.makeText(getActivity(), "User password not work.", Toast.LENGTH_SHORT).show();
                            // Xuat dialogAuthentication
//                            Intent intent = new Intent(ChangePasswordFragment.this, MainActivity.class);
//                            startActivity(intent);
                                openDialog(Gravity.CENTER);

                        }
                    }
                });

    }

    private void reAuthenticate(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String strEmail2 = edtEmail_Dialog.getText().toString().trim();
        String strPassword2 = edtPass_Dialog.getText().toString().trim();



        AuthCredential credential = EmailAuthProvider
                    .getCredential(strEmail2, strPassword2);


        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            onClickUpdatePassword();
                        }
                        //Log.d(TAG, "User re-authenticated.");
                    }
                });
    }

    private void openDialog(int gravity) {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_email_password);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        }
        else {
            dialog.setCancelable(false);
        }

        Button btnSend = dialog.findViewById(R.id.btn_send_email_and_password);
        edtEmail_Dialog = dialog.findViewById(R.id.edt_email_2);
        edtPass_Dialog = dialog.findViewById(R.id.edt_password_2);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reAuthenticate();
                dialog.dismiss();
            }
        });


        dialog.show();

    }

}
