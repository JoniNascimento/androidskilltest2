package com.test.joninascimento.cinq.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.joninascimento.cinq.R;
import com.test.joninascimento.cinq.model.Album;
import com.test.joninascimento.cinq.model.Usuario;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private List<Album> listaAlbuns;

    public AlbumAdapter(List<Album> lista) {
        listaAlbuns = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lista_album, viewGroup, false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Album album = listaAlbuns.get(i);
        viewHolder.txvTitulo.setText(album.getTitle());
        Picasso.get().load(album.getThumbnailUrl()).into(viewHolder.imgThumb);

    }

    @Override
    public int getItemCount() {

        if(listaAlbuns != null) {
            return this.listaAlbuns.size();
        }else{
            return 0;
        }

    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView txvTitulo;
        private ImageView imgThumb;

        public ViewHolder(View itemView) {
            super(itemView);

            txvTitulo = (TextView) itemView.findViewById(R.id.txvTitulo);
            imgThumb = (ImageView) itemView.findViewById(R.id.imgThumb);
        }
    }
}
