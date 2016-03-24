package com.plorial.vkphotoviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.plorial.vkphotoviewer.fragments.AlbumsFragment;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class MainActivity extends AppCompatActivity {

    public static final String[] SCOPES = {VKScope.PHOTOS};
    private boolean returningWithResult = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        VKSdk.login(this, SCOPES);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (returningWithResult) {
            AlbumsFragment albumsFragment = new AlbumsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, albumsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        returningWithResult = false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                returningWithResult = true;
            }

            @Override
            public void onError(VKError error) {
                VKSdk.login(getParent(), MainActivity.SCOPES);
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
