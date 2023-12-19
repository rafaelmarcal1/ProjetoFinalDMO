package br.edu.ifsp.dmo.projetofinaldmo.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import br.edu.ifsp.dmo.projetofinaldmo.model.UserModel;

/*Essa classe AndroidUtil contém métodos utilitários relacionados à interação com o Android. */
public class AndroidUtil {

    /*Este método estático é responsável por exibir um toast (mensagem temporária) na tela do
    dispositivo. Ele recebe dois parâmetros: context, que é o contexto da aplicação ou da atividade,
    e message, que é a mensagem a ser exibida no toast.*/
    public static void showToast(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    /*Este método estático é utilizado para passar informações de um objeto UserModel para um
    objeto Intent. Ele adiciona dados extras ao intent usando chaves específicas
    ("username", "phone", "userId") para representar diferentes atributos do modelo de usuário.*/
    public static void passUserModelAsIntent(Intent intent, UserModel model){
        intent.putExtra("username",model.getUsername());
        intent.putExtra("phone",model.getPhone());
        intent.putExtra("userId",model.getUserId());
    }


/*Este método estático é usado para recuperar um objeto UserModel de um objeto Intent.
Ele cria uma nova instância de UserModel e preenche seus atributos com os valores associados
às chaves específicas no intent.*/
    public static UserModel getUserModelFromIntent(Intent intent){
        UserModel userModel = new UserModel();
        userModel.setUsername(intent.getStringExtra("username"));
        userModel.setPhone(intent.getStringExtra("phone"));
        userModel.setUserId(intent.getStringExtra("userId"));
        return userModel;
    }
}
