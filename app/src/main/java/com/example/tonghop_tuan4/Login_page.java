package com.example.tonghop_tuan4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_page extends AppCompatActivity {


    private LinearLayout layoutSignUp;

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private ProgressDialog progressDialog;
    private LinearLayout layoutForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        initUi();
        initListener();
    }

    private void initUi(){
        progressDialog = new ProgressDialog(this);

        layoutSignUp = findViewById(R.id.layout_sign_up);

        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);

        btnLogin = findViewById(R.id.btn_login);
        layoutForgotPassword = findViewById(R.id.layout_forgot_password);
    }

    private void initListener() {
        layoutSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_page.this, Register_Page.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignIn();
            }
        });

        layoutForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickForgotPassword();
            }
        });
    }

    private void onClickSignIn() {
        //check string để thông báo
        String strEmail = edtEmail.getText().toString().trim();
        String strPassword = edtPassword.getText().toString().trim();

        if(validation(strEmail,strPassword)) {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            progressDialog.show();
            auth.signInWithEmailAndPassword(strEmail, strPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {

                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(Login_page.this, MainActivity.class);
                                startActivity(intent);
                                finishAffinity();
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(Login_page.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }


    private void onClickForgotPassword() {
        progressDialog.show();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String strEmail = edtEmail.getText().toString().trim();

        auth.sendPasswordResetEmail(strEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
//                            Log.d(TAG, "Email sent.");
                            Toast.makeText(Login_page.this, "Email sent", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private boolean validation(String email, String password) {
        if(isValidEmail(email) == false | email == null){
            edtEmail.setError("Invalid email");
            return false;
        }else if(password.length() < 6 | password.length() == 0){
            edtPassword.setError("Password must be at least 6 character");
            return false;
        }else{
            return true;
        }
    }

    public boolean isValidEmail(CharSequence target) {
        if(!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()){
            return true;
        }else{
            return false;
        }
    }



}