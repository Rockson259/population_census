package com.example.population_census.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.example.population_census.R;
import com.example.population_census.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, mBinding.drawerLayout,
                mBinding.toolbar,R.string.drawer_open, R.string.drawer_close);

        mBinding.navigationView.setNavigationItemSelectedListener(this::onMenuItemSelected);
        mBinding.navigationView.setCheckedItem(R.id.profileMenu);

        //Initial Replacement
        switchFragment(new ProfileFragment());
        mBinding.toolbar.setVisibility(View.INVISIBLE);
    }

    private boolean onMenuItemSelected(@NonNull MenuItem menuItem){


        switch (menuItem.getItemId()){
            case R.id.profileMenu:

                switchFragment(new ProfileFragment());
                return true;


            case R.id.logOutMenu:
                AlertDialog.Builder  builder =
                        new AlertDialog.Builder(this)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                })
                                .setTitle("Are you sure you want to Quit?");
                builder.show();

            default:return false;
        }
    }

    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentFrameLayout, fragment)
                .commit();
        mBinding.drawerLayout.closeDrawers();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void openDrawerLayout(){
        mBinding.drawerLayout.openDrawer(Gravity.LEFT);
    }
}