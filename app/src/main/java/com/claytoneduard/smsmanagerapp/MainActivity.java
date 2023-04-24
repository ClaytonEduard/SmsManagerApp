package com.claytoneduard.smsmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Permissions;

public class MainActivity extends AppCompatActivity {

    private TextView textResposta;
    private TextView txtTelefone;
    private TextView txtMensagem;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResposta = findViewById(R.id.txtResultado);
        txtMensagem = findViewById(R.id.txtMensagem);
        txtTelefone = findViewById(R.id.txtTelefone);
        btnEnviar = findViewById(R.id.btnEnviar);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED){
                String[] permissions = {Manifest.permission.SEND_SMS};
                requestPermissions(permissions, 1);
            }
        }
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarSMS(txtTelefone.getText().toString(), txtMensagem.getText().toString());
            }
        });
    }
    public void enviarSMS(String numero, String mensagem){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(numero,null, mensagem, null, null);
        Toast.makeText(MainActivity.this, "Enviado Com Sucesso", Toast.LENGTH_LONG).show();
    }
}