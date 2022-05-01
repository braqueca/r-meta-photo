package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.cache.CacheLoaderService;
import com.braquec.rmetaphoto.dto.AlbumDto;
import com.braquec.rmetaphoto.entity.Album;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlbumsServiceImpl implements AlbumsService, CacheLoaderService<Long, AlbumDto> {
    private DataEnricher<Album, AlbumDto> albumsDataEnricher;

    private static final String ALBUMS_BASE_URL = "https://jsonplaceholder.typicode.com/albums";
    private RestTemplate restTemplate = new RestTemplate();

    public AlbumsServiceImpl(AlbumDataEnricher albumsDataEnricher) {
        this.albumsDataEnricher = albumsDataEnricher;
    }

    @Override
    public AlbumDto getApiData(Long key) {
        return getById(key);
    }

    @Override
    public AlbumDto getById(Long id) {
        StringBuilder url = new StringBuilder(ALBUMS_BASE_URL);
        url.append("/").append(id);

        return albumsDataEnricher.enrich(restTemplate.getForObject(url.toString(), Album.class));
    }
}
