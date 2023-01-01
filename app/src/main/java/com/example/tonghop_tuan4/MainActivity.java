package com.example.tonghop_tuan4;

import static com.example.tonghop_tuan4.fragment.MyProfileFragment.MY_REQUEST_CODE;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tonghop_tuan4.fragment.AboutFragment;
import com.example.tonghop_tuan4.fragment.BlogFragment;
import com.example.tonghop_tuan4.fragment.ChangePasswordFragment;
import com.example.tonghop_tuan4.fragment.HomeFragment;
import com.example.tonghop_tuan4.fragment.LearningFragment;
import com.example.tonghop_tuan4.fragment.MyProfileFragment;
import com.example.tonghop_tuan4.fragment.ProductsFragment;
import com.example.tonghop_tuan4.fragment.StoryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_LEARNING = 1;
    private static final int FRAGMENT_PRODUCTS = 2;
    private static final int FRAGMENT_STORY = 3;
    private static final int FRAGMENT_BLOG = 4;
    private static final int FRAGMENT_ABOUT = 5;
    private static final int FRAGMENT_MY_PROFILE = 6;
    private static final int FRAGMENT_CHANGE_PASSWORD = 7;




    private int mCurrentFragment = FRAGMENT_HOME;

    private NavigationView mNavigationView;
    private BottomNavigationView mBottomNavigationView;

    private DrawerLayout mDrawerLayout;

    private ImageView imgAvatar;
    private TextView tvName,tvEmail;

    final private MyProfileFragment mMyProfileFragment = new MyProfileFragment();

    final private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                Intent intent = result.getData();
                if(intent == null){
                    return;
                }
                Uri uri = intent.getData();
                mMyProfileFragment.setmUri(uri);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    mMyProfileFragment.setBitMapImageView(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = findViewById(R.id.nagivation_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.nav_bot_home){
                    if (mCurrentFragment != FRAGMENT_HOME){
                        replaceFragment(new HomeFragment());
                        mCurrentFragment = FRAGMENT_HOME;
//                        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        mBottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                    }

                }else if(id == R.id.nav_bot_learing){
                    if (mCurrentFragment != FRAGMENT_LEARNING){
                        replaceFragment(new LearningFragment());
                        mCurrentFragment = FRAGMENT_LEARNING;
                        mNavigationView.getMenu().findItem(R.id.nav_learing).setChecked(true);
                    }
                }else if(id == R.id.nav_bot_products){
                    if (mCurrentFragment != FRAGMENT_PRODUCTS){
                        replaceFragment(new ProductsFragment());
                        mCurrentFragment = FRAGMENT_PRODUCTS;
                        mNavigationView.getMenu().findItem(R.id.nav_products).setChecked(true);
                    }
                }
//                else if(id == R.id.nav_bot_story){
//                    if (mCurrentFragment != FRAGMENT_STORY){
//                        replaceFragment(new StoryFragment());
//                        mCurrentFragment = FRAGMENT_STORY;
//                        mNavigationView.getMenu().findItem(R.id.nav_story).setChecked(true);
//                    }
//                }
                else if(id == R.id.nav_bot_blog){
                    if (mCurrentFragment != FRAGMENT_BLOG){
                        replaceFragment(new BlogFragment());
                        mCurrentFragment = FRAGMENT_BLOG;
                        mNavigationView.getMenu().findItem(R.id.nav_blog).setChecked(true);
                    }
                }

                return true;
            }
        });

        replaceFragment(new HomeFragment());
        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        mBottomNavigationView.getMenu().findItem(R.id.nav_bot_home).setChecked(true);
        setTitleToolbar();

        showUserInformation();

    }

    private void initUi(){
        mNavigationView = findViewById(R.id.nagivation_view);

        imgAvatar = mNavigationView.getHeaderView(0).findViewById(R.id.img_avatar);
        tvName = mNavigationView.getHeaderView(0).findViewById(R.id.tv_name);
        tvEmail = mNavigationView.getHeaderView(0).findViewById(R.id.tv_email);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            if (mCurrentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                mCurrentFragment = FRAGMENT_HOME;

            }
            mBottomNavigationView.getMenu().findItem(R.id.nav_bot_home).setChecked(true);

        }else if(id == R.id.nav_learing){
            if (mCurrentFragment != FRAGMENT_LEARNING){
                replaceFragment(new LearningFragment());
                mCurrentFragment = FRAGMENT_LEARNING;

            }
            mBottomNavigationView.getMenu().findItem(R.id.nav_bot_learing).setChecked(true);
        }else if(id == R.id.nav_products){
            if (mCurrentFragment != FRAGMENT_PRODUCTS){
                replaceFragment(new ProductsFragment());
                mCurrentFragment = FRAGMENT_PRODUCTS;

            }
            mBottomNavigationView.getMenu().findItem(R.id.nav_bot_products).setChecked(true);
        }
//        else if(id == R.id.nav_story){
//            if (mCurrentFragment != FRAGMENT_STORY){
//                replaceFragment(new StoryFragment());
//                mCurrentFragment = FRAGMENT_STORY;
//
//            }
//            mBottomNavigationView.getMenu().findItem(R.id.nav_bot_story).setChecked(true);
//        }
        else if(id == R.id.nav_blog){
            if (mCurrentFragment != FRAGMENT_BLOG){
                replaceFragment(new BlogFragment());
                mCurrentFragment = FRAGMENT_BLOG;

            }
            mBottomNavigationView.getMenu().findItem(R.id.nav_bot_blog).setChecked(true);
        }
//        else if(id == R.id.nav_about){
//            if (mCurrentFragment != FRAGMENT_ABOUT){
//                replaceFragment(new AboutFragment());
//                mCurrentFragment = FRAGMENT_ABOUT;
//
//            }
//
//        }
        else if(id == R.id.nav_sign_out){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, Login_page.class);
            startActivity(intent);
            finishAffinity();
        }

        else if(id == R.id.nav_my_profile){
            if (mCurrentFragment != FRAGMENT_MY_PROFILE){
                replaceFragment(mMyProfileFragment);
                mCurrentFragment = FRAGMENT_MY_PROFILE;


            }
            mNavigationView.getMenu().findItem(R.id.nav_my_profile).setChecked(true);
        }

        else if(id == R.id.nav_change_password){
            if (mCurrentFragment != FRAGMENT_CHANGE_PASSWORD){
                replaceFragment(new ChangePasswordFragment());
                mCurrentFragment = FRAGMENT_CHANGE_PASSWORD;


            }
            mNavigationView.getMenu().findItem(R.id.nav_change_password).setChecked(true);
        }

        setTitleToolbar();
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }


    private void setTitleToolbar(){
        String title = "";
        switch (mCurrentFragment){
            case FRAGMENT_HOME:
                title = getString(R.string.nav_home);
                break;

            case FRAGMENT_LEARNING:
                title = getString(R.string.nav_learning);
                break;

            case FRAGMENT_PRODUCTS:
                title = getString(R.string.nav_products);
                break;

            case FRAGMENT_STORY:
                title = getString(R.string.nav_story);
                break;

            case FRAGMENT_BLOG:
                title = getString(R.string.nav_blog);
                break;

            case FRAGMENT_ABOUT:
                title = getString(R.string.nav_about);
                break;

            case FRAGMENT_MY_PROFILE:
                title = getString(R.string.nav_my_profile);
                break;

            case FRAGMENT_CHANGE_PASSWORD:
                title = getString(R.string.nav_change_password);
                break;

        }

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
    }

    public void showUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        // Name, email address, and profile photo Url
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        // Check if user's email is verified
        //boolean emailVerified = user.isEmailVerified();

        // The user's ID, unique to the Firebase project. Do NOT use this value to
        // authenticate with your backend server, if you have one. Use
        // FirebaseUser.getIdToken() instead.
        //String uid = user.getUid();

        if (name == null){
            tvName.setVisibility(View.GONE);
        }
        else {
            tvName.setVisibility(View.VISIBLE);
        }

        tvName.setText(name);
        tvEmail.setText((email));

        Glide.with(this).load(photoUrl).error(R.drawable.ic_avatar_default).into(imgAvatar);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                openGallery();
            }
            else {
                Toast.makeText(this, "Vui long cho phep", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select picture"));

    }
}