package com.plorial.vkphotoviewer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.plorial.vkphotoviewer.adapters.PhotosAdapter;
import com.plorial.vkphotoviewer.R;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiPhoto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by plorial on 3/24/16.
 */
public class PhotosFragment extends Fragment {

    public static final String PHOTO = "PHOTO";

    private List<VKApiPhoto> photos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preview_fragment,container,false);

        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        VKRequest albumPhotosRequest = new VKRequest("photos.get", VKParameters.from("album_id", getArguments().getInt(AlbumsFragment.ALBUM_ID)));
        albumPhotosRequest.executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                try {
                    photos = new ArrayList<VKApiPhoto>();
                    JSONObject responseJSON = response.json.getJSONObject("response");
                    JSONArray photosJSON = responseJSON.getJSONArray("items");

                    for (int i = 0; i < photosJSON.length(); i++) {
                        VKApiPhoto photo = new VKApiPhoto();
                        photo.parse(photosJSON.getJSONObject(i));
                        photos.add(photo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoFragment photoFragment = new PhotoFragment();
                Bundle bundle = new Bundle();
                bundle.putString(PHOTO, photos.get(position).photo_807);
                photoFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, photoFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        gridView.setAdapter(new PhotosAdapter(getContext(),photos));
        return view;
    }
}
