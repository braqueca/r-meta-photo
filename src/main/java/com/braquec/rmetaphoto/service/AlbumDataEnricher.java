package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.cache.CacheException;
import com.braquec.rmetaphoto.cache.LoadingCacheStore;
import com.braquec.rmetaphoto.dto.AlbumDto;
import com.braquec.rmetaphoto.entity.Album;
import com.braquec.rmetaphoto.entity.User;
import com.braquec.rmetaphoto.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class AlbumDataEnricher implements DataEnricher<Album, AlbumDto> {
    @Autowired
    private LoadingCacheStore<Long, User> usersLoadingCache;

    @Override
    public AlbumDto enrich(Album album) {
        User user;
        try {
            user = usersLoadingCache.get(album.getUserId());
        } catch (ExecutionException e) {
            throw new CacheException(String.format("Error al consultar el usuario %s", album.getUserId()));
        }

        return ObjectMapperUtil.map(album, new AlbumDto(user));
    }
}
