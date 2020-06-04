package com.example.pmdm_aplicacion;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class AdaptadorImagenes extends BaseAdapter {
    private Context contexto;
    public int[] imagenesArray = {

    };

    public AdaptadorImagenes(Context contexto) {
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return imagenesArray.length;
    }

    @Override
    public Object getItem(int position) {
        return imagenesArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(contexto);
        imageView.setImageResource(imagenesArray[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(
                new GridView.LayoutParams(
                        340,
                        350
                ));
        return imageView;
    }
}
