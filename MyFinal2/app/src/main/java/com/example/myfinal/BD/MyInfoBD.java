package com.example.myfinal.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class MyInfoBD extends MyInfoBDService{

    Context context;

    public MyInfoBD(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public boolean validarInfo(int id){
        MyInfoBDService dbPagina = new MyInfoBDService(context);
        SQLiteDatabase db = dbPagina.getWritableDatabase();

        boolean Info;
        Cursor cursorInfo;

        cursorInfo = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorInfo.moveToFirst()) {
            Info = true;
        }else{
            Info = false;
        }

        cursorInfo.close();

        return Info;
    }

    public String checarInfo(int id) {

        MyInfoBDService dbPagina = new MyInfoBDService(context);
        SQLiteDatabase db = dbPagina.getWritableDatabase();

        String Info = null;
        Cursor cursorInfo;

        cursorInfo = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorInfo.moveToFirst()) {
            Info = cursorInfo.getString(1);
        }

        cursorInfo.close();

        return Info;
    }

    public long insertarInfo(int id, String textoC) {

        long status = 0;

        try {
            MyInfoBDService dbPagina = new MyInfoBDService(context);
            SQLiteDatabase db = dbPagina.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("id", id);
            values.put("textoC", textoC);

            status = db.insert(TABLE_USUARIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return status;
    }

    public boolean editarInfo(int id, String textoC) {

        boolean correcto = false;

        MyInfoBDService dbPagina = new MyInfoBDService(context);
        SQLiteDatabase db = dbPagina.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_USUARIOS + " SET textoC = '" + textoC + "' WHERE id = " + id + "");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

}
