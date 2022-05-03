package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.dto.AlbumDto;
import com.braquec.rmetaphoto.dto.PhotoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PhotosServiceImpl implements PhotosService {
    @Autowired
    private AlbumsService albumsService;

    @Autowired
    private FiltersService filtersService;

    private DataConverter<AlbumDto, PhotoDto> photoDataConverter;

    private static final String PHOTOS_BASE_URL = "https://jsonplaceholder.typicode.com/photos";
    private RestTemplate restTemplate = new RestTemplate();

    private static final String ID_PARAMETER = "id";

    public PhotosServiceImpl(PhotoDataConverter photoDataConverter) {
        this.photoDataConverter = photoDataConverter;
    }

    @Override
    public List<PhotoDto> getAll(Map<String,String> allRequestParams) {
        List<FilterStrategy> filters = filtersService.getFilterStrategies(allRequestParams);

        List<AlbumDto> albumDtoList = albumsService.getAll();

        List<PhotoDto> photoDtoList = photoDataConverter.convert(albumDtoList);

        Stream<PhotoDto> photoStream = photoDtoList.stream();

        for(FilterStrategy filter: filters){
            photoStream = photoStream.filter(filter::filter);
        }

        return photoStream.collect(Collectors.toList());
    }

    @Override
    public PhotoDto getById(Long id) {
        Map<String, String> params = new HashMap<>();
        params.put(ID_PARAMETER, id.toString());
        List<PhotoDto> photos = getAll(params);

        return photos.stream().findFirst().orElseThrow();
    }
}
