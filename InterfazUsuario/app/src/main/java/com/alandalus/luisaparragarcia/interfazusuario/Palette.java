package com.alandalus.luisaparragarcia.interfazusuario;

/**
 * Created by luisaparragarcia on 15/11/17.
 */

public class Palette {
    private String name;
    private String hexValue;
    private int intValue;

    public Palette(String name, String hexValue, int intValue) {
        this.name = name;
        this.hexValue = hexValue;
        this.intValue = intValue;
    }

    public String getName() {
        return name;
    }

    public String getHexValue() {
        return hexValue;
    }

    public int getIntValue() {
        return intValue;
    }
}
