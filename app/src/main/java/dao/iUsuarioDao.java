package dao;

import java.util.List;

import model.Usuario;

public interface iUsuarioDao {

    public boolean inserir(Usuario usuario);
    public boolean atualizar(Usuario usuario);
    public boolean deletar(Usuario usuario);
    public List<Usuario> listar();
    public Usuario pesquisarUsuario(String email, String Senha);

}
