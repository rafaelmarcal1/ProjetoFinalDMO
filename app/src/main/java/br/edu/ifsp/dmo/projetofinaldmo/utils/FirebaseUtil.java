package br.edu.ifsp.dmo.projetofinaldmo.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

/*uma classe de utilitário em Android que fornece métodos relacionados à autenticação,
    ao banco de dados Firestore e a operações específicas relacionadas a chatrooms. */
public class FirebaseUtil {

    /*Este método estático retorna o ID único (UID) do usuário atualmente autenticado no Firebase Authentication.*/
    public static String currentUserID(){
        return FirebaseAuth.getInstance().getUid();
    }

    /*Este método estático verifica se há um usuário autenticado, com base no ID do usuário atual. Retorna true
    se um usuário estiver autenticado, caso contrário, retorna false.*/
    public static boolean isLoggedIn(){
        if (currentUserID()!=null){
            return true;
        }
        return false;
    }

    /*Este método estático retorna uma referência ao documento do usuário atual no Firestore.
    A referência é construída com base no ID do usuário atual.
     */
    public static DocumentReference currentUserDetails(){
        return FirebaseFirestore.getInstance().collection("users").document(currentUserID());
    }

    /*Este método estático retorna uma referência à coleção que contém todos os usuários no Firestore.*/
    public static CollectionReference allUserCollectionReference(){
        return FirebaseFirestore.getInstance().collection("users");
    }

    /*Este método estático retorna uma referência ao documento específico de uma sala de chat com o ID fornecido.
     */
    public static DocumentReference getChatroomReference(String chatroomId){
        return FirebaseFirestore.getInstance().collection("chatrooms").document(chatroomId);
    }

/*Este método estático retorna uma referência à coleção que contém as mensagens de uma sala de chat específica.
 */
    public static CollectionReference getChatroomMessageReference(String chatroomId){
        return getChatroomReference(chatroomId).collection("chats");
    }

    /*Este método estático retorna um ID único para uma sala de chat, com base
    nos IDs de dois usuários. A lógica garante que a mesma sala seja identificada independentemente da ordem dos IDs dos usuários.*/
    public static String getChatroomId(String userId1,String userId2){
        if(userId1.hashCode()<userId2.hashCode()){
            return userId1+"_"+userId2;
        }else{
            return userId2+"_"+userId1;
        }
    }
}