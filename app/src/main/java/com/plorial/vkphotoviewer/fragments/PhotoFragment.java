package com.plorial.vkphotoviewer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.plorial.vkphotoviewer.DownloadImageTask;
import com.plorial.vkphotoviewer.R;

/**
 * Created by plorial on 3/24/16.
 */
public class PhotoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_fragment,container,false);
        new DownloadImageTask(view).execute(getArguments().getString(PhotosFragment.PHOTO));
        return view;
    }
}
