package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.dto.PhotoDto;

import java.util.List;
import java.util.Map;

public interface PhotosService {
    List<PhotoDto> getAll(Map<String,String> allRequestParams);
    PhotoDto getById(Long id);
}
