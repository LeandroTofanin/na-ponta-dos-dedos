package com.cursoandroid.leandro.napontadosdedosv02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cursoandroid.leandro.napontadosdedosv02.activity.CadastroActivity;
import com.cursoandroid.leandro.napontadosdedosv02.activity.LoginActivity;

public class IntroCadastro extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }
}
