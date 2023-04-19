package com.example.myslash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myslash.BD.MyInfoBD;

import org.json.JSONException;
import org.json.JSONObject;

public class Olvide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoverpass);
    }

    public void RecuperarContraseña (View v){
        EditText userName = (EditText) findViewById(R.id.editTextFPName);
        EditText Mail = (EditText) findViewById(R.id.editTextFPMail);

        String mensaje = "";

        if("".equals(userName.getText().toString()) || "".equals(Mail.getText().toString()))
        {
            mensaje = "Completar todos los campos";
        }else{
            boolean TipoCorreo = false;
            String Correo = "";
            for(int x = 0 ; x < Mail.length(); x++){
                if(Mail.getText().charAt(x) == '@'){
                    for(int y = x ; y < Mail.length(); y++){
                        Correo = Correo + Mail.getText().charAt(y);
                    }
                    if("@gmail.com".equals(Correo) || "@hotmail.com".equals(Correo) || "@outlook.com".equals(Correo)){
                        TipoCorreo = true;
                    }
                    break;
                }
            }
            if(userName.length() > 20 || Mail.length() > 25 || TipoCorreo == false){
                mensaje = "Error";
                if(userName.length() > 20){mensaje = "¡Ingresa un nombre más corto!";}
                if(Mail.length() > 25){mensaje = "¡Ingresa un correo más corto!";}
                if(TipoCorreo == false){mensaje = "¡Ingresa un correo más válido!";}
            }else {
                try {
                    JSON json = new JSON();
                    Des myDes = new Des();

                    String MailCorreo = "";
                    String HTMLCorreo = "";
                    String valorPass = "";

                    boolean BucleArchivo = true;
                    int x = 1;
                    int numArchivo = 0;
                    while (BucleArchivo) {

                        MyInfoBD dbPagina = new MyInfoBD(Olvide.this);

                        if(dbPagina.comprobarInfo(x)){


                            String completoTexto = dbPagina.verInfo(x);

                            MyInfo datos = json.leerJson(completoTexto);
                            String valorName = datos.getUsuario();
                            String valorMail = datos.getMail();

                            if (valorName.equals(userName.getText().toString()) & valorMail.equals(Mail.getText().toString())) {
                                mensaje = "Se encontró al usuario";
                                MailCorreo = valorMail;
                                valorPass = String.format(String.valueOf(Math.random() * 10000));

                                Sha1 digest = new Sha1();
                                byte[] txtByte = digest.createSha1(valorName + valorPass);
                                String Sha1Password = digest.bytesToHex(txtByte);

                                String textoJson = json.crearJson(datos.getNombre(), datos.getFecha(), datos.getArtista(), datos.getUsuario(),
                                        datos.getMail(), datos.getEdad(), datos.getBoletar(), datos.isGenero(), datos.isAnimal(), Sha1Password);


                                dbPagina.editarInfo(x, textoJson);

                                BucleArchivo = false;
                            } else {
                                x = x + 1;
                            }
                        }else{
                            mensaje = "Usuario no Encontrado";
                            BucleArchivo = false;
                        }
                    }
                    if("Se encontró al usuario".equals(mensaje)){
                        HTMLCorreo = "<html><head><title> Recuperacion de Contraseña </title></head><style>.div {background-color: #fecece;}</style><body><div class=3D\"email_container\" style=3D\"font-family:'Noto Sans', 'NotoSans'=\n" +
                                ", 'Apple-Gothic', '=EC=95=A0=ED=94=8C=EA=B3=A0=EB=94=95', 'Malgun Gothic', =\n" +
                                "'Roboto', sans-serif; position:relative; width: 100%;\"> <h1><Font color=#FECECE> ¡Recuperación de contraseña! </h1></font> Hola, le enviamos este correo desde la aplicación Connectech para recuperar su contraseña," +
                                "su nueva contraseña es: " + valorPass + " si usted no lo solicito ignore este mensaje</div></body></html>";
                        MailCorreo = myDes.cifrar(MailCorreo);
                        HTMLCorreo = myDes.cifrar(HTMLCorreo);
                        if( sendInfo( MailCorreo, HTMLCorreo ) ) {
                            mensaje = "Se envío el Correo";
                        }
                        else {
                            mensaje = "Error en el envío del Correo";
                        }
                    }
                } catch (Exception e) {
                    mensaje = "Error en el Archivo";
                }
            }
        }
        Toast.makeText(Olvide.this, mensaje, Toast.LENGTH_SHORT).show();
    }
    public void Volver (View v){
        Intent intent = new Intent (Olvide.this, Login.class);
        startActivity( intent );
    }
    public boolean sendInfo( String Correo , String HTML )
    {
        String TAG = "App";
        JsonObjectRequest jsonObjectRequest = null;
        JSONObject jsonObject = null;
        String url = "https://us-central1-nemidesarrollo.cloudfunctions.net/envio_correo";
        RequestQueue requestQueue = null;
        jsonObject = new JSONObject( );
        try {
            jsonObject.put("correo" , Correo);
            jsonObject.put("mensaje", HTML);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.i(TAG, response.toString());
            }
        } , new  Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        } );
        requestQueue = Volley.newRequestQueue( getBaseContext() );
        requestQueue.add(jsonObjectRequest);
        return true;
    }
}