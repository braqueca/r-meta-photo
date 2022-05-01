package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.cache.CacheException;
import com.braquec.rmetaphoto.cache.LoadingCacheStore;
import com.braquec.rmetaphoto.dto.AlbumDto;
import com.braquec.rmetaphoto.dto.PhotoDto;
import com.braquec.rmetaphoto.entity.Photo;
import com.braquec.rmetaphoto.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class PhotoDataEnricher implements DataEnricher<Photo, PhotoDto>{
    @Autowired
    private LoadingCacheStore<Long, AlbumDto> albumsLoadingCache;

    @Override
    public PhotoDto enrich(Photo photo) {
        AlbumDto album;
        try {
            album = albumsLoadingCache.get(photo.getAlbumId());
        } catch (ExecutionException e) {
            throw new CacheException(String.format("Error al consultar el album %s", photo.getAlbumId()));
        }

        return ObjectMapperUtil.map(photo, new PhotoDto(album));
    }
}
