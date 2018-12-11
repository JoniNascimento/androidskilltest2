package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.joninascimento.cinq.R;

import java.util.List;

import model.Usuario;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder> {

    private List<Usuario> listaUsuarios;

    public UsuarioAdapter(List<Usuario> lista) {
        listaUsuarios = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lista_usuario, viewGroup, false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Usuario usuario = listaUsuarios.get(i);
        viewHolder.txvDescricao.setText(usuario.getNome());

    }

    @Override
    public int getItemCount() {
        return this.listaUsuarios.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView txvDescricao;

        public ViewHolder(View itemView) {
            super(itemView);

            txvDescricao = (TextView) itemView.findViewById(R.id.txvDescricao);
        }
    }
}
