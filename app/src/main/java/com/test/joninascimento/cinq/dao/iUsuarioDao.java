package com.test.joninascimento.cinq.dao;

import java.util.List;

import com.test.joninascimento.cinq.model.Usuario;

public interface iUsuarioDao {

    public boolean inserir(Usuario usuario);
    public boolean atualizar(Usuario usuario);
    public boolean deletar(Usuario usuario);
    public List<Usuario> listar();
    public Usuario pesquisarUsuario(String email, String Senha);

}
