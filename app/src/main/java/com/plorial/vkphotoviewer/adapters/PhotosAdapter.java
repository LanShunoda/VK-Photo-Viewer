package com.plorial.vkphotoviewer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.plorial.vkphotoviewer.DownloadImageTask;
import com.plorial.vkphotoviewer.R;
import com.vk.sdk.api.model.VKApiPhoto;

import java.util.List;

/**
 * Created by plorial on 3/24/16.
 */
public class PhotosAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<VKApiPhoto> photos;

    public PhotosAdapter(Context context, List<VKApiPhoto> photos) {
        this.context = context;
        this.photos = photos;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int position) {
        return photos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.photo_item, parent, false);
        }

        new DownloadImageTask(view).execute(photos.get(position).photo_130);

        return view;
    }
}
