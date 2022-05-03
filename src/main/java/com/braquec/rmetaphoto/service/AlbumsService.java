package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.dto.AlbumDto;
import com.braquec.rmetaphoto.entity.Album;

import java.util.List;

public interface AlbumsService {
    List<AlbumDto> getAll();
}
