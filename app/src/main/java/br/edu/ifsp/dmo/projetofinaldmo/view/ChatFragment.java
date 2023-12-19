package br.edu.ifsp.dmo.projetofinaldmo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.ifsp.dmo.projetofinaldmo.R;

/*O código apresenta um fragmento (ChatFragment) em um aplicativo Android. Fragments são
componentes reutilizáveis em interfaces do usuário que representam partes modulares de uma tela. */
public class ChatFragment extends Fragment {

    public ChatFragment() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }
}