package projetofaculdade.example.ifood_ucsal.acitivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import projetofaculdade.example.ifood_ucsal.R;
import projetofaculdade.example.ifood_ucsal.helper.ConfiguracaoFirebase;

public class MainActivity2 extends AppCompatActivity {
    private Button botaoAcessar;
    private EditText campoEmail, campoSenha;
    private Switch tipoAcesso;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        inicializarComponetes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        verificarUsuarioLogado();
        botaoAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();
                if (!email.isEmpty()) {
                    if (!senha.isEmpty()) {
                        if (tipoAcesso.isChecked()) {
                            autenticacao.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity2.this, "Cadastro Realizado com suscesso!", Toast.LENGTH_SHORT).show();
                                        abrirTelaPrincipal();
                                    } else {
                                        String erroExcecao = "";
                                        try {
                                            throw task.getException();
                                        } catch (FirebaseAuthWeakPasswordException e) {
                                            erroExcecao = "Digite uma senha mais forte!";
                                        } catch (FirebaseAuthInvalidCredentialsException e) {
                                            erroExcecao = "Digite um E-mail valido! ";
                                        } catch (FirebaseAuthUserCollisionException e) {
                                            erroExcecao = "Esta conta ja foi cadastrada ";
                                        } catch (Exception e) {
                                            erroExcecao = "Ao cadastrar usuário:" + e.getMessage();
                                            e.printStackTrace();
                                        }
                                        Toast.makeText(MainActivity2.this, "Erro:" + erroExcecao, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {//login
                            autenticacao.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity2.this, "Logado com suscesso :", Toast.LENGTH_LONG).show();
                                        abrirTelaPrincipal();
                                    } else {
                                        Toast.makeText(MainActivity2.this, "Erro ao fazer login :", Toast.LENGTH_LONG).show();
                                    }
                                }


                            });
                        }
                    } else {
                        Toast.makeText(MainActivity2.this, "Preencha a Senha", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity2.this, "Preencha o E-mail", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void verificarUsuarioLogado(){
        FirebaseUser usuarioAtual = autenticacao.getCurrentUser();
        if (usuarioAtual != null){
            abrirTelaPrincipal();
        }
    }

    private void abrirTelaPrincipal() {
        startActivity(new Intent(getApplicationContext(), MainActivity3.class));
    }

    protected void inicializarComponetes() {
        campoEmail = findViewById(R.id.EditCadastroEmail);
        campoSenha = findViewById(R.id.EditCadastroSenha);
        botaoAcessar = findViewById(R.id.buttonAcesso);
        tipoAcesso = findViewById(R.id.switchAcesso);
    }
}



