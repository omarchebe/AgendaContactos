package com.agenda.omarche.agenda.entity;

import java.io.Serializable;

public class Contacto implements Serializable {

    private String Nombre;
    private String CorreoElectronico;
    private String Telefono;

    private String imagenUri;



    public Contacto(String nombre, String correoElectronico, String telefono, String direccion, String imageUri) {
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

    public String getImagenUri() {
        return imagenUri;
    }

    public void setImagenUri(String imagenUri) {
        this.imagenUri = imagenUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacto contacto = (Contacto) o;

        if (Nombre != null ? !Nombre.equals(contacto.Nombre) : contacto.Nombre != null)
            return false;
        if (CorreoElectronico != null ? !CorreoElectronico.equals(contacto.CorreoElectronico) : contacto.CorreoElectronico != null)
            return false;
        if (Telefono != null ? !Telefono.equals(contacto.Telefono) : contacto.Telefono != null)
            return false;
        if (imagenUri != null ? !imagenUri.equals(contacto.imagenUri) : contacto.imagenUri != null)
            return false;
        return !(Direccion != null ? !Direccion.equals(contacto.Direccion) : contacto.Direccion != null);

    }

    @Override
    public int hashCode() {
        int result = Nombre != null ? Nombre.hashCode() : 0;
        result = 31 * result + (CorreoElectronico != null ? CorreoElectronico.hashCode() : 0);
        result = 31 * result + (Telefono != null ? Telefono.hashCode() : 0);
        result = 31 * result + (imagenUri != null ? imagenUri.hashCode() : 0);
        result = 31 * result + (Direccion != null ? Direccion.hashCode() : 0);
        return result;
    }
}
