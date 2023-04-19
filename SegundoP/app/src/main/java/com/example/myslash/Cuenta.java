package com.example.myslash;

import android.graphics.Bitmap;
import android.location.Location;

import java.io.Serializable;

public class Cuenta implements Serializable{
    private String nameCuenta;
    private String passCuenta;
    private Location location;
    private boolean tipo;
    private String imageP;
    private int image;

    public Cuenta(){

    }

    public String getNameCuenta() {
        return nameCuenta;
    }

    public void setNameCuenta(String nameCuenta) {
        this.nameCuenta = nameCuenta;
    }

    public String getPassCuenta() {
        return passCuenta;
    }

    public void setPassCuenta(String passCuenta) {
        this.passCuenta = passCuenta;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public String getImageP() {
        return imageP;
    }

    public void setImageP(String imageP) {
        this.imageP = imageP;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}