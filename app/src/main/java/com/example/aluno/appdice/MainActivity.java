package com.example.aluno.appdice;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText email;
    EditText mensagem;
    EditText assunto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.btnEmail);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    protected void sendEmail() {
        email = (EditText) findViewById(R.id.email);
        mensagem = (EditText) findViewById(R.id.mensagem);
        assunto = (EditText) findViewById(R.id.assunto);

        String emailText = email.getText().toString();
        String messagemText = mensagem.getText().toString();
        String assuntoText = assunto.getText().toString();
        String emails[] = emailText.split(";");

        Log.i("0", "");
        String[] TO = {""};
     //   String[] CC = {"String [] BCC = {bcc1, bcc2, bcc3}"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("Data:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emails);
        //assunto
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, assuntoText);
        //mensagem
        emailIntent.putExtra(Intent.EXTRA_TEXT, messagemText);

        try {
            startActivity(Intent.createChooser(emailIntent, "Envio de E-mail..."));
            Log.i("Enviando e-mail...", "");
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "Error.", Toast.LENGTH_SHORT).show();
        }
        assunto.setText("");
        mensagem.setText("");
        email.setText("");
    }
}