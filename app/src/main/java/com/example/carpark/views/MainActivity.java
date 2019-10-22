package com.example.carpark.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.carpark.R;

public class MainActivity extends AppCompatActivity implements  PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, new SettingsFragment())
                    .commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        final Bundle args = pref.getExtras();
        final Fragment fragment = getSupportFragmentManager().getFragmentFactory().instantiate( getClassLoader(), pref.getFragment() );
        fragment.setArguments(args);
        fragment.setTargetFragment(caller, 0);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, fragment)
                .addToBackStack(null)
                .commit();
        return true;
    }
}
