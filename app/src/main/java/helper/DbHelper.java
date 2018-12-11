package helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSAO_DB = 1;
    public static String NOME_DB = "TESTE_CINQ";
    public static String TABELA = "usuarios";

    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA + "(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT NOT NULL, nome TEXT NOT NULL, senha TEXT NOT NULL );";
        try {
            db.execSQL(sql);
            Log.i("DB INFO:","Tabela Criada com Sucesso!!");
        }catch (Exception e){
            Log.i("DB INFO:","Erro o Criar a tabela: " +e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
