package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.dto.PhotoDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface PhotosService {
    Page<PhotoDto> getAll(Map<String,String> allRequestParams);
    PhotoDto getById(Long id);
}
