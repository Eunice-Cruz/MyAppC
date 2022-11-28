package com.example.myfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Serializable;

public class MyCuenta implements Serializable {
    private String nameCuenta;
    private String pswdCuenta;
    private int image;

    public MyCuenta(){

    }

    public String getNameCuenta() {
        return nameCuenta;
    }

    public void setNameCuenta(String nameCuenta) {
        this.nameCuenta = nameCuenta;
    }

    public String getPassCuenta() {
        return pswdCuenta;
    }

    public void setPassCuenta(String passCuenta) {
        this.pswdCuenta = passCuenta;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}