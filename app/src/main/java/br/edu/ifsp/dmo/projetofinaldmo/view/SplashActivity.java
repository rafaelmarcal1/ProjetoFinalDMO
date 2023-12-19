package br.edu.ifsp.dmo.projetofinaldmo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.edu.ifsp.dmo.projetofinaldmo.R;
import br.edu.ifsp.dmo.projetofinaldmo.utils.FirebaseUtil;

/*Este código é para a atividade SplashActivity em um aplicativo Android. A SplashActivity
geralmente é uma tela de introdução exibida por um curto período de tempo quando o aplicativo
é iniciado. Neste caso, a SplashActivity é utilizada para verificar se o usuário está logado
(usando o método FirebaseUtil.isLoggedIn()) e redirecioná-lo para a tela apropriada com base
nessa verificação. */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(FirebaseUtil.isLoggedIn()){
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }else{
                    startActivity(new Intent(SplashActivity.this, LoginPhoneNumberActivity.class));
                }
                finish();
            }
        },1000);
    }
}