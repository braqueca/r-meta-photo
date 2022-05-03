package com.braquec.rmetaphoto.service;

public class FieldNotAccessibleException extends RuntimeException  {
    private static final String DESCRIPTION = "El atributo no es accesible: ";

    public FieldNotAccessibleException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
