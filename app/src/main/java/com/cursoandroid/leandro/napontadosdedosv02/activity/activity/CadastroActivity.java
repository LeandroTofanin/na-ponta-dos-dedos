package com.cursoandroid.leandro.napontadosdedosv02.activity.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cursoandroid.leandro.napontadosdedosv02.R;
import com.cursoandroid.leandro.napontadosdedosv02.activity.config.ConfiguracaoFireBase;
import com.cursoandroid.leandro.napontadosdedosv02.activity.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById(R.id.editNome);
        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        botaoCadastrar = findViewById(R.id.buttonCadastrar);


        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoNome = campoNome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                //validação
                if (!textoNome.isEmpty()){
                    if (!textoEmail.isEmpty()){
                        if (!textoSenha.isEmpty()){
                            usuario = new Usuario();
                            usuario.setNome(textoNome);
                            usuario.setEmail(textoEmail);
                            usuario.setSenha(textoSenha);
                            cadastrarUsuario();
                        }
                        else{
                            Toast.makeText(CadastroActivity.this, "Preencha a senha!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(CadastroActivity.this, "Preencha o e-mail!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(CadastroActivity.this, "Preencha o nome!!", Toast.LENGTH_SHORT).show();
                }

            }


        });



    }

    public void cadastrarUsuario(){
        autenticacao = ConfiguracaoFireBase.getFireBaseAutenticacao();

        autenticacao.createUserWithEmailAndPassword( usuario.getEmail(),usuario.getSenha()
        ) .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar o usuário!", Toast.LENGTH_SHORT).show();

                }else{
                    String excecao = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte!";

                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Por Favor Digite um e-mail válido";

                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Este usuário já esta cadastrado";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuário" + e.getMessage();
                        e.getStackTrace();
                    }

                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
