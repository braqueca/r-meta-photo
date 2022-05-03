package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.dto.AlbumDto;
import com.braquec.rmetaphoto.dto.PhotoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PhotosServiceImpl implements PhotosService {
    @Autowired
    private AlbumsService albumsService;

    private DataConverter<AlbumDto, PhotoDto> photoDataConverter;

    private static final String PHOTOS_BASE_URL = "https://jsonplaceholder.typicode.com/photos";
    private RestTemplate restTemplate = new RestTemplate();

    public PhotosServiceImpl(PhotoDataConverter photoDataConverter) {
        this.photoDataConverter = photoDataConverter;
    }

    @Override
    public List<PhotoDto> getAll() {
        List<AlbumDto> albumDtoList = albumsService.getAll();

        return photoDataConverter.convert(albumDtoList);
    }

    @Override
    public PhotoDto getById(Long id) {
        List<PhotoDto> photos = getAll();

        return photos.stream().filter(photoDto -> photoDto.getId().equals(id)).findFirst().orElseThrow();
    }
}
