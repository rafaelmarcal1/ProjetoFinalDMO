package br.edu.ifsp.dmo.projetofinaldmo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.ifsp.dmo.projetofinaldmo.R;

/*Este código é para o fragmento ProfileFragment em um aplicativo Android. Fragments são
componentes modulares reutilizáveis em aplicativos Android que representam partes de uma
interface do usuário ou comportamentos específicos. */

public class ProfileFragment extends Fragment {


    public ProfileFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}