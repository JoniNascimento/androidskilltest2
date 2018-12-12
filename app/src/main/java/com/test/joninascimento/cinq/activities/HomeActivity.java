package com.test.joninascimento.cinq.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import com.test.joninascimento.cinq.R;

import java.util.ArrayList;
import java.util.List;

import com.test.joninascimento.cinq.adapter.UsuarioAdapter;
import com.test.joninascimento.cinq.dao.UsuarioDao;
import com.test.joninascimento.cinq.helper.RecyclerItemClickListener;
import com.test.joninascimento.cinq.model.Usuario;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView rvLista;
    private SearchView svPesquisaUsuario;
    private UsuarioAdapter usuarioAdapter;
    private List<Usuario> listaUsuario;
    private List<Usuario> listaUsuarioAux;
    private Usuario usuarioAtual;
    private TextView txvUsuarioAtual;
    private static final String PREFERENCIA = "LoginPreferencia";
    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rvLista = (RecyclerView) findViewById(R.id.rvLista);
        rvLista.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rvLista, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Usuario usuarioSelecionado = listaUsuario.get(position);
                Intent i = new Intent(getApplicationContext(), CadastrarActivity.class);
                i.putExtra("objUsuario",usuarioSelecionado);
                startActivity(i);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> paressnt, View view, int position, long id) {

            }
        }));

        svPesquisaUsuario = (SearchView) findViewById(R.id.svUsuarios);
        svPesquisaUsuario.setQueryHint("Buscar Usuários");
        svPesquisaUsuario.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String texto = newText.toUpperCase();
                pesquisaUsuarios(texto);
                return true;
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CadastrarActivity.class);
                startActivity(i);
            }
        });

        txvUsuarioAtual = (TextView) findViewById(R.id.txvUsuarioAtual);

        preferencias = getSharedPreferences(PREFERENCIA,0);
        editor = preferencias.edit();

        if (preferencias.contains("nome")){
            String nome = preferencias.getString("nome","");
            txvUsuarioAtual.setText(nome);

        }
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
            case R.id.itemSair:
                editor.clear();
                editor.commit();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        carregarUsuarios();
        super.onStart();
    }

    public void carregarUsuarios(){

        UsuarioDao usuarioDao = new UsuarioDao(getBaseContext());
        listaUsuario = usuarioDao.listar();

        usuarioAdapter = new UsuarioAdapter(listaUsuario);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvLista.setLayoutManager(layoutManager);
        rvLista.setHasFixedSize(true);
        rvLista.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        rvLista.setAdapter(usuarioAdapter);

    }

    private void pesquisaUsuarios(String texto){
        if(listaUsuarioAux == null){
            listaUsuarioAux = new ArrayList<>(listaUsuario);
        }
        listaUsuario.clear();

        if (texto.length() > 0 ){

            for (int i = 0; i < listaUsuarioAux.size(); i++){
                String registro = listaUsuarioAux.get(i).getNome();
                if(registro.toUpperCase().startsWith(texto)){
                    listaUsuario.add(listaUsuarioAux.get(i));
                }
            }
            usuarioAdapter.notifyDataSetChanged();
        }else{
            carregarUsuarios();
        }
    }


}
