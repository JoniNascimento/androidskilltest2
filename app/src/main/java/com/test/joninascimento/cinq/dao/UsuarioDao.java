package com.test.joninascimento.cinq.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import com.test.joninascimento.cinq.helper.DbHelper;
import com.test.joninascimento.cinq.model.Usuario;

public class UsuarioDao implements iUsuarioDao {

    private SQLiteDatabase escrever, ler;

    public UsuarioDao(Context context) {
        DbHelper db = new DbHelper(context);
        escrever = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public boolean inserir(Usuario usuario) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("email",usuario.getEmail());
        contentValues.put("nome", usuario.getNome());
        contentValues.put("senha", usuario.getSenha());
        try {
            escrever.insert(DbHelper.TABELA, null, contentValues);
            Log.i("Inserir Info:", "Usuário inserido com Sucesso!!");
            return true;
        }catch (Exception e){
            Log.i("Inserir Info:", "Erro ao inserir Usuário:" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizar(Usuario usuario) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("email",usuario.getEmail());
        contentValues.put("nome", usuario.getNome());
        contentValues.put("senha", usuario.getSenha());
        try {

            String[] argumentos = {Integer.toString(usuario.getId())};

            escrever.update(DbHelper.TABELA, contentValues, "id=?", argumentos);
            Log.i("Inserir Info:", "Usuário Atualizado com Sucesso!!");
            return true;
        }catch (Exception e){
            Log.i("Inserir Info:", "Erro ao atualizar Usuário:" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Usuario usuario) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("email",usuario.getEmail());
        contentValues.put("nome", usuario.getNome());
        contentValues.put("senha", usuario.getSenha());
        try {

            String[] argumentos = {Integer.toString(usuario.getId())};

            escrever.delete(DbHelper.TABELA, "id=?", argumentos);
            Log.i("Inserir Info:", "Usuário Atualizado com Sucesso!!");
            return true;
        }catch (Exception e){
            Log.i("Inserir Info:", "Erro ao atualizar Usuário:" + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA + ";";

        Cursor cursor = ler.rawQuery(sql,null);

        while (cursor.moveToNext()){
            Usuario usuario = new Usuario(cursor.getInt(cursor.getColumnIndex("id")),
                                          cursor.getString(cursor.getColumnIndex("email")),
                                          cursor.getString(cursor.getColumnIndex("nome")),
                                          cursor.getString(cursor.getColumnIndex("senha")));

            usuarios.add(usuario);
        }

        return usuarios;
    }

    @Override
    public Usuario pesquisarUsuario(String email, String Senha) {
        Usuario usuario = null;

        String sql = "SELECT * FROM " + DbHelper.TABELA + ";";

        Cursor cursor = ler.rawQuery(sql,null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            usuario = new Usuario(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("email")),
                    cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getString(cursor.getColumnIndex("senha")));


        }

        return usuario;
    }


}
