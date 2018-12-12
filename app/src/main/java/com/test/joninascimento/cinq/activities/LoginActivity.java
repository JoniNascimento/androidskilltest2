package com.test.joninascimento.cinq.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.joninascimento.cinq.R;

import com.test.joninascimento.cinq.dao.UsuarioDao;
import com.test.joninascimento.cinq.model.Usuario;

import java.util.prefs.PreferenceChangeEvent;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtSenha;
    private Button btnEntrar;
    private SharedPreferences preferencias;
    private SharedPreferences.Editor editorPreferencias;
    private static final String PREFERENCIA = "LoginPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText) findViewById(R.id.edtEmailLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenhaLogin);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaCampos()){
                    entrar();
                }
            }
        });

        preferencias = getSharedPreferences(PREFERENCIA,0);
        editorPreferencias = preferencias.edit();
        if (preferencias.contains("email")){
            String email = preferencias.getString("email","");
            String senha = preferencias.getString("senha","");
            edtEmail.setText(email);
            edtSenha.setText(senha);
            entrar();
        }
    }

    public boolean validaCampos(){
        boolean resposta = true;

        if (edtEmail.getText().toString().isEmpty()){
            resposta = false;
            Toast.makeText(this,getString(R.string.msg_email),Toast.LENGTH_LONG).show();
        }
        if (edtSenha.getText().toString().isEmpty()){
            resposta = false;
            Toast.makeText(this,getString(R.string.msg_senha),Toast.LENGTH_LONG).show();
        }

        return resposta;
    }

    public void entrar(){
        UsuarioDao usuarioDao = new UsuarioDao(getApplicationContext());
        Usuario usuario = usuarioDao.pesquisarUsuario(edtEmail.getText().toString(),edtSenha.getText().toString());
        if (usuario == null){
            Toast.makeText(getApplicationContext(),getString(R.string.msg_falha_login), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),getString(R.string.msg_sucesso_login), Toast.LENGTH_LONG).show();
            editorPreferencias.putString("nome",usuario.getNome());
            editorPreferencias.putString("email",usuario.getEmail());
            editorPreferencias.putString("senha",usuario.getSenha());
            editorPreferencias.commit();

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }


    }

    public void cadastrarUsuario(View V){
        Intent i = new Intent(LoginActivity.this, CadastrarActivity.class);
        startActivity(i);
    }
}
