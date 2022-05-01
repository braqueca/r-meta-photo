package com.braquec.rmetaphoto.cache;

import com.braquec.rmetaphoto.dto.AlbumDto;
import com.braquec.rmetaphoto.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStoreConfiguration {
    @Bean
    public LoadingCacheStore<Long, AlbumDto> albumLoadingCache(CacheLoaderService<Long, AlbumDto> albumsService) {
        return new LoadingCacheStore<>(28, TimeUnit.SECONDS, albumsService);
    }

    @Bean
    public LoadingCacheStore<Long, User> usersLoadingCache(CacheLoaderService<Long, User> usersService) {
        return new LoadingCacheStore<>(56, TimeUnit.SECONDS, usersService);
    }
}
