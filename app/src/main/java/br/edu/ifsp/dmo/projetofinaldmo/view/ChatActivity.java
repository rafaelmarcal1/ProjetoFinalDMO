package br.edu.ifsp.dmo.projetofinaldmo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Arrays;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;

import br.edu.ifsp.dmo.projetofinaldmo.R;
import br.edu.ifsp.dmo.projetofinaldmo.adapter.ChatRecyclerAdapter;
import br.edu.ifsp.dmo.projetofinaldmo.adapter.SearchUserRecyclerAdapter;
import br.edu.ifsp.dmo.projetofinaldmo.model.ChatMessageModel;
import br.edu.ifsp.dmo.projetofinaldmo.model.ChatroomModel;
import br.edu.ifsp.dmo.projetofinaldmo.model.UserModel;
import br.edu.ifsp.dmo.projetofinaldmo.utils.AndroidUtil;
import br.edu.ifsp.dmo.projetofinaldmo.utils.FirebaseUtil;

/*Esse é um código para uma atividade (Activity) em um aplicativo Android que implementa um chat entre usuários.*/

public class ChatActivity extends AppCompatActivity {

    /*A classe tem atributos para representar o usuário com quem o usuário atual
    está conversando (otherUser), o ID da sala de chat (chatroomId), o modelo da sala
    de chat (chatroomModel), um adaptador para o RecyclerView (adapter), e referências
    a elementos de interface do usuário como campos de entrada de mensagem, botões e o
    RecyclerView.*/
    UserModel otherUser;
    String chatroomId;
    ChatroomModel chatroomModel;
    ChatRecyclerAdapter adapter;

    EditText messageInput;
    ImageButton sendMessageBtn;
    ImageButton backBtn;
    TextView otherUsername;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //get UserModel
        otherUser = AndroidUtil.getUserModelFromIntent(getIntent());
        chatroomId = FirebaseUtil.getChatroomId(FirebaseUtil.currentUserID(),otherUser.getUserId());

        messageInput = findViewById(R.id.chat_message_input);
        sendMessageBtn = findViewById(R.id.message_send_btn);
        backBtn = findViewById(R.id.back_btn);
        otherUsername = findViewById(R.id.other_username);
        recyclerView = findViewById(R.id.chat_recycler_view);

        backBtn.setOnClickListener((v)->{
            onBackPressed();
        });
        otherUsername.setText(otherUser.getUsername());

        sendMessageBtn.setOnClickListener((v -> {
            String message = messageInput.getText().toString().trim();
            if (message.isEmpty())
                return;
            sendMessageToUser(message);
        }));

        getOrCreateChatroomModel();
    }

    /*Este método é chamado quando o usuário clica no botão de enviar mensagem.
    Ele configura o modelo da sala de chat com os detalhes da última mensagem e a envia para o
    Firestore. Além disso, adiciona a mensagem ao Firestore e atualiza o RecyclerView.*/
    void sendMessageToUser(String message){

        chatroomModel.setLastMessageTimestamp(Timestamp.now());
        chatroomModel.setLastMessageSenderId(FirebaseUtil.currentUserID());
        chatroomModel.setLastMessage(message);
        FirebaseUtil.getChatroomReference(chatroomId).set(chatroomModel);

        ChatMessageModel chatMessageModel = new ChatMessageModel(message,FirebaseUtil.currentUserID(),Timestamp.now());
        FirebaseUtil.getChatroomMessageReference(chatroomId).add(chatMessageModel)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            messageInput.setText("");
                        }
                    }
                });

        getOrCreateChatroomModel();
        setupChatRecyclerView();
    }

    /*Este método configura o RecyclerView para exibir as mensagens da sala de chat. Ele utiliza
    o Firebase Firestore para obter as mensagens e um adaptador personalizado (ChatRecyclerAdapter)
    para exibi-las.
     */
    void setupChatRecyclerView(){
        Query query = FirebaseUtil.getChatroomMessageReference(chatroomId)
                .orderBy("timestamp", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<ChatMessageModel> options = new FirestoreRecyclerOptions.Builder<ChatMessageModel>()
                .setQuery(query, ChatMessageModel.class).build();

        adapter = new ChatRecyclerAdapter(options, getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    /*Este método obtém ou cria o modelo da sala de chat no Firestore. Se a sala de chat não existir, ela é criada pela primeira vez.
     */
    void getOrCreateChatroomModel(){
        FirebaseUtil.getChatroomReference(chatroomId).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                chatroomModel = task.getResult().toObject(ChatroomModel.class);
                if(chatroomModel==null){
                    //first time chat
                    chatroomModel = new ChatroomModel(
                            chatroomId,
                            Arrays.asList(FirebaseUtil.currentUserID(),otherUser.getUserId()),
                            Timestamp.now(),
                            ""
                    );
                    FirebaseUtil.getChatroomReference(chatroomId).set(chatroomModel);
                }
            }
        });
    }
}