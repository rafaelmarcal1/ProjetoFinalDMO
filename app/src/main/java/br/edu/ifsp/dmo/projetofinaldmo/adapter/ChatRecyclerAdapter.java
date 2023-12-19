package br.edu.ifsp.dmo.projetofinaldmo.adapter;

/*Esse é um código Java que cria um adaptador de RecyclerView para exibir mensagens de chat.
Ele estende FirestoreRecyclerAdapter, uma classe fornecida pelo Firebase Firestore, que simplifica
 a criação de adaptadores para RecyclerViews quando se está trabalhando com dados armazenados no
 Firestore.*/

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import br.edu.ifsp.dmo.projetofinaldmo.R;
import br.edu.ifsp.dmo.projetofinaldmo.model.ChatMessageModel;
import br.edu.ifsp.dmo.projetofinaldmo.utils.FirebaseUtil;

/*ChatRecyclerAdapter é a classe principal que estende FirestoreRecyclerAdapter<ChatMessageModel,
 ChatRecyclerAdapter.ChatModelViewHolder>. Ela recebe dois parâmetros de tipo: ChatMessageModel,
 que representa o tipo de objeto de dados que será exibido, e ChatModelViewHolder, que representa
 o tipo de ViewHolder que será usado.*/
public class ChatRecyclerAdapter extends FirestoreRecyclerAdapter<ChatMessageModel, ChatRecyclerAdapter.ChatModelViewHolder> {

    /*Context context;: Uma instância da classe Context é armazenada como um atributo da classe.
    O contexto é frequentemente necessário para realizar operações relacionadas à interface
    do usuário.*/
    Context context;


    /*O construtor public ChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatMessageModel>
    options, Context context) é responsável por inicializar o adaptador. Ele recebe as opções de
    configuração (FirestoreRecyclerOptions) e o contexto.*/
    public ChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatMessageModel> options, Context context) {
        super(options);
        this.context = context;
    }

    /*onBindViewHolder: Este método é chamado para exibir os dados em uma posição específica.
    Ele é sobrescrito para personalizar como os dados são exibidos no RecyclerView.
    Dependendo do remetente da mensagem (model.getSenderId().equals(FirebaseUtil.currentUserID())),
    o layout apropriado é exibido.*/
    @Override
    protected void onBindViewHolder(@NonNull ChatModelViewHolder holder, int position, @NonNull ChatMessageModel model) {
        if(model.getSenderId().equals(FirebaseUtil.currentUserID())){
            holder.leftChatLayout.setVisibility(View.GONE);
            holder.rightChatLayout.setVisibility(View.VISIBLE);
            holder.rightChatTextview.setText(model.getMessage());
        }else{
            holder.rightChatLayout.setVisibility(View.GONE);
            holder.leftChatLayout.setVisibility(View.VISIBLE);
            holder.leftChatTextview.setText(model.getMessage());
        }
    }

    /*onCreateViewHolder: Este método é chamado quando novos ViewHolder precisam ser criados.
    Ele infla o layout da linha do RecyclerView (chat_message_recycler_row.xml) e retorna uma
    nova instância de ChatModelViewHolder.*/
    @NonNull
    @Override
    public ChatModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_message_recycler_row,parent,false);
        return new ChatModelViewHolder(view);
    }

    /*ChatModelViewHolder é uma classe interna que estende RecyclerView.ViewHolder.
    Ela representa o ViewHolder para exibir cada item de chat. Os elementos de layout
    (layouts de chat e textviews) são inicializados no construtor.*/
    class ChatModelViewHolder extends RecyclerView.ViewHolder{

        LinearLayout leftChatLayout,rightChatLayout;
        TextView leftChatTextview,rightChatTextview;

        public ChatModelViewHolder(@NonNull View itemView) {
            super(itemView);

            leftChatLayout = itemView.findViewById(R.id.left_chat_layout);
            rightChatLayout = itemView.findViewById(R.id.right_chat_layout);
            leftChatTextview = itemView.findViewById(R.id.left_chat_textview);
            rightChatTextview = itemView.findViewById(R.id.right_chat_textview);
        }
    }
}