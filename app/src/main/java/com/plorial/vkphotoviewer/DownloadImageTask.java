package com.plorial.vkphotoviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by plorial on 3/24/16.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private View view;

    public DownloadImageTask(View view) {
        this.view = view;
    }

    protected Bitmap doInBackground(String... url) {
        String urldisplay = url[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageBitmap(result);
        ProgressBar bar = (ProgressBar) view.findViewById(R.id.progressBar);
        bar.setVisibility(View.GONE);
    }
}

