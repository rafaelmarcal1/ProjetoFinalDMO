package br.edu.ifsp.dmo.projetofinaldmo.model;

import com.google.firebase.Timestamp;

/*Essa classe UserModel representa um modelo de dados para um usuário.*/
public class UserModel {

    /*A classe possui quatro atributos privados:
phone: uma string representando o número de telefone do usuário.
username: uma string representando o nome de usuário do usuário.
createdTimestamp: um carimbo de data e hora representando quando a conta do usuário foi criada.
userId: uma string representando o ID único do usuário.*/
    private String phone;
    private String username;
    private Timestamp createdTimestamp;
    private String userId;

    /*Um construtor padrão sem argumentos é fornecido. Isso é útil para a criação de instâncias
    da classe sem a necessidade de inicializar todos os atributos imediatamente.*/
    public UserModel() {

    }

    /*Um construtor com argumentos é fornecido para criar instâncias da classe com valores
    iniciais para phone, username, createdTimestamp e userId.*/
    public UserModel(String phone, String username, Timestamp createdTimestamp, String userId) {
        this.phone = phone;
        this.username = username;
        this.createdTimestamp = createdTimestamp;
        this.userId = userId;
    }

    /*Métodos Getters e Setters:*/
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
}
