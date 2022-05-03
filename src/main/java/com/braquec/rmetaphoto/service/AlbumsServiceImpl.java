package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.dto.AlbumDto;
import com.braquec.rmetaphoto.entity.Album;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AlbumsServiceImpl implements AlbumsService {
    private static final String ALBUMS_BASE_URL = "https://jsonplaceholder.typicode.com/albums";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<AlbumDto> getAll() {
        StringBuilder url = new StringBuilder(ALBUMS_BASE_URL);
        url.append("/").append("?_embed=photos&_expand=user");

        return Arrays.asList(restTemplate.getForObject(url.toString(), AlbumDto[].class));
    }
}
