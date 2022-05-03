package com.braquec.rmetaphoto.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FiltersServiceImpl implements FiltersService {
    private FilterStrategyFactory filterStrategyFactory = new FilterStrategyFactory();

    @Override
    public List<FilterStrategy> getFilterStrategies(Map<String, String> queryParams) {
        List<FilterStrategy> filters = new ArrayList<>();

        for(Map.Entry<String, String> entry: queryParams.entrySet()){
            filters.add(filterStrategyFactory.make(entry.getKey(), entry.getValue()));
        }

        return filters;
    }
}
