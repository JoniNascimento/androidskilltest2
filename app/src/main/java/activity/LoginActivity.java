package activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.joninascimento.cinq.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtSenha;
    private Button btnEntrar;

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

                }
            }
        });
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

    public void cadastrarUsuario(View V){
        Intent i = new Intent(getApplicationContext(), CadastrarActivity.class);
        startActivity(i);
    }
}
