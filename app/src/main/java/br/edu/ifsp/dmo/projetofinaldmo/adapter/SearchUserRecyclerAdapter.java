package br.edu.ifsp.dmo.projetofinaldmo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import br.edu.ifsp.dmo.projetofinaldmo.view.ChatActivity;
import br.edu.ifsp.dmo.projetofinaldmo.R;
import br.edu.ifsp.dmo.projetofinaldmo.model.UserModel;
import br.edu.ifsp.dmo.projetofinaldmo.utils.AndroidUtil;
import br.edu.ifsp.dmo.projetofinaldmo.utils.FirebaseUtil;


/*Essa classe, SearchUserRecyclerAdapter, é um adaptador para um RecyclerView que exibe informações
 de usuários, possivelmente para realizar pesquisas ou listagem de usuários. Vamos analisar os
 principais elementos do código:*/

/*A classe estende FirestoreRecyclerAdapter, indicando que é um adaptador para trabalhar com
    dados armazenados no Firebase Firestore. Ela lida com objetos do tipo UserModel e usa
    UserModelViewHolder como o ViewHolder associado.*/
public class SearchUserRecyclerAdapter extends FirestoreRecyclerAdapter<UserModel, SearchUserRecyclerAdapter.UserModelViewHolder> {

    /*Um atributo Context é declarado para armazenar o contexto, frequentemente necessário para operações relacionadas à interface do usuário.*/
    Context context;


    /*O construtor recebe FirestoreRecyclerOptions contendo opções de configuração e o contexto.
    Ele chama o construtor da classe pai (super(options)) para inicializar o adaptador com as
    opções fornecidas.*/
    public SearchUserRecyclerAdapter(@NonNull FirestoreRecyclerOptions<UserModel> options, Context context) {
        super(options);
        this.context = context;
    }

    /*Este método é chamado para exibir os dados em uma posição específica. Ele configura os textos
    e a lógica de clique para cada item do RecyclerView. Se o usuário representado pelo item for o
    usuário atual, o texto do nome de usuário é modificado para incluir "(Me)". Além disso, ao clicar
    no item, a ChatActivity é iniciada, passando as informações do usuário.*/
    @Override
    protected void onBindViewHolder(@NonNull UserModelViewHolder holder, int position, @NonNull UserModel model) {
        holder.usernameText.setText(model.getUsername());
        holder.phoneText.setText(model.getPhone());
        if(model.getUserId().equals(FirebaseUtil.currentUserID())){
            holder.usernameText.setText(model.getUsername()+" (Me)");
        }

        holder.itemView.setOnClickListener(view -> {
            //Navega para a activity com chat
            Intent intent = new Intent(context, ChatActivity.class);
            AndroidUtil.passUserModelAsIntent(intent,model);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    /*Este método é chamado para criar novos ViewHolders conforme necessário. Ele infla o layout
    da linha do RecyclerView (search_user_recycler_row.xml) e retorna uma nova instância de
    UserModelViewHolder.*/
    @NonNull
    @Override
    public UserModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_user_recycler_row,parent,false);
        return new UserModelViewHolder(view);
    }

    /*Esta é uma classe interna que estende RecyclerView.ViewHolder. Ela representa o ViewHolder
    para exibir cada item de usuário. Os elementos de layout (textos e imagem) são inicializados
    no construtor.*/
    class UserModelViewHolder extends RecyclerView.ViewHolder{
        TextView usernameText;
        TextView phoneText;
        ImageView profilePic;

        public UserModelViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.user_name_text);
            phoneText = itemView.findViewById(R.id.phone_text);
            profilePic = itemView.findViewById(R.id.profile_pic_image_view);
        }
    }
}
