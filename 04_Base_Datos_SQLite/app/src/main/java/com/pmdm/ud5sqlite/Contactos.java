package com.pmdm.ud5sqlite;

public class Contactos {

    long numero;
    String nombre;
    String telefono;
    String tipo;

    Contactos(long numerod, String nombred, String telefonod, String tipod) {
        numero = numerod;
        nombre = nombred;
        telefono = telefonod;
        tipo = tipod;
    }

    Contactos(String nombred, String telefonod, String tipod) {
        nombre = nombred;
        telefono = telefonod;
        tipo = tipod;
    }

    Contactos() {
        numero = 0;
        nombre = null;
        telefono = null;
        tipo = null;
    }

    public void setNombre(String nombred) {
        nombre = nombred;
    }

    public void setNumero(long numerod) {
        numero = numerod;
    }

    public void setTelefono(String telefonod) {
        telefono = telefonod;
    }

    public void setTipo(String tipod) {
        tipo = tipod;
    }

    public String getNombre() {
        return nombre;
    }

    public long getNumero() {
        return numero;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTipo() {
        return tipo;
    }
}
