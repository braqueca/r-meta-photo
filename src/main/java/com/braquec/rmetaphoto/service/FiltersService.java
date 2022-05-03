package com.braquec.rmetaphoto.service;

import java.util.List;
import java.util.Map;

public interface FiltersService {
    List<FilterStrategy> getFilterStrategies(Map<String, String> queryParams);
}
