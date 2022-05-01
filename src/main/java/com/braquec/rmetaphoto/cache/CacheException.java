package com.braquec.rmetaphoto.cache;

public class CacheException extends RuntimeException{
    private static final String DESCRIPTION = "Error al consultar la cache: ";

    public CacheException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
