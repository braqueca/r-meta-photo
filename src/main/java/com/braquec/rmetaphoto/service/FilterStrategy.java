package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.entity.Photo;

import java.lang.reflect.Field;

public abstract class FilterStrategy {
    protected String fieldName;
    protected String value;

    private static final String FIELD_SEPARATOR = "\\.";

    public FilterStrategy(String fieldName, String value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    abstract boolean filter(Object objectToPerformFilter);

    protected Object getFieldValue(Object objectToPerformFilter){
        String[] fieldNamesSplitted = fieldName.split(FIELD_SEPARATOR);

        Object valueFound;

        for(String fieldName: fieldNamesSplitted){
            Field fieldToPerformFilter = getFieldByName(objectToPerformFilter, fieldName);
            fieldToPerformFilter.setAccessible(true);

            try {
                objectToPerformFilter = fieldToPerformFilter.get(objectToPerformFilter);
            } catch (IllegalAccessException e) {
                throw new FieldNotAccessibleException(String.format("El atributo %s no es accesible", fieldName));
            }
        }

        return objectToPerformFilter;
    }

    protected Field getFieldByName(Object objectToPerformFilter, String fieldName){
        Field fieldToPerformFilter;

        try {
            fieldToPerformFilter = objectToPerformFilter.getClass().getDeclaredField(fieldName);
            fieldToPerformFilter.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new FieldNotFoundException(String.format("El atributo %s no fue encontrado en la clase %s",
                    fieldName,
                    Photo.class.getSimpleName()
            ));
        }

        return fieldToPerformFilter;
    }
}
