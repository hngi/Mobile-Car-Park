package com.carpark.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.carpark.Api.Responses.BaseDataResponse;
import com.carpark.Model.User;
import com.carpark.R;
import com.carpark.utils.Commons;
import com.carpark.utils.SharePreference;
import com.carpark.views.homefragments.DefaultFragment;
import com.carpark.views.homefragments.MyVehicleFragment;
import com.carpark.views.homefragments.ParkingHistoryFragment;
import com.carpark.views.homefragments.PaymentMethodsFragment;
import com.carpark.views.homefragments.PromotionFragment;
import com.carpark.views.homefragments.SettingsFragment;
import com.facebook.login.LoginManager;
import com.google.android.material.navigation.NavigationView;

;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.carpark.utils.Commons.*;

public class HomeActivity extends BaseActivity {

    //widgets
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private boolean mToolBarNavigationListenerIsRegistered = false;
    private TextView navText;
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        fetchUserDetails();
        setUpDefaultFragment();
        navigationClickListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        String name = getIntent().getStringExtra("name");
        if(name!=null){
            Fragment frag = new MyVehicleFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.home_frame, frag).commit();
            enableBackViews(true);
            getSupportActionBar().setTitle("My Vehicle");
        }
    }

    private void fetchUserDetails() {
        String token = getSharePref().getAccessToken();
        getParkingApi().getProfileDetails(token).enqueue(new Callback<BaseDataResponse<User>>() {
            @Override
            public void onResponse(Call<BaseDataResponse<User>> call, Response<BaseDataResponse<User>>response) {
                if(response.isSuccessful()){
                    setUser(response.body().getData());
                    String name = getUser().getFirstName() + " " + getUser().getLastName();
                    navText.setText(name);
                }

                else {

                }

            }

            @Override
            public void onFailure(Call<BaseDataResponse<User>> call, Throwable t) {

            }
        });
    }

    private void initViews() {
        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        headerView = navigationView.getHeaderView(0);
        navText = headerView.findViewById(R.id.nav_drawer_name);

    }

    private void setUpDefaultFragment() {
        DefaultFragment defaultFragment = new DefaultFragment();
        setUpFragment(defaultFragment);
        toolbar.setTitle(null);
    }


    private void navigationClickListeners() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                String title = "";
                switch (item.getItemId()) {

                    case R.id.nav_notification:
                        title = "Notificatins";
                        fragment = new NotificationFragment();
                        break;

                    case R.id.nav_parking_history:
                        title = "Parking History ";
                        fragment = new ParkingHistoryFragment();
                        break;

                    case R.id.nav_pay:
                        title = "Payment Methods";
                        fragment = new PaymentMethodsFragment();
                        break;

                    case R.id.nav_prom:
                        title = "Promotions";
                        fragment = new PromotionFragment();
                        break;

                    case R.id.nav_car:
                        title = "My Vehicle";
                        fragment = new MyVehicleFragment();
                        break;

                    case R.id.nav_settings:
                       startActivity(new Intent(HomeActivity.this, SettingsActivity.class));

                    case R.id.nav_sign_out:
                        title = "Logout";
                        signout();
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                navigationView.setCheckedItem(item);
                if (fragment != null) {
                    setUpFragment(fragment);
                    enableBackViews(true);
                    toolbar.setTitle(title);

                }
                return true;
            }
        });

    }

    public void setUpFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.home_frame, fragment).commit();

    }


    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.home_frame);
        if(f instanceof DefaultFragment ){
            finish();
        }
        else {
            setUpDefaultFragment();
            enableBackViews(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment frag = new MyVehicleFragment();
        Fragment frag2 = new DefaultFragment();
        if((requestCode == 10001) && (resultCode == Activity.RESULT_OK)) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.detach(frag2).attach(frag).commit();
        }
    }

    private void signout() {
        // Facebook logout
        if (LoginManager.getInstance() != null) {
            LoginManager.getInstance().logOut();
        }

        SharePreference.getINSTANCE(getApplicationContext()).setIsUserLoggedIn(false);
        SharePreference.getINSTANCE(getApplicationContext()).setAccesstoken("null");
        Intent logout = new Intent(getApplicationContext(), GetStarted.class);
        logout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logout);
        finish();
    }

    private void enableBackViews(boolean enable) {

        if (enable) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.setDrawerIndicatorEnabled(false);

            final Drawable upArrow = getResources().getDrawable(R.drawable.ic_action_back);
            upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            if (!mToolBarNavigationListenerIsRegistered) {
                toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });

                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toggle.setDrawerIndicatorEnabled(true);
            toggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;

            toolbar.setBackgroundColor(getResources().getColor(R.color.color_white));        }
    }

}
