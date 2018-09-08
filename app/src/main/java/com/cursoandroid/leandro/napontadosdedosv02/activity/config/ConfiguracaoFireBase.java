package com.cursoandroid.leandro.napontadosdedosv02.activity.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFireBase {

    private static FirebaseAuth autenticacao;

    //retorna instancia firebase auth


    public static FirebaseAuth getFireBaseAutenticacao() {
        if(autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;

    }
}
