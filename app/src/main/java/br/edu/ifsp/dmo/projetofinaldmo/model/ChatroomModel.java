package br.edu.ifsp.dmo.projetofinaldmo.model;

import com.google.firebase.Timestamp;
import java.util.List;

/*Essa classe ChatroomModel representa um modelo de dados para uma sala de chat (chatroom).*/
public class ChatroomModel {

    /*A classe possui cinco atributos privados:
chatroomId: uma string que identifica exclusivamente a sala de chat.
userIds: uma lista de strings contendo os IDs dos usuários associados à sala de chat.
lastMessageTimestamp: um carimbo de data e hora representando quando a última mensagem foi enviada na sala de chat.
lastMessageSenderId: uma string representando o ID do remetente da última mensagem.
lastMessage: uma string contendo o texto da última mensagem enviada na sala de chat.*/
    String chatroomId;
    List<String> userIds;
    Timestamp lastMessageTimestamp;
    String lastMessageSenderId;
    String lastMessage;

    /*Um construtor padrão sem argumentos é fornecido. Isso é útil para a criação de instâncias
    da classe sem a necessidade de inicializar todos os atributos imediatamente.*/
    public ChatroomModel() {
    }

    /*Um construtor com argumentos é fornecido para criar instâncias da classe com valores
    iniciais para chatroomId, userIds, lastMessageTimestamp e lastMessageSenderId.*/
    public ChatroomModel(String chatroomId, List<String> userIds, Timestamp lastMessageTimestamp, String lastMessageSenderId) {
        this.chatroomId = chatroomId;
        this.userIds = userIds;
        this.lastMessageTimestamp = lastMessageTimestamp;
        this.lastMessageSenderId = lastMessageSenderId;
    }

    /*Para cada atributo, há um par de métodos getter e setter. Os métodos getter são usados para
    obter o valor atual do atributo, enquanto os métodos setter são usados para definir ou
    atualizar o valor do atributo.*/
    public String getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public Timestamp getLastMessageTimestamp() {
        return lastMessageTimestamp;
    }

    public void setLastMessageTimestamp(Timestamp lastMessageTimestamp) {
        this.lastMessageTimestamp = lastMessageTimestamp;
    }

    public String getLastMessageSenderId() {
        return lastMessageSenderId;
    }

    public void setLastMessageSenderId(String lastMessageSenderId) {
        this.lastMessageSenderId = lastMessageSenderId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}