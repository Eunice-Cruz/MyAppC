package com.example.myfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.myfinal.BD.MyInfoBD;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Acceso extends AppCompatActivity {

        TextView textview;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_acceso);
            textview = findViewById(R.id.textView);

            try {

                int numArchivo = getIntent().getExtras().getInt("numArchivo");
                JSON json = new JSON();



                MyInfoBD myInfobd = new MyInfoBD(Acceso.this);
                String completoTexto = myInfobd.checarInfo(numArchivo);
                MyInfo datos = json.leerJson(completoTexto);

                textview.setText("¡Bienvenido! " + datos.getNombre());
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
                new Handler( ).postDelayed(new Runnable() {
                    @Override
                    public void run(){
                        Intent intent = new Intent( Acceso.this, Listita.class);
                        intent.putExtra("numArchivo", numArchivo);
                        startActivity( intent );
                    }
                } , 4000 );
            }catch(Exception e){}
        }
    }