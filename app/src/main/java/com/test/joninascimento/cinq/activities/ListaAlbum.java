package com.test.joninascimento.cinq.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.test.joninascimento.cinq.R;
import com.test.joninascimento.cinq.adapter.AlbumAdapter;
import com.test.joninascimento.cinq.adapter.UsuarioAdapter;
import com.test.joninascimento.cinq.api.Service;
import com.test.joninascimento.cinq.dao.UsuarioDao;
import com.test.joninascimento.cinq.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaAlbum extends AppCompatActivity {

    private Retrofit retrofit;
    private List<Album> listaAlbum;
    private AlbumAdapter albumAdapter;
    private RecyclerView rvAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_album);

        rvAlbum = (RecyclerView) findViewById(R.id.rvAlbum);

        retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").addConverterFactory(GsonConverterFactory.create()).build();
        buscaDadosApi();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()){
            case R.id.itemHome :
                i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.itemListaAlbuns:
                i = new Intent(getApplicationContext(), ListaAlbum.class);
                startActivity(i);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void buscaDadosApi(){
        Service service = retrofit.create(Service.class);
        Call<List<Album>> call = service.recuperarDados();
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.isSuccessful()){
                    listaAlbum = response.body();
                    carregarAlbuns();
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });

    }

    public void carregarAlbuns(){



        albumAdapter = new AlbumAdapter(listaAlbum);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvAlbum.setLayoutManager(layoutManager);
        rvAlbum.setHasFixedSize(true);
        rvAlbum.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        rvAlbum.setAdapter(albumAdapter);

    }
}
