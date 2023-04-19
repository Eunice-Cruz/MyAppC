package com.example.myslash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myslash.BD.MyInfoBD;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void IniciarSesion (View v){
        EditText userName = (EditText) findViewById(R.id.usuarioId);
        EditText Password = (EditText) findViewById(R.id.contraseñaId);

        String mensaje = "";

        if("".equals(userName.getText().toString()) || "".equals(Password.getText().toString()))
        {
            mensaje = "Falta un Parametro";
        }else{
            if(userName.length() > 20 || Password.length() > 30){
                mensaje = "Parametro Erroneo";
                if(userName.length() > 20){mensaje = "Ingresar un nombre más corto";}
                if(Password.length() > 30){mensaje = "Ingresar una contraseña más corta";}
            }else {
                try {

                    Sha1 digest = new Sha1();
                    byte[] txtByte = digest.createSha1(userName.getText().toString() + Password.getText().toString());
                    String Sha1Password1 = digest.bytesToHex(txtByte);

                    JSON json = new JSON();

                    boolean BucleArchivo = true;
                    int x = 1;
                    int numArchivo = 0;
                    while (BucleArchivo) {
                        MyInfoBD myInfoBD = new MyInfoBD(Login.this);
                        if (myInfoBD.comprobarInfo(x)) {
                            String completoTexto = myInfoBD.verInfo(x);

                            MyInfo datos = json.leerJson(completoTexto);
                            String Sha1Password2 = datos.getPassword();

                            if (Sha1Password1.equals(Sha1Password2)) {
                                mensaje = "Usuario Encontrado";
                                numArchivo = x;
                                BucleArchivo = false;
                            } else {
                                x = x + 1;
                            }
                        }else{
                            mensaje = "Usuario no Encontrado";
                            BucleArchivo = false;
                        }
                    }

                    if("Usuario Encontrado".equals(mensaje)){
                        Toast.makeText(Login.this, mensaje, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Website.class);
                        intent.putExtra("numArchivo", numArchivo);
                        intent.putExtra("numLista", 1);
                        startActivity(intent);
                    }

                } catch (Exception e) {
                    mensaje = "Error en el Archivo";
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
        Intent intent = new Intent (Login.this, Forgotpass.class);
        startActivity( intent );
    }
}