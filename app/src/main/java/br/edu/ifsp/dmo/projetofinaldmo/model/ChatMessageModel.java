package br.edu.ifsp.dmo.projetofinaldmo.model;

import com.google.firebase.Timestamp;

/*Essa classe ChatMessageModel representa um modelo de dados para mensagens de chat. */
public class ChatMessageModel {

    /*A classe possui três atributos privados: message (mensagem do chat), senderId (ID do remetente)
    e timestamp (marca de data e hora da mensagem).*/
    private String message;
    private String senderId;
    private Timestamp timestamp;

    /*Um construtor padrão sem argumentos é fornecido. Isso é útil para a criação de
    instâncias da classe sem a necessidade de inicializar todos os atributos imediatamente.*/
    public ChatMessageModel() {
    }

    /*Um construtor com argumentos é fornecido para criar instâncias da classe com valores iniciais
    para message, senderId e timestamp.*/
    public ChatMessageModel(String message, String senderId, Timestamp timestamp) {
        this.message = message;
        this.senderId = senderId;
        this.timestamp = timestamp;
    }


    /*Para cada atributo, há um par de métodos getter e setter. Os métodos getter são usados
    para obter o valor atual do atributo, enquanto os métodos setter são usados para definir
    ou atualizar o valor do atributo.*/
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}