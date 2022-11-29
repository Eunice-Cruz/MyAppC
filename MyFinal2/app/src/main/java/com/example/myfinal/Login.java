package com.example.myfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void IniciarSesion (View v){
        EditText usuario = (EditText) findViewById(R.id.usuarioId);
        EditText pswd = (EditText) findViewById(R.id.contraseñaId);

        String mensaje = "";

        if("".equals(usuario.getText().toString()) || "".equals(pswd.getText().toString()))
        {
            mensaje = "Completa todos los campos";
        }else{
            if(usuario.length() > 20 || pswd.length() > 30){
                mensaje = "Error";
                if(usuario.length() > 20){mensaje = "¡Ingresa un usuario más corto!";}
                if(pswd.length() > 30){mensaje = "¡Ingresa una contraseña más corta!";}
            }else {
                try {

                    Sha1 digest = new Sha1();
                    byte[] txtByte = digest.createSha1(usuario.getText().toString() + pswd.getText().toString());
                    String Sha1Password1 = digest.bytesToHex(txtByte);

                    JSON json = new JSON();

                    boolean BucleArchivo = true;
                    int x = 1;
                    int numArchivo = 0;
                    while (BucleArchivo) {
                        File Cfile = new File(getApplicationContext().getFilesDir() + "/" + "Archivo" + x + ".txt");
                        if(Cfile.exists()) {
                            BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + x + ".txt")));
                            String lineaTexto = file.readLine();
                            String completoTexto = "";
                            while(lineaTexto != null){
                                completoTexto = completoTexto + lineaTexto;
                                lineaTexto = file.readLine();
                            }
                            file.close();

                            MyInfo datos = json.leerJson(completoTexto);
                            String Sha1Password2 = datos.getPassword();

                            if (Sha1Password1.equals(Sha1Password2)) {
                                mensaje = "Bienvenido";
                                numArchivo = x;
                                BucleArchivo = false;
                            } else {
                                x = x + 1;
                            }
                        }else{
                            mensaje = "Error";
                            BucleArchivo = false;
                        }
                    }

                    if("Bienvenido".equals(mensaje)){
                        Toast.makeText(Login.this, mensaje, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Acceso.class);
                        intent.putExtra("numArchivo", numArchivo);
                        startActivity(intent);
                    }

                } catch (Exception e) {
                    mensaje = "Error";
                }
            }
        }
        Toast.makeText(Login.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void Registrarse (View v){
        Intent intent = new Intent (Login.this, Registro.class);
        startActivity( intent );
    }

    public void OlvidarContra (View v){
        Intent intent = new Intent (Login.this, Olvide.class);
        startActivity( intent );
    }
}