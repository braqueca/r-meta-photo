package com.braquec.rmetaphoto.service;

public class ContainsFilterStrategy extends FilterStrategy{
    public ContainsFilterStrategy(String fieldName, String value) {
        super(fieldName, value);
    }

    @Override
    boolean filter(Object objectToPerformFilter) {
        Object fieldValue = getFieldValue(objectToPerformFilter);

        if(fieldValue instanceof String){
            String value = (String) fieldValue;
            return value.contains(this.value);
        }

        return false;
    }
}
