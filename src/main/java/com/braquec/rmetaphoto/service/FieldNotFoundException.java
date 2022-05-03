package com.braquec.rmetaphoto.service;

public class FieldNotFoundException extends RuntimeException {
    private static final String DESCRIPTION = "Error al obtener el campo de la clase: ";

    public FieldNotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
