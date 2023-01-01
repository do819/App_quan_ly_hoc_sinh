package com.example.tonghop_tuan4;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tonghop_tuan4.Constructor.class_registered;
import com.example.tonghop_tuan4.Constructor.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Register_Page extends AppCompatActivity {

    private EditText edtMail, edtPassword, edtFullname ;
    private Button btnSignUp;

    private ProgressDialog progressDialog;
    public static List<User> users;

    FirebaseUser user;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
////    String userId = user.getUid();
//    DatabaseReference userListRef = mDatabase.getRef();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);


        initUi();
        initListener();
    }

    private void initUi(){
        edtFullname = findViewById(R.id.edt_fullname);
        edtMail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);

        btnSignUp = findViewById(R.id.btn_register);

        progressDialog = new ProgressDialog(this);
    }

    private void initListener() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignUp();
            }
        });
        
    }

    private void onClickSignUp() {
        String strEmail = edtMail.getText().toString().trim();
        String strPassword = edtPassword.getText().toString().trim();

        //theem logic check email and password not null //

        if(validation(strEmail, strPassword)){
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(strEmail, strPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                user = FirebaseAuth.getInstance().getCurrentUser();
                                String userId = user.getUid();

                                System.out.println("User id"+ userId);

                                initUser(userId);
//                                initInformationUser(userId);
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(Register_Page.this, "Sign in ",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register_Page.this, MainActivity.class);

                                startActivity(intent);
                                finishAffinity();
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(Register_Page.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }

    }

    // ntd23082001@gmail.com
    // 123456789
    private void initUser(String userId) {
        DatabaseReference userListRef = mDatabase.getRef().child("User");

        User user = new User(userId,edtFullname.getText().toString().trim(), edtMail.getText().toString().trim(), null,edtPassword.getText().toString().trim() );

        userListRef.child(userId).setValue(user);


//        class_registered class_registered = new class_registered("");
//        userListRef.child(userId).child("class_registered").setValue(class_registered);





    }





    private boolean validation(String email, String password) {
        if(isValidEmail(email) == false | email == null){
            edtMail.setError("Invalid email");
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