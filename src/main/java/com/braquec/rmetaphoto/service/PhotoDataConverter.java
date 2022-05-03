package com.braquec.rmetaphoto.service;

import com.braquec.rmetaphoto.dto.AlbumDto;
import com.braquec.rmetaphoto.dto.PhotoDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoDataConverter implements DataConverter<AlbumDto, PhotoDto> {

    @Override
    public List<PhotoDto> convert(List<AlbumDto> albumDtoList) {
        albumDtoList.forEach(
                albumDto -> albumDto.getPhotos().forEach(
                        photo -> photo.setAlbum(albumDto)
                )
        );

        return albumDtoList.stream().map(AlbumDto::getPhotos).flatMap(List::stream).collect(Collectors.toList());
    }


}
