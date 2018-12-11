package activity;

import android.content.Intent;
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

import com.test.joninascimento.cinq.R;

import java.util.List;

import adapter.UsuarioAdapter;
import dao.UsuarioDao;
import helper.RecyclerItemClickListener;
import model.Usuario;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView rvLista;
    private UsuarioAdapter usuarioAdapter;
    private List<Usuario> listaUsuario;

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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CadastrarActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemHome :
                Intent i = new Intent(getApplicationContext(), CadastrarActivity.class);
                startActivity(i);
                break;

            case R.id.itemListaAlbuns:
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
}
