package com.example.myfinal.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyInfoBDService extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "Usuarios.db";
    public static final String TABLE_USUARIOS = "t_usuarios";
    public static final String TABLE_CUENTA = "t_cuenta";

    public MyInfoBDService(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY NOT NULL," +
                "textoC TEXT NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CUENTA + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idUsr INTEGER NOT NULL," +
                "idCut INTEGER NOT NULL," +
                "textoCC TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUARIOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CUENTA);
        onCreate(sqLiteDatabase);

    }

}
