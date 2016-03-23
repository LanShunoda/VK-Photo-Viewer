package com.plorial.vkphotoviewer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Created by plorial on 3/23/16.
 */
public class AlbumsFragment extends Fragment{

    List<Album> albums;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.albums_fragment,container,false);
        VKRequest request = new VKRequest("photos.getAlbums", VKParameters.from("need_covers", 1));
        AlbumsRequestListener albumsRequestListener = new AlbumsRequestListener();
        request.executeSyncWithListener(albumsRequestListener);

        albums = albumsRequestListener.getAlbums();
        GridView gridView = (GridView) view.findViewById(R.id.gridView);
//        gridView.setNumColumns(1);

        AlbumAdapter adapter = new AlbumAdapter(getContext(),albums);
        gridView.setAdapter(adapter);
        return view;
    }
}
