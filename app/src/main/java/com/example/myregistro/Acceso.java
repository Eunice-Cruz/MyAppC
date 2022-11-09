package com.example.myregistro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Acceso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        TextView Nombre;

        Nombre = findViewById(R.id.NombreUsr);


        try{
            int y = getIntent().getExtras().getInt("archivo");
            JSON json = new JSON();
            BufferedReader archivito = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + y + ".txt")));
            String datos = archivito.readLine();
            MyInfo Data = json.leerJson(datos);
            archivito.close();

            Nombre.setText("Bienvenid@ a la app, " +Data.getNombreId() );
        } catch(Exception e){
        }
    }
}