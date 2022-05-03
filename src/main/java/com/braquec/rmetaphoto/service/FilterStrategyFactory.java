package com.braquec.rmetaphoto.service;

public class FilterStrategyFactory {
    private static final String TITLE_FIELD_NAME = "title";
    private static final String FIELD_SEPARATOR = "\\.";

    public FilterStrategy make(String fieldName, String value){
        String[] fieldNameSplitted = fieldName.split(FIELD_SEPARATOR);
        String finalFieldName = fieldNameSplitted[fieldNameSplitted.length - 1];

        switch (finalFieldName){
            case TITLE_FIELD_NAME:
                return new ContainsFilterStrategy(fieldName, value);
            default:
                return new EqualsFilterStrategy(fieldName, value);
        }
    }
}
