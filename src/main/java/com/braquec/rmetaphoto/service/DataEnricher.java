package com.braquec.rmetaphoto.service;

public interface DataEnricher<S,D> {
    D enrich(S sourceObject);
}
