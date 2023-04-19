package com.example.myslash;

import android.graphics.Bitmap;
import android.location.Location;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class JSON extends AppCompatActivity{

    public static String crearJson(String JNombre , String JFecha , String JArtista , String JUsuario , String JMail , int JEdad , int JBoleta , boolean JGenero , boolean JAnimal , String Password ) {
        MyInfo datos = new MyInfo();
        Gson gson = new Gson();
        Des myDes = new Des();

        datos.setNombre(JNombre);
        datos.setFecha(JFecha);
        datos.setArtista(JArtista);
        datos.setUsuario(JUsuario);
        datos.setMail(JMail);
        datos.setEdad(JEdad);
        datos.setBoletar(JBoleta);
        datos.setGenero(JGenero);
        datos.setAnimal(JAnimal);
        datos.setPassword(Password);

        String nuevojson = myDes.cifrar(gson.toJson(datos));

        return nuevojson;
    }

    public static MyInfo leerJson(String textoJson){
        Gson gson = new Gson();
        Des myDes = new Des();
        MyInfo datos = gson.fromJson(myDes.desCifrar(textoJson), MyInfo.class);

        return datos;
    }

    public static String crearJsonCuenta(String Name , String Password , Location location , boolean tipo , Bitmap bitmap , int Image) {
        Cuenta datos = new Cuenta();
        Gson gson = new Gson();
        Des myDes = new Des();
        EncripBitMap EBM = new EncripBitMap();

        String imageP;

        if(tipo) {
            imageP = EBM.cifrar(bitmap);
        }else{
            imageP = null;
        }

        datos.setNameCuenta(Name);
        datos.setPassCuenta(Password);
        datos.setLocation(location);
        datos.setTipo(tipo);
        datos.setImageP(imageP);
        datos.setImage(Image);

        String nuevojson = myDes.cifrar(gson.toJson(datos));

        return nuevojson;
    }

    public static Cuenta leerJsonCuenta(String textoJson){
        Gson gson = new Gson();
        Des myDes = new Des();
        Cuenta datos = gson.fromJson(myDes.desCifrar(textoJson), Cuenta.class);

        return datos;
    }
}
