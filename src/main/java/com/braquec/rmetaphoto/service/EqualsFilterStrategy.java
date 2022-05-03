package com.braquec.rmetaphoto.service;

public class EqualsFilterStrategy extends FilterStrategy{
    public EqualsFilterStrategy(String fieldName, String value) {
        super(fieldName, value);
    }

    @Override
    boolean filter(Object objectToPerformFilter) {
        Object fieldValue = getFieldValue(objectToPerformFilter);

        if(fieldValue instanceof String){
            String value = (String) fieldValue;
            return value.equals(this.value);
        } else if (fieldValue instanceof Long){
            Long value = (Long) fieldValue;
            Long filterValue = Long.valueOf(this.value);

            return value.equals(filterValue);
        }

        return false;
    }
}
