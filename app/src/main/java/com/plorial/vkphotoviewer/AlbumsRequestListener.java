package com.plorial.vkphotoviewer;

import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by plorial on 3/23/16.
 */
public class AlbumsRequestListener extends VKRequest.VKRequestListener {

    private List<Album> albums;

    @Override
    public void onComplete(VKResponse response) {
        super.onComplete(response);
        albums = new ArrayList<>();
        try {
            JSONObject responseJSON = response.json.getJSONObject("response");
            JSONArray albumsJSON = responseJSON.getJSONArray("items");
            int count = albumsJSON.length();
            for (int i = 0; i < count; i++) {
                JSONObject albumJSON = albumsJSON.getJSONObject(i);
                Album album = new Album(albumJSON.getInt("id"));
                album.setTitle(albumJSON.getString("title"));
                album.setThumbSrc(albumJSON.getString("thumb_src"));
                album.setSize(albumJSON.getInt("size"));
                albums.add(album);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Album> getAlbums() {
        return albums;
    }
}
