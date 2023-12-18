package br.edu.ifsp.dmo.projetofinaldmo.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

public class FirebaseUtil {

    public static String currentUserID(){
        return FirebaseAuth.getInstance().getUid();
    }

    public static boolean isLoggedIn(){
        if (currentUserID()!=null){
            return true;
        }
        return false;
    }

    public static DocumentReference currentUserDetails(){
        return FirebaseFirestore.getInstance().collection("users").document(currentUserID());
    }

    public static CollectionReference allUserCollectionReference(){
        return FirebaseFirestore.getInstance().collection("users");
    }

    public static DocumentReference getChatroomReference(String chatroomId){
        return FirebaseFirestore.getInstance().collection("chatrooms").document(chatroomId);
    }

    public static CollectionReference getChatroomMessageReference(String chatroomId){
        return getChatroomReference(chatroomId).collection("chats");
    }

    public static String getChatroomId(String userId1,String userId2){
        if(userId1.hashCode()<userId2.hashCode()){
            return userId1+"_"+userId2;
        }else{
            return userId2+"_"+userId1;
        }
    }
}