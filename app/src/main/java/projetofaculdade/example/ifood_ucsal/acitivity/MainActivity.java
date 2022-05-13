package projetofaculdade.example.ifood_ucsal.acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import projetofaculdade.example.ifood_ucsal.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       getSupportActionBar().hide(); //Tira Barra branca de cima

        new Handler().postDelayed(new Runnable() {
           @Override
            public void run() {
           abrirAutenticacao();
            }
       }, 3000); //quantos segundos a tela primeira tela vai aparecer
    }
    private void abrirAutenticacao(){
        Intent i = new Intent(MainActivity.this,MainActivity2.class); //trocar tela
        startActivity(i);
        finish(); //finalizar a primeira tela
    }
}