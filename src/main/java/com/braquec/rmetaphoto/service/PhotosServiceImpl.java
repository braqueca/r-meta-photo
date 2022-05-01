package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.dto.PhotoDto;
import com.braquec.rmetaphoto.entity.Photo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PhotosServiceImpl implements PhotosService {
    private DataEnricher<Photo, PhotoDto> photoDataEnricher;

    private static final String PHOTOS_BASE_URL = "https://jsonplaceholder.typicode.com/photos";
    private RestTemplate restTemplate = new RestTemplate();

    public PhotosServiceImpl(PhotoDataEnricher photoDataEnricher) {
        this.photoDataEnricher = photoDataEnricher;
    }

    @Override
    public List<PhotoDto> getAll() {
        Photo[] photos = restTemplate.getForObject(PHOTOS_BASE_URL, Photo[].class);

        return Stream.of(photos).map(photoDataEnricher::enrich).collect(Collectors.toList());
    }

    @Override
    public PhotoDto getById(Long id) {
        StringBuilder url = new StringBuilder(PHOTOS_BASE_URL);
        url.append("/").append(id);

        return photoDataEnricher.enrich(restTemplate.getForObject(url.toString(), Photo.class));
    }
}
