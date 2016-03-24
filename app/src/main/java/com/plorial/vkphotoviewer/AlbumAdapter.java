package com.plorial.vkphotoviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by plorial on 3/24/16.
 */
public class AlbumAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<Album> albums;

    public AlbumAdapter(Context context, List<Album> albums) {
        this.context = context;
        this.albums = albums;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return albums.size();
    }

    @Override
    public Object getItem(int position) {
        return albums.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.album_item, parent, false);
        }
        TextView tvAlbumTitle = (TextView) view.findViewById(R.id.tvAlbumTitle);
        tvAlbumTitle.setText(albums.get(position).getTitle());

        new DownloadImageTask(view).execute(albums.get(position).getThumbSrc());

        return view;
    }
}
