package com.braquec.rmetaphoto.service;

import java.util.List;

public interface DataConverter<S,D> {
    List<D> convert(List<S> sourceList);
}
