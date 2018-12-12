package com.test.joninascimento.cinq.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.joninascimento.cinq.R;

import com.test.joninascimento.cinq.dao.UsuarioDao;
import com.test.joninascimento.cinq.model.Usuario;

public class CadastrarActivity extends AppCompatActivity {

    private EditText edtEmail, edtNome, edtSenha;
    private Button btnCadastrar, btnExcluir;
    private Usuario usuarioEditar, usuarioNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);




        edtEmail = (EditText) findViewById(R.id.edtEmailCadastrar);
        edtNome = (EditText) findViewById(R.id.edtNomeCadastrar);
        edtSenha = (EditText) findViewById(R.id.edtSenhaCadastrar);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaCampos()){
                    cadastrarUsuario();

                }
            }
        });

        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        btnExcluir.setVisibility(View.INVISIBLE);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletaUsuario();
            }
        });

        trataExtra();
    }

    public void trataExtra(){
        usuarioEditar = (Usuario) getIntent().getSerializableExtra("objUsuario");
       if(usuarioEditar != null) {
           edtEmail.setText(usuarioEditar.getEmail());
           edtNome.setText(usuarioEditar.getNome());
           edtSenha.setText(usuarioEditar.getSenha());
           btnExcluir.setVisibility(View.VISIBLE);
           edtEmail.setEnabled(false);
       }
    }

    public boolean validaCampos(){
        boolean resposta = true;

        if (edtEmail.getText().toString().isEmpty()){
            resposta = false;
            Toast.makeText(this,getString(R.string.msg_email),Toast.LENGTH_LONG).show();
        }
        if (edtNome.getText().toString().isEmpty()){
            resposta = false;
            Toast.makeText(this,getString(R.string.msg_nome),Toast.LENGTH_LONG).show();
        }
        if (edtSenha.getText().toString().isEmpty()){
            resposta = false;
            Toast.makeText(this,getString(R.string.msg_senha),Toast.LENGTH_LONG).show();
        }

        if (resposta){
            if (usuarioEditar != null) {
                usuarioEditar.setEmail(edtEmail.getText().toString());
                usuarioEditar.setNome(edtNome.getText().toString());
                usuarioEditar.setSenha(edtSenha.getText().toString());
            }else {
                usuarioNovo = new Usuario(0, edtEmail.getText().toString(), edtNome.getText().toString(), edtSenha.getText().toString());
            }
        }

        return resposta;
    }

    public void cadastrarUsuario(){
        UsuarioDao usuarioDao = new UsuarioDao(getApplicationContext());
        if (usuarioEditar != null) {
            if (usuarioDao.atualizar(usuarioEditar)) {
                Toast.makeText(getApplicationContext(), getText(R.string.msg_cadastro_sucesso), Toast.LENGTH_LONG).show();
                finish();
            }

        }else {
            if (usuarioDao.inserir(usuarioNovo)) {
                Toast.makeText(getApplicationContext(), getText(R.string.msg_cadastro_sucesso), Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    public void deletaUsuario(){

        if (usuarioEditar != null) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.titulo_dialogo_del)
                    .setMessage(R.string.msg_del_usuario)
                    .setPositiveButton(R.string.opcao_sim, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            UsuarioDao usuarioDao = new UsuarioDao(getApplicationContext());
                            usuarioDao.deletar(usuarioEditar);
                            Toast.makeText(CadastrarActivity.this, getString(R.string.msg_del_cadastro_sucesso),Toast.LENGTH_LONG).show();
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.opcao_nao,null).show();

        }
    }

    @Override
    protected void onStart() {

        super.onStart();
    }
}
