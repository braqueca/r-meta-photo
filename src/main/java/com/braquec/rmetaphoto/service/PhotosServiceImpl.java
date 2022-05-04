package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.dto.AlbumDto;
import com.braquec.rmetaphoto.dto.PhotoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private static final String LIMIT_QUERY_PARAM = "limit";
    private static final String OFFSET_QUERY_PARAM = "offset";

    public PhotosServiceImpl(PhotoDataConverter photoDataConverter) {
        this.photoDataConverter = photoDataConverter;
    }

    @Override
    public Page<PhotoDto> getAll(Map<String,String> allRequestParams) {
        int limit = Integer.valueOf(allRequestParams.getOrDefault(LIMIT_QUERY_PARAM, "25"));
        int offset = Integer.valueOf(allRequestParams.getOrDefault(OFFSET_QUERY_PARAM, "0"));
        int pageNumber = (offset / limit) + 1;

        Pageable pageable = PageRequest.of(pageNumber, limit);

        allRequestParams.remove(LIMIT_QUERY_PARAM);
        allRequestParams.remove(OFFSET_QUERY_PARAM);

        List<FilterStrategy> filters = filtersService.getFilterStrategies(allRequestParams);

        List<AlbumDto> albumDtoList = albumsService.getAll();

        List<PhotoDto> photoDtoList = photoDataConverter.convert(albumDtoList);

        long count = photoDtoList.stream().count();

        Stream<PhotoDto> photoStream = photoDtoList.stream();

        for(FilterStrategy filter: filters){
            photoStream = photoStream.filter(filter::filter);
        }

        return new PageImpl<>(
                photoStream.skip(offset).limit(limit).collect(Collectors.toList()),
                pageable,
                count
        );
    }

    @Override
    public PhotoDto getById(Long id) {
        Map<String, String> params = new HashMap<>();
        params.put(ID_PARAMETER, id.toString());
        Page<PhotoDto> photos = getAll(params);

        return photos.stream().findFirst().orElseThrow();
    }
}
