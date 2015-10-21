package com.agenda.omarche.agenda.util;

import android.net.Uri;

/**
 * Created by Omar Che on 20/10/2015.
 */
public class Contacto {

    private String Nombre;
    private String CorreoElectronico;
    private String Telefono;

    private Uri imagenUri;

    public Contacto(String nombre, String correoElectronico, String telefono, String direccion, Uri imageUri) {
        Nombre = nombre;
        CorreoElectronico = correoElectronico;
        Telefono = telefono;
        Direccion = direccion;
        imagenUri = imageUri;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        CorreoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    private String Direccion;

    public Uri getImagenUri() {
        return imagenUri;
    }

    public void setImagenUri(Uri imagenUri) {
        this.imagenUri = imagenUri;
    }

}
