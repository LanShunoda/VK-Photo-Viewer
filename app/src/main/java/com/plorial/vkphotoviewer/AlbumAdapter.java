package com.plorial.vkphotoviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
            view = inflater.inflate(R.layout.item, parent, false);
        }
        TextView tvAlbumTitle = (TextView) view.findViewById(R.id.tvAlbumTitle);
        tvAlbumTitle.setText(albums.get(position).getTitle());

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        new DownloadImageTask(imageView).execute(albums.get(position).getThumbSrc());
        return view;
    }
}
