package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.dto.PhotoDto;

import java.util.List;

public interface PhotosService {
    List<PhotoDto> getAll();
    PhotoDto getById(Long id);
}
