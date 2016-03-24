package com.plorial.vkphotoviewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;

import java.util.List;

/**
 * Created by plorial on 3/23/16.
 */
public class AlbumsFragment extends Fragment{

    List<Album> albums;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preview_fragment,container,false);
        final VKRequest albumsRequest = new VKRequest("photos.getAlbums", VKParameters.from("need_covers", 1));
        AlbumsRequestListener albumsRequestListener = new AlbumsRequestListener();
        albumsRequest.executeSyncWithListener(albumsRequestListener);

        albums = albumsRequestListener.getAlbums();
        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
                PhotosFragment photosFragment = new PhotosFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("ALBUM_ID",albums.get(position).getId());
                photosFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.add(R.id.fragment_container, photosFragment);
                transaction.commit();
            }
        });

        AlbumAdapter adapter = new AlbumAdapter(getContext(),albums);
        gridView.setAdapter(adapter);
        return view;
    }
}
